package com.oc.ly.PayMyBuddy.tool;

import com.oc.ly.PayMyBuddy.dto.*;
import com.oc.ly.PayMyBuddy.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public class Factory {

    private static Logger logger = LogManager.getLogger(Factory.class);


    LocalDate createDate = LocalDate.now();

    //----------  USER -----------------------------------------------------
    public UserDTO constructUserDTO(User u) {
        logger.info("on entre dans le construct userDTO 1");
        UserDTO usDTO = new UserDTO();
            logger.info("on entre dans le construct userDTO dans IF");
            usDTO.setId(u.getId());
            usDTO.setFirstName(u.getFirstName());
            usDTO.setUserName(u.getUserName());
            usDTO.setEmail(u.getEmail());
            usDTO.setPassword(u.getPassword());
            usDTO.setWallet(u.getWallet());
            usDTO.setActive(u.isActive());
            usDTO.setRoles(u.getRoles());
            usDTO.setCreationDate(u.getCreationDate());
            usDTO.setModifDate(u.getModifDate());
            return usDTO;

    }

    public User constructUser(UserDTO usDTO) {
        User user = new User();
        if(usDTO != null) {
            user.setId(usDTO.getId());
            user.setFirstName(usDTO.getFirstName());
            user.setUserName(usDTO.getUserName());
            user.setEmail(usDTO.getEmail());
            user.setPassword(usDTO.getPassword());
            user.setWallet(usDTO.getWallet());
            user.setActive(usDTO.isActive());
            user.setRoles(usDTO.getRoles());
            user.setCreationDate(usDTO.getCreationDate());
            user.setModifDate(usDTO.getModifDate());
            return user;
        } else{
            return null;
        }
    }


   //----------  BANK -----------------------------------------------------

    public BankAccount constructBank(BankAccountDTO bankAccountDTO) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setIdBankAccount(bankAccountDTO.getIdBankAccount());
        bankAccount.setRib(bankAccountDTO.getRib());
        bankAccount.setUser(bankAccountDTO.getUser());
        return bankAccount;
    }

    public BankAccountDTO constructBankDTO(BankAccount bankAccount) {
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setIdBankAccount(bankAccount.getIdBankAccount());
        bankAccountDTO.setRib(bankAccount.getRib());
        bankAccountDTO.setUser(bankAccount.getUser());
        return bankAccountDTO;
    }

    //----------  TRANSFER -----------------------------------------------------

    public Transfer constructTransfer(TransferDTO transferDTO) {
        Transfer transfer = new Transfer();
        transfer.setIdTransfer(transferDTO.getIdTransfer());
        transfer.setUser(transferDTO.getUser());
        transfer.setRib(transferDTO.getRib());
        transfer.setCreateDate(transferDTO.getCreateDate());
        transfer.setAmount(transferDTO.getAmount());
        transfer.setType(transferDTO.getType());
        return transfer;
    }

    public TransferDTO constructTransferDTO(Transfer transfer) {
        TransferDTO transferDTO = new TransferDTO();
        transferDTO.setIdTransfer(transfer.getIdTransfer());
        transferDTO.setUser(transfer.getUser());
        transferDTO.setRib(transferDTO.getRib());
        transferDTO.setCreateDate(transfer.getCreateDate());
        transferDTO.setAmount(transfer.getAmount());
        transferDTO.setType(transfer.getType());
       // transferDTO.setType(TransferType.valueOf(transfer.getType()));
        return transferDTO;
    }

    //----------  TRANSACTION -----------------------------------------------------

    public Transaction constructTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setIdTransaction(transactionDTO.getIdTransaction());
        transaction.setCreationDate(transactionDTO.getCreationDate());
        transaction.setBeneficiary(transactionDTO.getBeneficiary());
        transaction.setPayer(transactionDTO.getPayer());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setDescription(transactionDTO.getDescription());
        transaction.setFee(transactionDTO.getFee());
        return transaction;
    }



    public TransactionDTO constructTransactionDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setIdTransaction(transaction.getIdTransaction());
        transactionDTO.setCreationDate(transaction.getCreationDate());
        transactionDTO.setBeneficiary(transaction.getBeneficiary());
        transactionDTO.setPayer(transaction.getPayer());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setDescription(transaction.getDescription());
        transactionDTO.setFee(transaction.getFee());
        return transactionDTO;
    }

    //----------  FRIEND -----------------------------------------------------------

    public Friend constructFriend(FriendDTO friendDTO) {
        Friend friend = new Friend();
        friend.setIdFriend(friendDTO.getIdFriend());
        friend.setFriend(friendDTO.getFriend());
        friend.setOwner(friendDTO.getOwner());
        friend.setCreationDate(friendDTO.getCreationDate());
        return friend;
    }

    public FriendDTO constructFriendDTO(Friend friend) {
        FriendDTO friendDTO = new FriendDTO();
        friendDTO.setIdFriend(friend.getIdFriend());
        friendDTO.setCreationDate(friend.getCreationDate());
        friendDTO.setFriend(friend.getFriend());
        friendDTO.setOwner(friend.getOwner());
        return friendDTO;
    }

   //------------------------------------------------------------------------------
}
