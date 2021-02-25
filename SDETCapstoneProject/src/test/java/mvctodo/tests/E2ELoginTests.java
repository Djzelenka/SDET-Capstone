package mvctodo.tests;

import static org.junit.Assert.assertEquals;

import mvctodo.pages.Login;
import mvctodo.pages.Signup;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class E2ELoginTests {

  static WebDriver driver;

  private static String PAGE_URL = "http://localhost:3000/login";
  //  private static String SEED_URL = "http://localhost:3000/accounts/seed";
  private static String SIGNUP_URL = "http://localhost:3000/signup";

  @BeforeClass
  public static void setUp() {

    System.setProperty("webdriver.chrome.driver",
        "C:\\Users\\dzelenka\\chromedriver_win32\\chromedriver.exe");
    driver = new ChromeDriver();
    driver.navigate().to(PAGE_URL);
  }

  @Test
  public void urlCheck() {
    WebElement heading = driver.findElement(By.cssSelector("h1"));

    assertEquals("Title is correct", driver.getCurrentUrl(), PAGE_URL);
    assertEquals(heading.getText(), "login");
  }

  @Test
  public void badUrlCheck() {
    driver.navigate().to("http://localhost:3000000/login");
    assertEquals(driver.getCurrentUrl(), PAGE_URL);
  }

  @Test
  public void loginWithBadCredentials() {
    Login login = new Login(driver);
    login.submit_login("invalid@email.com", "password1");
    WebElement errorMessage = driver.findElement(By.id("errorMessage"));
    assertEquals(driver.getCurrentUrl(), PAGE_URL);
    assertEquals(errorMessage.getAttribute("style"), "");
  }

  @Test
  public void loginGoodCredentials() {
    driver.navigate().to(SIGNUP_URL);
    Signup signup = new Signup(driver);
    signup.signupNoEmailConfirmation("valid@email.com", "password123");
    driver.navigate().to(PAGE_URL);
    Login login = new Login(driver);
    login.submit_login("valid@email.com", "password123");
    WebElement loginMessage = driver.findElement(By.id("loginMessage"));
    assertEquals(loginMessage.getAttribute("style"), "");
    assertEquals(driver.getCurrentUrl(), "http://localhost:3000/");
  }

  @Test
  public void loginNoCredentials() {
    Login login = new Login(driver);
    login.hitSubmitButton();
    WebElement errorMessage = driver.findElement(By.id("errorMessage"));
    assertEquals(driver.getCurrentUrl(), PAGE_URL);
    assertEquals(errorMessage.getAttribute("style"), "");
  }

  @Test
  public void LoginNoEmail() {
    Login login = new Login(driver);
    login.submiLoginNoEmail("password");
    WebElement errorMessage = driver.findElement(By.id("errorMessage"));
    assertEquals(driver.getCurrentUrl(), PAGE_URL);
    assertEquals(errorMessage.getAttribute("style"), "");
  }

  @Test
  public void LoginNoPassword() {
    Login login = new Login(driver);
    login.submitLoginNoPassword("email@email.com");
    WebElement errorMessage = driver.findElement(By.id("errorMessage"));
    assertEquals(driver.getCurrentUrl(), PAGE_URL);
    assertEquals(errorMessage.getAttribute("style"), "");

  }

  @After
  public void cleanup() {
    driver.close();
    driver.quit();
  }

}
