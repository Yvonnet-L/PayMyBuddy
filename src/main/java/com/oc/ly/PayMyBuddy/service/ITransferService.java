package com.oc.ly.PayMyBuddy.service;

import com.oc.ly.PayMyBuddy.model.Transfer;
import com.oc.ly.PayMyBuddy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITransferService {

    public Transfer addFriend(Transfer  transfer);

    public Transfer updateFriend(Transfer  transfer);

    public Transfer deleteTransfer (Transfer  transfer);

    public List<Transfer > allTransfer();

    public Transfer addTransfer(String rib, String type, double amount, User user);

    public List<Transfer > FindTransferByUser (User user);

    public Page<Transfer> findAllByUser(User user, Pageable pageable);


}
