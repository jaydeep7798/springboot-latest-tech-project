package org.example.controller;

import org.example.service.CalculatorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class calculatorJunitExampleController {

    @Autowired
    private CalculatorServiceImpl calculatorServiceImpl;

    @GetMapping("/add")
    public int add(@RequestParam int a, @RequestParam int b) {
        return calculatorServiceImpl.add(a, b);
    }

    @GetMapping("/subtract")
    public int subtract(@RequestParam int a, @RequestParam int b) {
        return calculatorServiceImpl.subtract(a, b);
    }

    @GetMapping("/multiply")
    public int multiply(@RequestParam int a, @RequestParam int b) {
        return calculatorServiceImpl.multiply(a, b);
    }

    @GetMapping("/divide")
    public double divide(@RequestParam int a, @RequestParam int b) {
        return calculatorServiceImpl.divide(a, b);
    }

}
