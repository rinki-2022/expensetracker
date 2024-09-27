package com.learnandgrow.expensetrackerapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseController {

    @GetMapping("/expenses")
    public String getExpenses() {
        return "List of all expenses";
    }
}
