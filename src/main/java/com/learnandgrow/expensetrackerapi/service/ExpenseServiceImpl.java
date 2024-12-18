package com.learnandgrow.expensetrackerapi.service;

import com.learnandgrow.expensetrackerapi.entity.Expense;
import com.learnandgrow.expensetrackerapi.exceptions.ResourceNotFoundException;
import com.learnandgrow.expensetrackerapi.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserService userService;

    @Override
    public Page<Expense> getAllExpenses(Pageable pageable) {
        return expenseRepository.findByUserId(userService.getLoggedInUser().getId(), pageable);
    }

    @Override
    public Expense getExpenseById(Long id) {
        Optional<Expense> expense = expenseRepository.findByUserIdAndId(userService.getLoggedInUser().getId(), id);
        if (expense.isPresent()) {
            return expense.get();
        }
        throw new ResourceNotFoundException("Expense not found for the id : "+id);
    }

    @Override
    public void deleteExpenseById(Long id) {
        Expense expense = getExpenseById(id);
        expenseRepository.delete(expense);
    }

    @Override
    public Expense saveExpenseDetails(Expense expense) {
        expense.setUser(userService.getLoggedInUser());
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

    @Override
    public List<Expense> readByCategory(String category) {
        return expenseRepository.findByCategory(category);
    }

    @Override
    public List<Expense> readByName(String keyword, Pageable pageable) {
        return expenseRepository.findByNameContaining(keyword, pageable).toList();
    }

    @Override
    public List<Expense> readByDate(Date startDate, Date endDate, Pageable pageable) {
        if  (startDate == null) {
            startDate = new Date(0);
        }
        if (endDate == null) {
            endDate = new Date(System.currentTimeMillis());
        }
        return expenseRepository.findByDateBetween(startDate, endDate,pageable).toList();
    }


}
