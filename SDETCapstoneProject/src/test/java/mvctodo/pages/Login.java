package mvctodo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Login {

  private WebDriver driver;

  private static String PAGE_URL = "http://localhost:3000/login";

  @FindBy(tagName = "h1")
  WebElement heading;

  @FindBy(css = "input[type=email]")
  WebElement emailInput;

  @FindBy(css = "input[type=password]")
  WebElement passwordInput;

  @FindBy(css = "button[class=login-button]")
  WebElement submitButton;

  public Login(WebDriver driver) {
    this.driver = driver;
    driver.get(PAGE_URL);
    PageFactory.initElements(driver, this);
  }
  
  public void submit_login(String email, String password) {
    emailInput.sendKeys(email);
    passwordInput.sendKeys(password);
    submitButton.click();
  }
  
  public void hitSubmitButton() {
    submitButton.click();
  }
  
  public void submitLoginNoPassword(String email){
    emailInput.sendKeys(email);
    submitButton.click();
  }
  
  public void submiLoginNoEmail(String password){
    passwordInput.sendKeys(password);
    submitButton.click();
  }
  
}
