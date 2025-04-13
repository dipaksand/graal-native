package com.dipak.graal.graal_native.controller_layer;

import com.dipak.graal.graal_native.service.CalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class CalculatorTest {

    @Mock
    private CalculatorService calculatorService;

    @InjectMocks
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculate_validInput_shouldReturnResult() {
        ArrayList<String> numbers = new ArrayList<>();
        numbers.add("2");
        numbers.add("+");
        numbers.add("3");
        Float expected = 5f;
        // Mock the service call if needed
         when(calculatorService.calculate(numbers)).thenReturn(expected);

        Float result = calculator.calculate(numbers);
        assertEquals(expected, result);
    }

    @Test
    void calculate_nullInput_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(null));
    }

    @Test
    void calculate_emptyInput_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(new ArrayList<>()));
    }

    @Test
    void calculate_invalidNumber_shouldThrowException() {
        ArrayList<String> numbers = new ArrayList<>();

        numbers.add("2");
        numbers.add("+");
        numbers.add("a");
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(numbers));
    }

    @Test
    void calculate_invalidOperator_shouldThrowException() {
        ArrayList<String> numbers = new ArrayList<>();

        numbers.add("2");
        numbers.add("x");
        numbers.add("3");
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(numbers));
    }
}