package com.oc.ly.PayMyBuddy.service;


import com.oc.ly.PayMyBuddy.dto.TransactionDTO;
import com.oc.ly.PayMyBuddy.dto.UserDTO;
import com.oc.ly.PayMyBuddy.exceptions.DataNotConformException;
import com.oc.ly.PayMyBuddy.exceptions.DataNotFoundException;
import com.oc.ly.PayMyBuddy.exceptions.WalletNotEnoughException;
import com.oc.ly.PayMyBuddy.model.Transaction;
import com.oc.ly.PayMyBuddy.model.User;
import com.oc.ly.PayMyBuddy.repository.TransactionRepository;
import com.oc.ly.PayMyBuddy.tool.Factory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service("TransactionService")
@EnableTransactionManagement
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    IUserService userService;

    private Factory factory = new Factory();

    private static Logger logger = LogManager.getLogger(TransactionServiceImpl.class);

    LocalDate today = LocalDate.now();

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = RuntimeException.class)
    public TransactionDTO addTransaction(TransactionDTO transactionDTO) {

        logger.info(" ---> Launch addTransaction ");

            //-- Vérification de la validitée des données
                if (transactionDTO.getDescription().length() > 30) {
                    throw new DataNotConformException("The name is too big! (30 characters maximum)");
                }
                if (transactionDTO.getAmount() <= 0) {
                    throw new WalletNotEnoughException("The amount must be positive");
                }

                if (userService.findUserById(transactionDTO.getPayer().getId())==null) {
                    throw new DataNotFoundException("Problem with the database, user payer not found");
                }


           //-- mise en place des données manquante pour la transaction
            LocalDateTime createDate = LocalDateTime.now();
            Double fee = (double)Math.round((transactionDTO.getAmount()*0.005)*100)/100;
            transactionDTO.setCreationDate(createDate);
            transactionDTO.setFee(fee);

            //-- Appel de la methode de vérification de la provision du wallet
            Boolean bUserWalletOperation = walletOperation(transactionDTO);


            if (bUserWalletOperation == true){
                //-- déclaration de la transaction
                    Transaction transaction = new Transaction();
                //- sauvegarde de la transaction
                    transaction = factory.constructTransaction(transactionDTO);
                    transactionRepository.save(transaction);

                //- Récuperation des données venant de la DB en réinjectant dans le DTO
                    transactionDTO = factory.constructTransactionDTO(transaction);
                //- Controle par l'ID reçu
                    logger.info("   ---> ID transaction created:"+ transactionDTO.getIdTransaction());
                //- Update des wallets des Users: benneficyary et payer
                    //- benneficyary
                    transactionDTO.getBeneficiary().setWallet((transactionDTO.getBeneficiary().getWallet() + transactionDTO.getAmount()));

                    //- payer : on utilise Math.round pour arondir le calcul
                    Double newWallet = (transactionDTO.getPayer().getWallet() - (transactionDTO.getAmount() + transactionDTO.getFee()) );
                    newWallet = (double)Math.round((newWallet)*100)/100;
                    transactionDTO.getPayer().setWallet(newWallet);

                //- ajout de la date de modification chez les Users
                    transactionDTO.getBeneficiary().setModifDate(today);
                    transactionDTO.getPayer().setModifDate(today);

                    userService.saveUser(factory.constructUserDTO(transactionDTO.getPayer()));
                    userService.saveUser(factory.constructUserDTO(transactionDTO.getBeneficiary()));
                    logger.info("  ----> Save transaction ok! Update Users ok!");
                return transactionDTO;
            } else {
                throw new WalletNotEnoughException("the amount exceeds the wallet");
            }

    }

    @Override
    public List<TransactionDTO> findAll() {
        logger.info(" ---> Launch findAll ");
        List<Transaction> transactionList = transactionRepository.findAll();
        List<TransactionDTO> transactionDTOList = new ArrayList<TransactionDTO>();
        for (Transaction transaction: transactionList){
            transactionDTOList.add(factory.constructTransactionDTO(transaction));
        }
        return transactionDTOList;
    }

    @Override
    public Transaction deleteById(int idTransactionDTO) {
        logger.info(" ---> Launch deleteById ");
        transactionRepository.deleteById(idTransactionDTO);
        logger.info(" ----> transaction deleted ");
        return null;
    }

    @Override
    public Page<TransactionDTO> theLastThreeTransactions(UserDTO userDTO, Pageable pageable) {
        logger.info(" ---> Launch theLastThreeTransactions ");
        User user = factory.constructUser(userDTO);
        Page<Transaction> pagesTransaction = transactionRepository.theLastThreeTransactions(user, pageable);
        Page<TransactionDTO> pagesTransactionDTO= pagesTransaction.map(new Function<Transaction, TransactionDTO>() {
            @Override
            public TransactionDTO apply(Transaction transaction) {
                TransactionDTO transactionDTO = new TransactionDTO();
                transactionDTO = factory.constructTransactionDTO(transaction);
                return transactionDTO;
            }
        });
        return pagesTransactionDTO;
    }

    @Override
    public Page<TransactionDTO> theLastThreeTransactionsBeneficiary(UserDTO userDTO, Pageable pageable) {
        logger.info(" ---> Launch theLastThreeTransactionsBeneficiary ");
        User user = factory.constructUser(userDTO);
        Page<Transaction> pagesTransaction =transactionRepository.theLastThreeTransactionsBeneficiary(user, pageable);
            Page<TransactionDTO> pagesTransactionDTO= pagesTransaction.map(new Function<Transaction, TransactionDTO>() {
                @Override
                public TransactionDTO apply(Transaction transaction) {
                    TransactionDTO transactionDTO = new TransactionDTO();
                    transactionDTO = factory.constructTransactionDTO(transaction);
                    return transactionDTO;
                }
            });
        return pagesTransactionDTO;
    }

    @Override
    public Page<TransactionDTO> findAllByPayer(UserDTO payerDTO, Pageable pageable) {
        logger.info(" ---> Launch findAllByPayer ");
        User payer = factory.constructUser(payerDTO);
        Page<Transaction> pagesTransaction = transactionRepository.findAllByPayer(payer, pageable);
        Page<TransactionDTO> pagesTransactionDTO= pagesTransaction.map(new Function<Transaction, TransactionDTO>() {
            @Override
            public TransactionDTO apply(Transaction transaction) {
                TransactionDTO transactionDTO = new TransactionDTO();
                transactionDTO = factory.constructTransactionDTO(transaction);
                return transactionDTO;
            }
        });
        return pagesTransactionDTO;
    }

    //---------------------------------------------------------------------------------------
    private Boolean walletOperation(TransactionDTO transactionDTO) {
        logger.info("  ----> Launch walletOperation");
        if ( transactionDTO.getAmount() == 0) {
            throw new DataNotConformException("Amount must be greater than 0");
        }
        double wallet = transactionDTO.getPayer().getWallet();
        double amount = transactionDTO.getAmount();
        double fee = transactionDTO.getFee();
        //--- vérification de la couverture --
        if( wallet - (amount+fee) >= 0) {
            return true;
        }else {
            return false;
        }

    }
}
