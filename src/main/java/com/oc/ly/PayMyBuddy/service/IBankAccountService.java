package com.oc.ly.PayMyBuddy.service;

import com.oc.ly.PayMyBuddy.model.BankAccount;
import com.oc.ly.PayMyBuddy.model.User;

import java.util.List;

public interface IBankAccountService {

    public List<BankAccount> findBankAccountByUser(User user);

    public void addAccount(String rib, User user);

    public void deleteAccount(Integer id);
}
