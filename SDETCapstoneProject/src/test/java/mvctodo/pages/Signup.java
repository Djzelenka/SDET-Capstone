package mvctodo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Signup {

  private WebDriver driver;

  private static String PAGE_URL = "http://localhost:3000/signup";

  @FindBy(tagName = "h1")
  WebElement header;

  @FindBy(css = "input[type=email]")
  WebElement emailInput;

  @FindBy(css = "input[type=password]")
  WebElement passwordInput;

  @FindBy(css = "button[class=signup-button]")
  WebElement signupButton;

  @FindBy(css = "input[type=checkbox]")
  WebElement emailCheckBox;

  public Signup(WebDriver driver) {
    this.driver = driver;
    driver.get(PAGE_URL);
    PageFactory.initElements(driver, this);
  }

  public void signupNoEmailConfirmation(String email, String password) {
    emailInput.sendKeys(email);
    passwordInput.sendKeys(password);
    signupButton.click();
  }
  
  public void signUpEmailConfirmation(String email, String password) {
    emailInput.sendKeys(email);
    passwordInput.sendKeys(password);
    emailCheckBox.click();
    signupButton.click();
  }
  
  public void signupNoEmail(String password) {
    passwordInput.sendKeys(password);
    signupButton.click();
  }
  
  public void signupNoPassword(String email) {
    emailInput.sendKeys(email);
    signupButton.click();
  }

}
