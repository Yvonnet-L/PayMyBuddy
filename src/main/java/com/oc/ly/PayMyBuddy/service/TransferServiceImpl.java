package com.oc.ly.PayMyBuddy.service;

import com.oc.ly.PayMyBuddy.constants.TransferType;
import com.oc.ly.PayMyBuddy.dto.TransferDTO;
import com.oc.ly.PayMyBuddy.dto.UserDTO;
import com.oc.ly.PayMyBuddy.exceptions.DataNotConformException;
import com.oc.ly.PayMyBuddy.exceptions.DataNotFoundException;
import com.oc.ly.PayMyBuddy.model.Transfer;
import com.oc.ly.PayMyBuddy.model.User;
import com.oc.ly.PayMyBuddy.repository.TransferRepository;
import com.oc.ly.PayMyBuddy.tool.Factory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

@Service
public class TransferServiceImpl implements ITransferService{

    @Autowired
    TransferRepository transferRepository;

    @Autowired
    IUserService userService;

    public Factory factory  = new Factory();

    private static Logger logger = LogManager.getLogger(TransferServiceImpl.class);

    //-----------------------------------------------------------------------------------------------
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public TransferDTO addTransfer(String rib, String type, double amount, User user) {
        logger.info(" ---> Launch addTransfer");
        Transfer transfer = new Transfer(user, rib, LocalDateTime.now(), amount, type);
        if (tranferDataVerification(transfer)) {
               logger.info(" ----> Verification transfer ok");
                    if (transfer.getType().equals(TransferType.CREDIT_WALLET.toString())) {
                        Double newWallet =  (double)Math.round((transfer.getUser().getWallet() + transfer.getAmount())*100)/100;
                        transfer.getUser().setWallet(newWallet);

                        transferRepository.save(transfer);
                        logger.info(" ------> transfer saved");
                        userService.saveUser(factory.constructUserDTO(transfer.getUser()));
                        logger.info(" ------> user (new wallet) saved");
                    } else {
                        if (walletOperation(transfer)) {
                            Double newWallet =  (double)Math.round((transfer.getUser().getWallet() - transfer.getAmount())*100)/100;
                            transfer.getUser().setWallet(newWallet);
                            transfer.getUser().setModifDate(LocalDate.now());

                            transferRepository.save(transfer);
                            logger.info(" ------> transfer saved");
                            userService.saveUser(factory.constructUserDTO(transfer.getUser()));
                            //userService.saveUser(null);
                            logger.info(" ------> user (new wallet) saved");
                        } else {
                            throw new DataNotConformException("the amount exceeds the wallet");
                        }
                    }
                return factory.constructTransferDTO(transfer);
        }else{
            throw new DataNotConformException("invalid transfer, data problem !");
        }
    }
    //-----------------------------------------------------------------------------------------------
    @Override
    public Page<TransferDTO> findAllByUser(UserDTO userDTO, Pageable pageable) {
        logger.info(" ---> Launch findAllByUser");
        User user = factory.constructUser(userDTO);
        Page<TransferDTO> pagesTransferDTO = transferRepository.findAllByUser(user, pageable)
                                                                    .map(factory::constructTransferDTO);
        return pagesTransferDTO;

    }
    //-----------------------------------------------------------------------------------------------
    @Override
    public Page<TransferDTO> theLastThreeTransfers(UserDTO userDTO, Pageable pageable) {
        logger.info(" ---> Launch theLastThreeTransfers");
        User user = factory.constructUser(userDTO);
        Page<TransferDTO> pagesTransferDTO = transferRepository.theLastThreeTransfers(user, pageable)
                                                                    .map(factory::constructTransferDTO);
        return pagesTransferDTO;
    }
    //--------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------
    private Boolean walletOperation(Transfer transfer) {
        logger.info("  -----> Launch walletOperation");
        if ( transfer.getAmount() == 0) {
            throw new DataNotFoundException("Amount must be greater than 0");
        }
        double wallet = transfer.getUser().getWallet();
        double amount = transfer.getAmount();
        //--- vérification de la couverture --
        if( wallet - amount >= 0) {
            return true;
        }else {
            return false;
        }
    }
    //----------- verification data transfer valid -----------------------------------------------
    private Boolean tranferDataVerification(Transfer transfer){
        logger.info("  -----> Launch tranferDataVerification");
        Boolean resultat = true;
        if (transfer.getAmount() <= 0 ) {
            resultat = false;
        } ;
        logger.info("   ----->  result ammount = "+resultat);
        if (transfer.getUser() !=null) {
            if (userService.userExistById(transfer.getUser().getId()) == false) {
                resultat = false;
            }
        }else {
            resultat = false;
        }
        logger.info("   ----->  result user = "+resultat);

        String type = transfer.getType();
        if ( type.equals("CREDIT_WALLET") || type.equals("DEBIT_WALLET")) {
        }else{
            resultat=false;
        }
        logger.info("   ----->   result type = "+resultat);
        logger.info("   ----->   result tranferDataVerification= "+resultat);
        return resultat;
    }

    //--------------------------------------------------------------------------------------------
}
