package com.zerobase.demo.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/finance")
public class FinanceController {

    @GetMapping("/dividend/{companyName}")
    public ResponseEntity<?> serchFinance(@PathVariable String companyName) {
        return null;
    }
}
