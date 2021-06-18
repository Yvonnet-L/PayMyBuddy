package com.oc.ly.PayMyBuddy.dto;

import com.oc.ly.PayMyBuddy.model.User;

import java.time.LocalDate;

public class TransferDTO {

    private int idTransfer;
    private User user;
    private String rib;
    private LocalDate createDate;
    private double amount;
    private String type;
}
