package com.masayukikaburagi.testingdemo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class SimpleMathTestBasic {

    private SimpleMath sm;

    @Before
    public void setup() {
        sm = new SimpleMath();
        System.out.println("ready for testing...");
    }

    @After
    public void tearDown() {
        System.out.println("done with unit test");
    }

    @BeforeClass
    public static void testClassSetup() {
        System.out.println("getting test class ready");
    }

    @AfterClass
    public static void testClassCleanup() {
        System.out.println("done with tests");
    }

    @Test
    public void testAdd() {
        sm = new SimpleMath();
        int total = sm.add(4, 5);
        assertEquals("Simple Math is not adding correctly", 9, total);
    }

    @Test
    public void testDiff() {
        sm = new SimpleMath();
        int total = sm.diff(9, 2);
        assertEquals("Simple Math is not subtracting correctly", 7, total);
    }

    @Test
    public void testDiv() {
        sm = new SimpleMath();
        double total = sm.div(9, 3);
        assertEquals("Simple Math is not dividing correctly", 3.0, total, 0.0);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivWithZeroDivisor() {
        sm = new SimpleMath();
        double total = sm.div(9, 0);
        assertEquals("Simple Math is not handling division by zero correctly", 0.0, total, 0.0);
    }
}
