package com.oc.ly.PayMyBuddy.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class WalletNotEnoughException extends RuntimeException{

    private static Logger logger = LogManager.getLogger(DataNotFoundException.class);

    public WalletNotEnoughException(String message) {
        super(message);
        logger.error("--> " + message);
    }
}
