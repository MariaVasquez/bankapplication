package com.devsu.hackerearth.backend.account.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsu.hackerearth.backend.account.model.Transaction;

import feign.Param;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    @Query("SELECT t FROM Transaction t WHERE t.accountId = :accountId AND t.date BETWEEN :dateTransactionStart AND :dateTransactionEnd")
    List<Transaction> findTransactionsByAccountIdAndDateRange(
            @Param("accountId") Long accountId,
            @Param("dateTransactionStart") Date dateTransactionStart,
            @Param("dateTransactionEnd") Date dateTransactionEnd
    );

}
