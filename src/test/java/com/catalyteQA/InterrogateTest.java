package com.catalyteQA;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class InterrogateTest {
    static WebDriver driver;

    @BeforeClass
    public static void createDriverAndVisitPage() {
        // instantiate the Chrome driver
        driver = new ChromeDriver();

        // tell the driver to navigate to the test page
        driver.navigate().to("http://localhost:3000/");

    }

    @Test //annotation allows the test runner know that this is a test
    public void interrogateDriver(){
        // assert that the title starts with Selenium
        assertEquals("Title is correct", driver.getCurrentUrl(), "http://localhost:3000/");
    }

    @Test //annotation allows the test runner know that this is a test
    public void interrogateDom(){
        // Find the web element for add todo
        WebElement id1 = driver.findElement(By.id("add-todo"));
        WebElement id2 = driver.findElement(By.cssSelector("#add-todo"));

        // assert the placeholder using By.id
        assertEquals(id1.getAttribute("placeholder"), "What needs to be done?" );

        // assert the placeholder using css selector
        assertEquals(id2.getAttribute("placeholder"), "What needs to be done?" );
    }

    @AfterClass
    public static void closeBrowser() {
        // quit the driver
        driver.close();
        driver.quit();
    }

}

