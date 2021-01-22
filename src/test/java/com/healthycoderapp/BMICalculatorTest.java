package com.healthycoderapp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BMICalculatorTest {

    @ParameterizedTest(name = "weight{0}, height{1}")
    @CsvFileSource(resources = {"/diet-recommended-input-data.csv"}, numLinesToSkip = 1)
    void itShouldIsDietRecommended(Double cWeight, Double cHeight) {
        // Given
        double weight = cWeight;
        double height = cHeight;

        // When
        boolean recommended = BMICalculator.isDietRecommended(weight, height);

        // Then
        assertTrue(recommended);
    }

    @Test
    @DisplayName("Should_Throw_Exception")
    void itShouldThrowExceptionWhenHeightIsZero() {

        double weight = 89.0;
        double height = 0.0;

        assertThrows(ArithmeticException.class,
                () -> assertTrue(BMICalculator.isDietRecommended(weight, height)));
    }

    @Test
    void itShould_FindCoderWithWorstBMI_When_ListNotEmpty() {
        // Given
        List<Coder> coders = List.of(
                new Coder(1.90, 78.0),
                new Coder(1.70, 90.0),
                new Coder(1.90, 95.0)
        );
        // When
        Coder c = BMICalculator.findCoderWithWorstBMI(coders);
        // Then
        assertAll(
                () -> assertEquals(1.70, c.getHeight()),
                () -> assertEquals(90.0, c.getWeight())
        );

    }

    @Test
    void itShould_Return_Null_IfEmptyList() {
        List<Coder> coders = new ArrayList<>();
        // When
        Coder c = BMICalculator.findCoderWithWorstBMI(coders);
        // Then
        assertNull(c);
    }

    @Test
    void itShould_Return_CorrectBMIScoreArray_When_NotEmpty() {
        List<Coder> coders = List.of(
                new Coder(1.80, 60.0),
                new Coder(1.82, 98.0),
                new Coder(1.82, 64.7)
        );
        double[] scoreExpected = {18.52, 29.59, 19.53};
        double[] scoreActual = BMICalculator.getBMIScores(coders);

        assertArrayEquals(scoreExpected, scoreActual);
    }
}