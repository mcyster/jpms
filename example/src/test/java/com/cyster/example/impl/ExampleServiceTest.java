package com.cyster.example.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ExampleServiceTest {

    @Test
    public void testOneRequiredAttribute() {
        assertTrue(new ExampleServiceImpl("456").getMessage().contains("456"), "No 456!");
    }
}
