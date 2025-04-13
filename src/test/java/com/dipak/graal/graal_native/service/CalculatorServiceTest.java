package com.dipak.graal.graal_native.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {

    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        calculatorService = new CalculatorService();
    }

    @Test
    void calculate_validInput_shouldReturnResult() {
        ArrayList<String> numbers = new ArrayList<>();
        numbers.add("2");
        numbers.add("+");
        numbers.add("3");
        Float result = calculatorService.calculate(numbers);
        assertEquals(5f, result);
    }

    @Test
    void calculate_validInputWithMultipleOperators_shouldReturnResult() {
        ArrayList<String> numbers = new ArrayList<>();
        numbers.add("2");
        numbers.add("+");
        numbers.add("3");
        numbers.add("*");
        numbers.add("4");
        Float result = calculatorService.calculate(numbers);
        assertEquals(14f, result);
    }

    @Test
    void calculate_nullInput_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> calculatorService.calculate(null));
    }

    @Test
    void calculate_emptyInput_shouldThrowException() {
        ArrayList<String> numbers = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> calculatorService.calculate(numbers));
    }

    @Test
    void calculate_invalidNumber_shouldThrowException() {
        ArrayList<String> numbers = new ArrayList<>();
        numbers.add("2");
        numbers.add("+");
        numbers.add("a");
        assertThrows(IllegalArgumentException.class, () -> calculatorService.calculate(numbers));
    }

    @Test
    void calculate_invalidOperator_shouldThrowException() {
        ArrayList<String> numbers = new ArrayList<>();
        numbers.add("2");
        numbers.add("x");
        numbers.add("3");
        assertThrows(IllegalArgumentException.class, () -> calculatorService.calculate(numbers));
    }

    @Test
    void calculate_divisionByZero_shouldThrowException() {
        ArrayList<String> numbers = new ArrayList<>();
        numbers.add("2");
        numbers.add("/");
        numbers.add("0");
        assertThrows(ArithmeticException.class, () -> calculatorService.calculate(numbers));
    }
}