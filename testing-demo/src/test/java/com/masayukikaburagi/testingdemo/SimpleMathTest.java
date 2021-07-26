package com.masayukikaburagi.testingdemo;

import com.masayukikaburagi.testingdemo.categories.ContactIntegrationTests;
import com.masayukikaburagi.testingdemo.categories.FastTests;
import com.masayukikaburagi.testingdemo.categories.InAppPurchaseTests;
import com.masayukikaburagi.testingdemo.categories.SlowTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;

@Category(FastTests.class)
public class SimpleMathTest {

    SimpleMath sm;

    // Decorator pattern : @Before/@After/@BeforeClass/@AfterClassの代わりとして使う
    @Rule
    public ReportTestExecution exec = new ReportTestExecution();


    @Before
    public void setup() {
        sm = new SimpleMath();
//        // sysout to simulate call to Web service
//        System.out.println("Sending to Web service:  about to execute test method in SimpleMathTest.");
    }

//    @After
//    public void tearDown() {
//        // sysout to simulate call to Web service
//        System.out.println("Sending to Web service:  done executing test method in SimpleMathTest.");
//    }

    @Test
//    @Category(FastTests.class)
    public void testAdd() {
        assertEquals("SimpleMath addition not adding correctly", 9, sm.add(4, 5));
    }

    @Test
//    @Category(SlowTests.class)
    @Category({InAppPurchaseTests.class, ContactIntegrationTests.class})
    public void testDiff() {
        assertEquals("SimpleMath diff not subtracting correctly", 5, sm.diff(12, 7));
    }
}
