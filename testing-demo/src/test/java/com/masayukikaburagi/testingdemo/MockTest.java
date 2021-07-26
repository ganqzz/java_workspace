package com.masayukikaburagi.testingdemo;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MockTest {

    @Test
    public void testMock() {
        SimpleMath math = mock(SimpleMath.class);
        when(math.add(9, 5)).thenReturn(14);
        when(math.add(4, 15)).thenReturn(19);
//        System.out.println(math.getClass());
        int sum = math.add(9, 5);
//        System.out.println(sum);
        sum = math.add(4, 15);
//        System.out.println(sum);
    }

    // this test results in MockitoException
    @Test
    public void testBadDiff() {
        SimpleMath math = mock(SimpleMath.class);
        when(math.diff(4, 5)).thenReturn(-1);
        // do not use for Checked Exceptions
        when(math.diff(0, 0)).thenThrow(new RuntimeException("don't test with zeros"));
        int sum = math.add(0, 0);
    }

    @Test
    public void testAddIsCalled() {
        SimpleMath math = mock(SimpleMath.class);
//        when(math.add(4, 5)).thenReturn(9);

        MathUser user = new MathUser(math);
        user.doSomeMath();
        verify(math).add(4, 5); // このmethodが呼ばれたかどうかの検証
    }

    // this test fails verification
    @Test
    public void testVerifyFail() {
        SimpleMath math = mock(SimpleMath.class);
//        when(math.diff(4, 5)).thenReturn(-1);

        MathUser user = new MathUser(math);
        user.doSomeMath();
        verify(math).diff(4, 5); // このmethodが呼ばれたかどうかの検証
    }

    @Test
    public void testVerifyMultiplyCalled() {
        SimpleMath math = mock(SimpleMath.class);
        when(math.multiply(4, 5)).thenReturn(20); // fake method

        MathUser user = new MathUser(math);

        assertEquals(20, user.doSomeMultiply());
        verify(math).multiply(4, 5); // このmethodが呼ばれたかどうかの検証
    }

    @Test
    public void testHoge() {
        Hogerable hoge = mock(Hogerable.class);
        when(hoge.hoge()).thenReturn(10);

        int ret = hoge.hoge();

        assertEquals(10, ret);
        verify(hoge).hoge();
    }
}
