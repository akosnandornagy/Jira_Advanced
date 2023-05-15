package org.example.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class JiraLogoutPage extends BasePage {
    @FindBy(xpath = "//input[@id='confirm-logout-submit']")
    private WebElement logoutBtn;
    @FindBy(xpath = "//p[@class='title']")
    private WebElement logoutMessage;
    @FindBy(xpath = "//a[@href='/login.jsp']")
    private WebElement loginIcon;

    public void navigateToLogoutPage() {
        driver.get(baseUrl + "/secure/Logout!default.jspa");
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