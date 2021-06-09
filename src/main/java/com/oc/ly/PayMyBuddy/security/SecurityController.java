package com.oc.ly.PayMyBuddy.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class SecurityController {

    @RequestMapping(value = { "/notAuthorized" }, method = RequestMethod.GET)
    public String error(Model model)
    {
        return "notAuthorized";
    }
}
