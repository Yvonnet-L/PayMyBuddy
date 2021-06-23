package com.oc.ly.PayMyBuddy.service;

import com.oc.ly.PayMyBuddy.dto.TransferDTO;
import com.oc.ly.PayMyBuddy.dto.UserDTO;
import com.oc.ly.PayMyBuddy.model.Transfer;
import com.oc.ly.PayMyBuddy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITransferService {

    public TransferDTO addTransfer(String rib, String type, double amount, User user);

    public Page<TransferDTO> findAllByUser(UserDTO userDTO, Pageable pageable);

    public Page<TransferDTO> theLastThreeTransfers(UserDTO userDTO, Pageable pageable);


}
