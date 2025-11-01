package org.example.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest  //It is used to create and load the full Spring application context for integration testing — meaning it tests how multiple components (controllers, services, repositories, etc.)
// work together in a Spring Boot application.
class CalculatorServiceImplSpringTest {

    @Autowired
    private CalculatorServiceImpl calculatorService;

    @Test // Unit Testing
    void testAdd() {
        assertEquals(8, calculatorService.add(3, 5));
    }

    @Test
    void testSubtract() {
        assertEquals(2, calculatorService.subtract(7, 5));
    }

    @Test
    void testMultiply() {
        assertEquals(20, calculatorService.multiply(4, 5));
    }

    @Test
    void testDivide() {
        assertEquals(2.5, calculatorService.divide(5, 2));
    }

    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calculatorService.divide(5, 0));
    }
}
