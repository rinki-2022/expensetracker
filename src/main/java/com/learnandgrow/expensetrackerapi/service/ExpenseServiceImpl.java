package com.learnandgrow.expensetrackerapi.service;

import com.learnandgrow.expensetrackerapi.entity.Expense;
import com.learnandgrow.expensetrackerapi.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public Page<Expense> getAllExpenses(Pageable pageable) {
        return expenseRepository.findAll(pageable);
    }

    @Override
    public Expense getExpenseById(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        if (expense.isPresent()) {
            return expense.get();
        }
        throw new RuntimeException("Expense not found for the id : "+id);
    }

    @Override
    public void deleteExpenseById(Long id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public Expense saveExpenseDetails(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public Expense updateExpenseDetails(Long id, Expense expense) {
        Expense existingExpenseDetails = getExpenseById(id);
        existingExpenseDetails.setName(expense.getName() != null ? expense.getName() : existingExpenseDetails.getName());
        existingExpenseDetails.setDescription(expense.getDescription() != null ? expense.getDescription() : existingExpenseDetails.getDescription());
        existingExpenseDetails.setCategory(expense.getCategory() != null ? expense.getCategory() : existingExpenseDetails.getCategory());
        existingExpenseDetails.setAmount(expense.getAmount() != null ? expense.getAmount() : existingExpenseDetails.getAmount());
        existingExpenseDetails.setDate(expense.getDate() != null ? expense.getDate() : existingExpenseDetails.getDate());

        return expenseRepository.save(existingExpenseDetails);
    }
}
