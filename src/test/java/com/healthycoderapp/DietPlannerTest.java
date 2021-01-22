package com.healthycoderapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DietPlannerTest {

    private DietPlanner dietPlanner;

    @BeforeEach
    void init() {
        dietPlanner = new DietPlanner(20, 30, 50);
    }

    @AfterEach
    void afterEach() {
        System.out.println("A unit test was run");
    }

    @Test
    void itShouldCalculateDiet() {
        // Given
        Coder coder = new Coder(1.82, 75.0, 26, Gender.MALE);
        DietPlan dietPlanExpected = new DietPlan(2202, 110, 73, 275);
        // When
        DietPlan actual = dietPlanner.calculateDiet(coder);
        // Then
        assertAll(
                () -> assertEquals(dietPlanExpected.getCalories(), actual.getCalories()),
                () -> assertEquals(dietPlanExpected.getProtein(), actual.getProtein()),
                () -> assertEquals(dietPlanExpected.getFat(), actual.getFat()),
                () -> assertEquals(dietPlanExpected.getCarbohydrate(), actual.getCarbohydrate())

        );
    }
}