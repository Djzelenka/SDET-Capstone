package com.catalyteQA;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class InterrogateTest {

    @Test //annotation allows the test runner know that this is a test
    public void interrogateDriver(){
        // instantiate the Firefox driver
        WebDriver driver = new ChromeDriver();

        // tell the driver to navigate to the test page
        driver.navigate().to("http://localhost:3000/");

        // assert that the title starts with Selenium
        assertEquals("Title is correct", driver.getCurrentUrl(), "http://localhost:3000/");

        // quit the driver
        driver.close();
        driver.quit();
    }

    @Test //annotation allows the test runner know that this is a test
    public void interrogateDom(){
        // instantiate the Firefox driver
        WebDriver driver = new ChromeDriver();

        // tell the driver to navigate to the test page
        driver.navigate().to("http://localhost:3000/");

        WebElement id1 = driver.findElement(By.id("add-todo"));
        assertEquals(id1.getAttribute("placeholder"), "What needs to be done?" );

        // assert that the title starts with Selenium
//        assertEquals("Title is correct", driver.getCurrentUrl(), "http://localhost:3000/");

        // quit the driver
        driver.close();
        driver.quit();
    }

}

