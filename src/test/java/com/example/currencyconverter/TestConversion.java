package com.example.currencyconverter;

import org.junit.jupiter.api.BeforeAll;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TestConversion {

    @Mock
    static Conversion conversion;
    static ArrayList<Conversion> temp = new ArrayList<>();

    @BeforeAll
    public static void setUp() {
        temp.add(new Conversion(3.7,"usd", "ils"));
        temp.add(new Conversion(5.1, "jod", "ils"));
    }

    @Test
    public void testGetValue() {
        double expectedValue = 10.0;
        Mockito.when(conversion.getValue()).thenReturn(expectedValue);

        double actualValue = conversion.getValue();

        assertEquals(expectedValue, actualValue, 0.0);
    }

    @Test
    public void testGetFrom() {
        String expectedFrom = "USD";
        Mockito.when(conversion.getFrom()).thenReturn(expectedFrom);

        String actualFrom = conversion.getFrom();

        assertEquals(expectedFrom, actualFrom);
    }

    @Test
    public void testGetTo() {
        String expectedTo = "EUR";
        Mockito.when(conversion.getTo()).thenReturn(expectedTo);

        String actualTo = conversion.getTo();

        assertEquals(expectedTo, actualTo);
    }

    @Test
    public void checkingValues() {
        double temp1 = temp.get(0).getValue() * 50;
        double temp2 = temp.get(1).getValue() * 5;
        assertEquals(185, temp1);
        assertEquals(25.5, temp2);
    }

}
