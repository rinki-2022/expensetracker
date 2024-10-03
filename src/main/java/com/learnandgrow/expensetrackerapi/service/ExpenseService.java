package com.learnandgrow.expensetrackerapi.service;

import com.learnandgrow.expensetrackerapi.entity.Expense;

import java.util.List;

public interface ExpenseService {

    List<Expense> getAllExpenses();

    Expense getExpenseById(Long id);

    void deleteExpenseById(Long id);

    Expense saveExpenseDetails(Expense expense);
}
