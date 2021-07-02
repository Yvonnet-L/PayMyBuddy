package com.oc.ly.PayMyBuddy.security;


import com.oc.ly.PayMyBuddy.controller.HomeController;
import com.oc.ly.PayMyBuddy.model.MyUserDetails;
import com.oc.ly.PayMyBuddy.model.User;
import com.oc.ly.PayMyBuddy.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;



    private static Logger logger = LogManager.getLogger(HomeController.class);

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, DataAccessException {

        logger.info("---> Identifier verification: " + email);
        Optional<User> user = userRepository.findByEmail(email);

        user.orElseThrow(() -> new UsernameNotFoundException("Not Found: " + email));

        logger.info("---> User "+ email + " Find ");

        return user.map(MyUserDetails::new).get();
    }

}
/*
    Pour parametrer SpringSecurity avec la DB nous avons besoin des 4 classes soit:
    Bien sur le User, MyUserDetails qui fait le lien entre l'entity User et SrpingSecurity
    puis nous avons le service de ce dernier MyUserDetailService qui permet d'éttablir la recherche sur la DB
    et pour finir bien sur SecurityConfig indispensable pour configurer l'ensemble.

    Nous injectons l'email dans MyUserDetails grâce à " this.userName = user.getEmail(); " dans le constructeur (User)
    afin de persister dans le context notre clé unique.
 */