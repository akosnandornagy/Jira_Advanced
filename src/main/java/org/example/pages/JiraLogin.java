package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JiraLogin {
    private WebDriver driver;
    private WebDriverWait wait;
    By userNameJira = By.xpath("//input[@id='login-form-username']");
    By passwordJira = By.xpath("//input[@id='login-form-password']");
    By loginBtn = By.xpath("//input[@id='login']");
    By userIcon = By.xpath("//img[@alt='User profile for Auto Tester 52']");
    public JiraLogin(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameJira)).sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordJira)).sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
    }
    public WebElement getUserIcon() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(userIcon));
    }
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}