package com.google.docs;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    @BeforeTest
    public void setup() {
        //need Chrome 64.0 for this version of chromedriver
        WebDriverManager.chromedriver().version("2.37").setup();
        Configuration.browser = "chrome";
    }

}
