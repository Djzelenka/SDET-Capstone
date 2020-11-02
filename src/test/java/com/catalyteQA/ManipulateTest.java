package com.catalyteQA;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;


public class ManipulateTest {
    static WebDriver driver;

    @BeforeClass
    public static void createDriverAndVisitPage() {
        // instantiate the Chrome driver
        driver = new ChromeDriver();

        // tell the driver to navigate to the test page
        driver.navigate().to("http://localhost:3000/");
    }

    @Test //annotation allows the test runner know that this is a test
    public void manipulate(){
        WebElement id2 = driver.findElement(By.cssSelector("#add-todo"));
        id2.sendKeys("Homework");
        id2.sendKeys(Keys.ENTER);

        // assert that Homework is in the todo list
        assertEquals("Homework is not found in the list", driver.findElement(By.cssSelector(".view")).getText(), "Homework");
    }

    @AfterClass
    public static void closeBrowser() {
        // quit the driver
        driver.close();
        driver.quit();
    }

}
