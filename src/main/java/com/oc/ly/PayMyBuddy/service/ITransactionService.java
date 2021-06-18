package com.oc.ly.PayMyBuddy.service;

import com.oc.ly.PayMyBuddy.dto.FriendDTO;
import com.oc.ly.PayMyBuddy.dto.TransactionDTO;
import com.oc.ly.PayMyBuddy.model.Friend;
import com.oc.ly.PayMyBuddy.model.Transaction;
import com.oc.ly.PayMyBuddy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITransactionService {


    public TransactionDTO addTransaction(TransactionDTO  transactionDTO);

    public Transaction  updateTransaction(Transaction  transaction);

    public Transaction deleteTransaction (Transaction  transaction);

    public Transaction deleteById ( int id );

    public List<Transaction > allTransaction ();

    public List<Transaction > FindTransactionByPayer (User payer);

    Page<Transaction> theLastThreeTransactions(User user, Pageable pageable);

    public Page<Transaction> findAllByPayer(User payer, Pageable pageable);
}
