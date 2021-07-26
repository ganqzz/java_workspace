package com.masayukikaburagi.testingdemo;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import static org.junit.Assert.assertEquals;

public class SimpleMathWithTestName {

    @Rule
    public TestName name = new TestName();

    @Test
    public void testAdd() {
        SimpleMath sm = new SimpleMath();
        int total = sm.add(4, 5);
        assertEquals(name.getMethodName() + " is not adding correctly", 9, total);
    }

    @Test
    public void testDiff() {
        SimpleMath sm = new SimpleMath();
        int total = sm.diff(12, 7);
        assertEquals(name.getMethodName() + " is not subtracting correctly", 5, total);
    }
}
