package com.masayukikaburagi.testingdemo;

import com.masayukikaburagi.testingdemo.categories.FastTests;
import com.masayukikaburagi.testingdemo.categories.GoogleMapsIntegrationTests;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory({FastTests.class})
@Categories.ExcludeCategory({GoogleMapsIntegrationTests.class})
@Suite.SuiteClasses({SimpleMathTest.class, AnotherTest.class})
public class MyTestSuite {
}
