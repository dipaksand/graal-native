package com.dipak.graal.graal_native.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CalculatorService {
    public Float calculate(ArrayList<String> numbers) {
        //validate the input
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("Input numbers cannot be null or empty");
        }
        float result = 0f;
        String[] operators = {"*", "/","-", "+"};
        ArrayList<String> temp = new ArrayList<>(numbers);
        //multiplication & division
        for (String operator : operators) {
            //check if the operator is present in the input
            if (temp.contains(operator)) {
                //perform the operation
                performOperation(temp, operator);
            }
        }
        //return the result
        if (temp.size() == 1) {
            result = Float.parseFloat(temp.get(0));
        } else {
            throw new IllegalArgumentException("Invalid input: " + numbers);
        }
        return result;
    }
    private void performOperation(ArrayList<String > numbers, String operator) {
        //perform all multiplication & division
        int length = numbers.size() - 1;
        while (length >= 2) {
            Float result = 0f;
            switch (numbers.get(length - 1)) {
                case "*":
                    result = Float.parseFloat(numbers.get(length - 2)) * Float.parseFloat(numbers.get(length));
                    break;
                case "/":
                    if(Float.parseFloat(numbers.get(length)) == 0) {
                        throw new ArithmeticException("Division by zero is not allowed");
                    }
                    result = Float.parseFloat(numbers.get(length - 2)) / Float.parseFloat(numbers.get(length));
                    break;
                case "+":
                    result = Float.parseFloat(numbers.get(length - 2)) + Float.parseFloat(numbers.get(length));
                    break;
                case "-":
                    result = Float.parseFloat(numbers.get(length - 2)) - Float.parseFloat(numbers.get(length));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operator: " + operator);
            }
            numbers.remove(length);
            numbers.remove(length - 1);
            numbers.remove(length - 2);
            numbers.add(length - 2, String.valueOf(result));
            length -= 2;
        }
    }
}
