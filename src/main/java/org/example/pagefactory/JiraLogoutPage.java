package org.example.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JiraLogoutPage extends BasePage {
    private final WebDriverWait wait;
    @FindBy(xpath = "//img[@alt='User profile for Auto Tester 49']")
    private WebElement userIcon;
    @FindBy(xpath = "//a[@id='log_out']")
    private WebElement logoutBtn;
    @FindBy(xpath = "//p[@class='title']")
    private WebElement logoutMessage;
    @FindBy(xpath = "//a[@href='/login.jsp']")
    private WebElement loginIcon;
    public JiraLogoutPage() {
        super();
        wait = new WebDriverWait(driver, 10);
    }
    public void clickUserIcon(){
        wait.until(ExpectedConditions.visibilityOf(userIcon)).click();
    }
    public void clickLogoutBtn(){
        wait.until(ExpectedConditions.visibilityOf(logoutBtn)).click();
    }
    public void getLogoutMessage(){
       String logoutMsg = wait.until(ExpectedConditions.visibilityOf(logoutMessage)).getText();
       assertEquals("You are now logged out. Any automatic login has also been stopped.", logoutMsg);
    }
    public void checkLoginIcon(){
        wait.until(ExpectedConditions.visibilityOf(loginIcon));
    }
}