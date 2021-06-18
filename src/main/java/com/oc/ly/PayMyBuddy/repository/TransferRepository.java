package com.oc.ly.PayMyBuddy.repository;

import com.oc.ly.PayMyBuddy.model.Transaction;
import com.oc.ly.PayMyBuddy.model.Transfer;
import com.oc.ly.PayMyBuddy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer>{

    public List<Transfer> findAllByUser(User user);

    public Page<Transfer> findAllByUser(User user, Pageable pageable);

    @Query("SELECT t FROM Transfer t WHERE t.user= :user  ORDER BY date desc")
    Page<Transfer> theLastThreeTransfers(User user, Pageable pageable);
}
