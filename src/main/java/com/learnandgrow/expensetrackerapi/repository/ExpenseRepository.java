package com.learnandgrow.expensetrackerapi.repository;

import com.learnandgrow.expensetrackerapi.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByCategory(String category);
    Page<Expense> findByNameContaining(String keyword, Pageable pageable);
    Page<Expense> findByDateBetween(Date startDate, Date endDate,Pageable pageable);
    Page<Expense> findByUserId(Long userId,Pageable pageable);
    Optional<Expense> findByUserIdAndId(Long userId, Long expenseId);

}
