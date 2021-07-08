package com.oc.ly.PayMyBuddy.tool;

import java.util.regex.Pattern;

public class StringUtilsService {

    //-------------------------------------------------------------------------------------------------------
    public boolean checkStringName(String string) {

        Pattern stringNamePattern = Pattern.compile("[a-zA-Z\\+\\-\\+]{2,100}");
        return stringNamePattern.matcher(string).matches();
    }
    //-------------------------------------------------------------------------------------------------------

    public boolean checkStringEmail(String string) {
        Pattern stringNamePattern = Pattern.compile("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+");
        return stringNamePattern.matcher(string).matches();
    }
    //-------------------------------------------------------------------------------------------------------
}
