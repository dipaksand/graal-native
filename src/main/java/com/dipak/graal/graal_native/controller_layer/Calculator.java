package com.dipak.graal.graal_native.controller_layer;

import com.dipak.graal.graal_native.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class Calculator {
    @Autowired
    public CalculatorService calculatorService;

    @GetMapping("/calculateDefault")
    public Float calculateDefault() {
        ArrayList<String> numbers = new ArrayList<>();
        numbers.add("1");
        numbers.add("+");
        numbers.add("2");
        numbers.add("*");
        numbers.add("3");
        numbers.add("/");
        numbers.add("4");
        numbers.add("-");
        numbers.add("5");
        numbers.add("+");
        numbers.add("6");
        return calculatorService.calculate(numbers);
    }
    @PostMapping("/calculate")
    public Float calculate(ArrayList<String> numbers) {
        //validate the input
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("Input numbers cannot be null or empty");
        }
        //ensure there is a mathematical operator between each number
        for (int i = 0; i < numbers.size(); i++) {
            if (i % 2 == 0) {
                try {
                    Float.parseFloat(numbers.get(i));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid number: " + numbers.get(i));
                }
            } else {
                if (!isOperator(numbers.get(i))) {
                    throw new IllegalArgumentException("Invalid operator: " + numbers.get(i));
                }
            }
        }
        return calculatorService.calculate(numbers);
    }

    private boolean isOperator(String number) {
        return number.equals("+") || number.equals("-") || number.equals("*") || number.equals("/");
    }
}
