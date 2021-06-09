package com.oc.ly.PayMyBuddy.repository;

import com.oc.ly.PayMyBuddy.model.Transaction;
import com.oc.ly.PayMyBuddy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findAllByPayer(User payer);

    public Page<Transaction> findAllByPayer(User payer, Pageable pageable);
}
