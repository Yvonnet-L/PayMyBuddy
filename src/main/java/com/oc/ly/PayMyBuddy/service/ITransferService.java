package com.oc.ly.PayMyBuddy.service;

import com.oc.ly.PayMyBuddy.model.Transfer;
import com.oc.ly.PayMyBuddy.model.User;

import java.util.List;

public interface ITransferService {

    public Transfer addFriend(Transfer  transfer);

    public Transfer updateFriend(Transfer  transfer);

    public Transfer deleteTransaction (Transfer  transfer);

    public List<Transfer > allTransfer();

    public List<Transfer > FindTransactionByUser (User user);
}
