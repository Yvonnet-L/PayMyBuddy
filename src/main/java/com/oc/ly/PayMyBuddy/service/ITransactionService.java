package com.oc.ly.PayMyBuddy.service;

import com.oc.ly.PayMyBuddy.dto.FriendDTO;
import com.oc.ly.PayMyBuddy.model.Friend;
import com.oc.ly.PayMyBuddy.model.Transaction;
import com.oc.ly.PayMyBuddy.model.User;

import java.util.List;

public interface ITransactionService {


    public Transaction addFriend(Transaction  transaction);

    public Transaction  updateFriend(Transaction  transaction);

    public Transaction deleteTransaction (Transaction  transaction);

    public List<Transaction > allTransaction ();

    public List<Transaction > FindTransactionByPayer (User payer);
}
