package com.oc.ly.PayMyBuddy.service;


import com.oc.ly.PayMyBuddy.dto.TransactionDTO;
import com.oc.ly.PayMyBuddy.dto.UserDTO;
import com.oc.ly.PayMyBuddy.exceptions.DataNotConformException;
import com.oc.ly.PayMyBuddy.exceptions.DataNotFoundException;
import com.oc.ly.PayMyBuddy.model.Transaction;
import com.oc.ly.PayMyBuddy.model.User;
import com.oc.ly.PayMyBuddy.repository.TransactionRepository;
import com.oc.ly.PayMyBuddy.repository.UserRepository;
import com.oc.ly.PayMyBuddy.tool.Factory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

@Service("TransactionService")
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    private Factory factory = new Factory();

    private static Logger logger = LogManager.getLogger(TransactionServiceImpl.class);

    LocalDate today = LocalDate.now();
    // @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TransactionDTO addTransaction(TransactionDTO transactionDTO) {

        logger.info("---> processing of the request to add a transaction by the service   !");


            //-- Vérification de la validitée des données
                if (transactionDTO.getDescription().length() > 30) {
                    throw new DataNotConformException("The name is too big! (30 characters maximum)");
                }
                if (transactionDTO.getAmount() <= 0) {
                    throw new DataNotConformException("The amount must be positive");
                }

                if (userRepository.findUserById(transactionDTO.getPayer().getId())==null) {
                    throw new DataNotFoundException("Problem with the database, user payer not found");
                }

      //  try {

           //-- mise en place des données manquante pour la transaction
            LocalDateTime createDate = LocalDateTime.now();
            Double fee = (double)Math.round((transactionDTO.getAmount()*0.005)*100)/100;
            transactionDTO.setCreationDate(createDate);
            transactionDTO.setFee(fee);

            //-- Appel de la methode de vérification de la provision du wallet
            Boolean bUserWalletOperation = walletOperation(transactionDTO);
            //-- déclaration de la transaction

            TransactionDTO t = transactionDTO;
            Transaction transaction = new Transaction();

            if (bUserWalletOperation == true){
                //- sauvegarde de la transaction
                    transaction = factory.constructTransaction(transactionDTO);
                    transactionRepository.save(transaction);
                //- Récuperation des données venant de la DB en réinjectant dans le DTO
                   // factory.constructTransactionDTO(transactionDTO, transaction);
                //- Controle par l'ID reçu
                    logger.info("   ---> ID of the transaction created:"+ transactionDTO.getIdTransaction());
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

                    userRepository.save(transactionDTO.getBeneficiary());
                   // userRepository.save(null);
                    userRepository.save(transactionDTO.getPayer());
                    logger.info("  ---> Save transaction ok! Update Users ok!");
                return transactionDTO;
            } else {
                logger.info("  ---> the amount withdrawn exceeds the wallet");
                throw new DataNotConformException("the amount exceeds the wallet");
            }
     /*   }
        catch (Exception e){
           throw new DataNotFoundException("Problem with the DataBase");
        }*/
    }


    @Override
    public Transaction updateTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public Transaction deleteTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public Transaction deleteById(int idTransaction) {
        transactionRepository.deleteById(idTransaction);
        return null;
    }

    @Override
    public List<Transaction> allTransaction() {
        return null;
    }

    @Override
    public List<Transaction> FindTransactionByPayer(User payer) {
        return null;
    }

    @Override
    public Page<TransactionDTO> theLastThreeTransactions(UserDTO userDTO, Pageable pageable) {
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
        for (TransactionDTO t: pagesTransactionDTO)
        {
            logger.info("--- montant du dto de Page<DTO> " + t.getAmount());
        }
        return pagesTransactionDTO;
    }

    @Override
    public Page<Transaction> findAllByPayer(UserDTO payerDTO, Pageable pageable) {
        User payer = factory.constructUser(payerDTO);
        return transactionRepository.findAllByPayer(payer, pageable);
    }

    //---------------------------------------------------------------------------------------
    private Boolean walletOperation(TransactionDTO transactionDTO) {
        if ( transactionDTO.getAmount() == 0) {
            throw new DataNotFoundException("Amount must be greater than 0");
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
