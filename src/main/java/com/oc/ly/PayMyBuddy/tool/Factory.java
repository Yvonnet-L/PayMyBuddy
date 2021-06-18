package com.oc.ly.PayMyBuddy.tool;

import com.oc.ly.PayMyBuddy.dto.FriendDTO;
import com.oc.ly.PayMyBuddy.dto.TransactionDTO;
import com.oc.ly.PayMyBuddy.dto.UserDTO;
import com.oc.ly.PayMyBuddy.model.Friend;
import com.oc.ly.PayMyBuddy.model.Transaction;
import com.oc.ly.PayMyBuddy.model.User;
import com.oc.ly.PayMyBuddy.service.FriendServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public class Factory {

    private static Logger logger = LogManager.getLogger(Factory.class);

    LocalDate createDate = LocalDate.now();

    //----------  USER -----------------------------------------------------
    public UserDTO constructUserDTO(UserDTO usDTO, User u) {
        if(u != null) {
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
        }else {
            return null;
        }
    }

    public User constructUser(UserDTO usDTO, User user) {
        if(usDTO != null) {
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

  /*
   //----------  BANK -----------------------------------------------------

    public Bank constructBank(BankDTO bankDTO, Bank bank) {
        bank.setBankName(bankDTO.getBankName());
        bank.setCodeBank(bankDTO.getCodeBank());
        return bank;
    }

    public BankDTO constructBankDTO(BankDTO bankDTO, Bank bank) {
        bankDTO.setIdBank(bank.getIdBank());
        bankDTO.setBankName(bank.getBankName());
        bankDTO.setCodeBank(bank.getCodeBank());
        return bankDTO;
    }

    //----------  TRANSFER -----------------------------------------------------

    public Transfer constructTransfer(TransferDTO transferDTO, Transfer transfer) {
        transfer.setUser(transferDTO.getUser());
        transfer.setIdBank(transferDTO.getIdBank());
        transfer.setCreateDate(createDate);
        transfer.setAmount(transferDTO.getAmount());
        transfer.setType(transferDTO.getType().toString());
        return transfer;
    }

    public TransferDTO constructTransferDTO(TransferDTO transferDTO, Transfer transfer) {
        transferDTO.setIdTransfer(transfer.getIdTransfer());
        transferDTO.setUser(transfer.getUser());
        transferDTO.setIdBank(transfer.getIdBank());
        transferDTO.setCreateDate(transfer.getCreateDate());
        transferDTO.setAmount(transfer.getAmount());
        transferDTO.setType(TransferType.valueOf(transfer.getType()));
        return transferDTO;
    }
*/
    //----------  TRANSACTION -----------------------------------------------------

    public Transaction constructTransaction(TransactionDTO transactionDTO, Transaction transaction) {
        transaction.setIdTransaction(transactionDTO.getIdTransaction());
        transaction.setCreationDate(transactionDTO.getCreationDate());
        transaction.setBeneficiary(transactionDTO.getBeneficiary());
        transaction.setPayer(transactionDTO.getPayer());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setDescription(transactionDTO.getDescription());
        transaction.setFee(transactionDTO.getFee());
        return transaction;
    }



    public TransactionDTO constructTransactionDTO(TransactionDTO transactionDTO, Transaction transaction) {
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

    public Friend constructFriend(FriendDTO friendDTO, Friend friend) {
        friend.setIdFriend(friendDTO.getIdFriend());
        friend.setFriend(friendDTO.getFriend());
        friend.setOwner(friendDTO.getOwner());
        friend.setCreationDate(friendDTO.getDateCreation());
        return friend;
    }

    public FriendDTO constructFriendDTO(FriendDTO friendDTO, Friend friend) {
        friendDTO.setIdFriend(friend.getIdFriend());
        friendDTO.setDateCreation(friend.getCreationDate());
        friendDTO.setFriend(friend.getFriend());
        friendDTO.setOwner(friend.getOwner());
        return friendDTO;
    }

   //------------------------------------------------------------------------------
}
