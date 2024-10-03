package com.learnandgrow.expensetrackerapi.controller;

import com.learnandgrow.expensetrackerapi.entity.Expense;
import com.learnandgrow.expensetrackerapi.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/expenses")
    public List<Expense> getExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/expenses/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id);
    }

    @DeleteMapping("/expenses")
    public void deleteExpenseById(@RequestParam("id") Long id) {
        expenseService.deleteExpenseById(id);
    }

    @PostMapping("/expenses")
    public Expense saveExpenseDetails(@RequestBody Expense expense){
        return expenseService.saveExpenseDetails(expense);
    }

    @PutMapping("/expenses/{id}")
    public Expense updateExpenseDetails(@RequestBody Expense expense, @PathVariable Long id) {
        return expenseService.updateExpenseDetails(id, expense);
    }
}
