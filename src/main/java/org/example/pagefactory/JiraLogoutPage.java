package org.example.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class JiraLogoutPage extends BasePage {
    @FindBy(xpath = "//img[contains(@alt, 'User profile for Auto Tester')]")
    private WebElement userIcon;
    @FindBy(xpath = "//a[@id='log_out']")
    private WebElement logoutBtn;
    @FindBy(xpath = "//p[@class='title']")
    private WebElement logoutMessage;
    @FindBy(xpath = "//a[@href='/login.jsp']")
    private WebElement loginIcon;
    public void clickUserIcon(){
        wait.until(ExpectedConditions.visibilityOf(userIcon)).click();
    }
    public void clickLogoutBtn(){
        wait.until(ExpectedConditions.visibilityOf(logoutBtn)).click();
    }
    public String getLogoutMessage() {
        return wait.until(ExpectedConditions.visibilityOf(logoutMessage)).getText();
    }
    public void checkLoginIcon(){
        wait.until(ExpectedConditions.visibilityOf(loginIcon));
    }
}