package com.oc.ly.PayMyBuddy.repository;

import com.oc.ly.PayMyBuddy.model.Transaction;
import com.oc.ly.PayMyBuddy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    public List<Transaction> findAllByPayer(User payer);

    public Page<Transaction> findAllByPayer(User payer, Pageable pageable);

    @Query("SELECT t FROM Transaction t WHERE t.payer= :user  ORDER BY date desc")
    Page<Transaction> theLastThreeTransactions(User user, Pageable pageable);

    @Query("SELECT t FROM Transaction t WHERE t.beneficiary= :user  ORDER BY date desc")
    Page<Transaction> theLastThreeTransactionsBeneficiary(User user,  Pageable pageable);
}
