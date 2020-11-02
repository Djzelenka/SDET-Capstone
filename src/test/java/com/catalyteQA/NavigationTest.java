package com.catalyteQA;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class NavigationTest {

    @Test //annotation allows the test runner know that this is a test
    public void navigate(){
        // instantiate the Firefox driver
        WebDriver driver = new ChromeDriver();

        // tell the driver to navigate to the test page
        driver.navigate().to("http://localhost:3000/");

        // assert that the title starts with Selenium
        assertEquals("Title is correct", driver.getTitle(), "TodoMVC - Test automation in Cypress");

        // quit the driver
        driver.close();
        driver.quit();
    }

}
