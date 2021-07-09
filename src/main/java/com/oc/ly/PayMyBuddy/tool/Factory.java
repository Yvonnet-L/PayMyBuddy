package com.oc.ly.PayMyBuddy.tool;

import com.oc.ly.PayMyBuddy.dto.*;
import com.oc.ly.PayMyBuddy.exceptions.DataNotConformException;
import com.oc.ly.PayMyBuddy.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public class Factory {

    private static Logger logger = LogManager.getLogger(Factory.class);


    LocalDate createDate = LocalDate.now();

    //----------  USER -----------------------------------------------------
    public UserDTO constructUserDTO(User u) {
        if (u != null) {
            UserDTO usDTO = new UserDTO();
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
       }else{
            throw new DataNotConformException(" **** User not be null ****");
        }

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
        if (bankAccountDTO !=null){
        bankAccount.setIdBankAccount(bankAccountDTO.getIdBankAccount());
        bankAccount.setRib(bankAccountDTO.getRib());
        bankAccount.setUser(bankAccountDTO.getUser());
        return bankAccount;
        } else{
            return null;
        }
    }

    public BankAccountDTO constructBankDTO(BankAccount bankAccount) {
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        if (bankAccount !=null){
        bankAccountDTO.setIdBankAccount(bankAccount.getIdBankAccount());
        bankAccountDTO.setRib(bankAccount.getRib());
        bankAccountDTO.setUser(bankAccount.getUser());
        return bankAccountDTO;
        } else{
            return null;
        }
    }

    //----------  TRANSFER -----------------------------------------------------
    /*
    public Transfer constructTransfer(TransferDTO transferDTO) {
        Transfer transfer = new Transfer();
        if (transferDTO !=null){
        transfer.setIdTransfer(transferDTO.getIdTransfer());
        transfer.setUser(transferDTO.getUser());
        transfer.setRib(transferDTO.getRib());
        transfer.setCreateDate(transferDTO.getCreateDate());
        transfer.setAmount(transferDTO.getAmount());
        transfer.setType(transferDTO.getType());
        return transfer;
          } else{
            return null;
        }
    }
    */
    public TransferDTO constructTransferDTO(Transfer transfer) {
        TransferDTO transferDTO = new TransferDTO();
        if (transfer !=null){
        transferDTO.setIdTransfer(transfer.getIdTransfer());
        transferDTO.setUser(transfer.getUser());
        transferDTO.setRib(transferDTO.getRib());
        transferDTO.setCreateDate(transfer.getCreateDate());
        transferDTO.setAmount(transfer.getAmount());
        transferDTO.setType(transfer.getType());
        return transferDTO;
        } else{
            return null;
            }
    }

    //----------  TRANSACTION -----------------------------------------------------

    public Transaction constructTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        if (transactionDTO !=null){
        transaction.setIdTransaction(transactionDTO.getIdTransaction());
        transaction.setCreationDate(transactionDTO.getCreationDate());
        transaction.setBeneficiary(transactionDTO.getBeneficiary());
        transaction.setPayer(transactionDTO.getPayer());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setDescription(transactionDTO.getDescription());
        transaction.setFee(transactionDTO.getFee());
        return transaction;
        } else{
        return null;
        }
    }



    public TransactionDTO constructTransactionDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        if (transaction !=null){
        transactionDTO.setIdTransaction(transaction.getIdTransaction());
        transactionDTO.setCreationDate(transaction.getCreationDate());
        transactionDTO.setBeneficiary(transaction.getBeneficiary());
        transactionDTO.setPayer(transaction.getPayer());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setDescription(transaction.getDescription());
        transactionDTO.setFee(transaction.getFee());
        return transactionDTO;
        } else{
        return null;
        }
    }

    //----------  FRIEND -----------------------------------------------------------

    public Friend constructFriend(FriendDTO friendDTO) {
        Friend friend = new Friend();
        if (friendDTO !=null){
        friend.setIdFriend(friendDTO.getIdFriend());
        friend.setFriend(friendDTO.getFriend());
        friend.setOwner(friendDTO.getOwner());
        friend.setCreationDate(friendDTO.getCreationDate());
        return friend;
        } else{
        return null;
        }
    }

    public FriendDTO constructFriendDTO(Friend friend) {
        FriendDTO friendDTO = new FriendDTO();
        if (friend !=null){
        friendDTO.setIdFriend(friend.getIdFriend());
        friendDTO.setCreationDate(friend.getCreationDate());
        friendDTO.setFriend(friend.getFriend());
        friendDTO.setOwner(friend.getOwner());
        return friendDTO;
        } else{
        return null;
        }
    }

   //------------------------------------------------------------------------------
}
