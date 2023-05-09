package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JiraLogin {
    private final WebDriverWait wait;
    private final By username = By.xpath("//input[@id='login-form-username']");
    private final By password = By.xpath("//input[@id='login-form-password']");
    private final By loginSubmitBtn = By.xpath("//input[@id='login-form-submit']");
    private final By userIcon = By.xpath("//img[contains(@alt, 'User profile for Auto Tester')]");
    private final By errorMessage = By.xpath("//div[contains(@class, 'aui-message-error')]/child::p");
    private final By loginLink = By.xpath("//li[@id='user-options']/child::a[@class='aui-nav-link login-link']");
    private final By userProfile = By.xpath("//a[@id='view_profile']");
    public JiraLogin(WebDriver driver) {
        this.wait = new WebDriverWait(driver, 10);
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.username)).sendKeys(username);
    }
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.password)).sendKeys(password);
    }
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginSubmitBtn)).click();
    }
    public WebElement getUserIcon() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(userIcon));
    }
    public WebElement getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
    }
    public WebElement getLoginLink() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginLink));
    }
    public void clickUserIcon() {
        WebElement userIconElement = wait.until(ExpectedConditions.visibilityOfElementLocated(userIcon));
        userIconElement.click();
    }
    public void clickUserProfile() {
        WebElement userProfileElement = wait.until(ExpectedConditions.visibilityOfElementLocated(userProfile));
        userProfileElement.click();
    }
    public void login(String username, String password) {
        enterUsername(username);

        if (password != null && !password.isEmpty()) {
            enterPassword(password);
        } else {
            WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(this.password));
            passwordInput.clear();
        }

        clickLoginButton();
    }
}