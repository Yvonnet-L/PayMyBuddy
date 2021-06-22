package com.oc.ly.PayMyBuddy.service;

import com.oc.ly.PayMyBuddy.dto.BankAccountDTO;
import com.oc.ly.PayMyBuddy.dto.UserDTO;
import com.oc.ly.PayMyBuddy.model.BankAccount;
import com.oc.ly.PayMyBuddy.model.User;

import java.util.List;

public interface IBankAccountService {

    public List<BankAccountDTO> findBankAccountByUser(UserDTO userDTO);

    public void addAccount(String rib, UserDTO userDTO);

    public void deleteAccount(Integer id);
}
