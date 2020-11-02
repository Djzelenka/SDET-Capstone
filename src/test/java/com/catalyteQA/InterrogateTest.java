package com.catalyteQA;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class InterrogateTest {

    @Test //annotation allows the test runner know that this is a test
    public void driverIsKing() {
        // instantiate the Firefox driver
        WebDriver driver = new FirefoxDriver();

        // tell the driver to navigate to the test page
        driver.navigate().to("https://testpages.herokuapp.com/");

        // assert that the title starts with Selenium
        Assert.assertTrue(driver.getTitle().startsWith("Selenium"));

        // quit the driver
        driver.quit();
    }
}
