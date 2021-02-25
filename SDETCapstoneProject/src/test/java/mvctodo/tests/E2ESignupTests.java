package mvctodo.tests;

import static org.junit.Assert.assertEquals;

import mvctodo.pages.Signup;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class E2ESignupTests {

  static WebDriver driver;

  private static String SIGNUP_URL = "http://localhost:3000/signup";

  @BeforeClass
  public static void setUp() {

    System.setProperty("webdriver.chrome.driver",
        "C:\\Users\\dzelenka\\chromedriver_win32\\chromedriver.exe");
    driver = new ChromeDriver();
    driver.navigate().to(SIGNUP_URL);
  }

  @Test
  public void URLCheck() {
    WebElement heading = driver.findElement(By.cssSelector("h1"));
    assertEquals(driver.getCurrentUrl(), SIGNUP_URL);
    assertEquals(heading.getText(), "signup");
  }

  @Test
  public void BadUrlCheck() {
    assertEquals(driver.getCurrentUrl(), "http://localhost:300000/signup");
  }

  @Test
  public void signupValidCredentials() {
    Signup signup = new Signup(driver);
    signup.signupNoEmailConfirmation("validEmail@etest.com", "password123");
    WebElement loginMessage = driver.findElement(By.id("loginMessage"));
    assertEquals(driver.getCurrentUrl(), "http://localhost:3000/");
    assertEquals(loginMessage.getAttribute("style"), "");
  }

  @Test
  public void signupEmailChecked() {
    Signup signup = new Signup(driver);
    signup.signUpEmailConfirmation("testEmail@testing.com", "password");
    WebElement loginMessage = driver.findElement(By.id("loginMessage"));
    assertEquals(driver.getCurrentUrl(), "http://localhost:3000/");
    assertEquals(loginMessage.getAttribute("style"), "");
  }

//  @Test
//  public void signupValidCredentialsEmailCheck() {
//    Signup signup = new Signup(driver);
//    signup.signUpEmailConfirmation("newEmail@test.com", "password");
//    VerifyEmail.verifyMail("newEmail@test.com", "password","")
//  }

  @Test
  public void invalidCredentials() {
    Signup signup = new Signup(driver);
    signup.signupNoEmailConfirmation("asdf3", "password");
    WebElement errorMessage = driver.findElement(By.id("errorMessage"));
    assertEquals(driver.getCurrentUrl(), SIGNUP_URL);
    assertEquals(errorMessage.getAttribute("style"), "");
  }

  @Test
  public void signupNoEmail() {
    Signup signup = new Signup(driver);
    signup.signupNoEmail("password");
    WebElement errorMessage = driver.findElement(By.id("errorMessage"));
    assertEquals(driver.getCurrentUrl(), SIGNUP_URL);
    assertEquals(errorMessage.getAttribute("style"), "");
  }

  @Test
  public void signupNoPassword() {
    Signup signup = new Signup(driver);
    signup.signupNoPassword("email@email.com");
    WebElement errorMessage = driver.findElement(By.id("errorMessage"));
    assertEquals(driver.getCurrentUrl(), SIGNUP_URL);
    assertEquals(errorMessage.getAttribute("style"), "");
  }

  @Test
  public void signupWithSameEmail() {
    Signup signup = new Signup(driver);
    signup.signupNoEmailConfirmation("TestEmail@Test.com", "password");
    driver.navigate().to(SIGNUP_URL);
    signup.signupNoEmailConfirmation("TestEmail@Test.com", "password");
    WebElement errorMessage = driver.findElement(By.id("errorMessage"));
    assertEquals(driver.getCurrentUrl(), SIGNUP_URL);
    assertEquals(errorMessage.getAttribute("style"), "");
  }

  @After
  public void cleanup() {
    driver.close();
    driver.quit();
  }
}
