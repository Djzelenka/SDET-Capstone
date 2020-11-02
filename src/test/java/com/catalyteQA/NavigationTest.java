package com.catalyteQA;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class NavigationTest {

    @Test //annotation allows the test runner know that this is a test
    public void driverIsKing(){
        // instantiate the Firefox driver
        WebDriver driver = new ChromeDriver();

        // tell the driver to navigate to the test page
        driver.navigate().to("https://testpages.herokuapp.com/");

        // assert that the title starts with Selenium
        assertTrue(driver.getTitle().startsWith("Selenium"));
        assertEquals("Title is correct", driver.getTitle(), "Selenium Test Pages");

        // quit the driver
        driver.close();
        driver.quit();
    }

}
