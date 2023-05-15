package org.example.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class JiraLoginPage extends BasePage {

    @FindBy(xpath = "//input[@id='login-form-username']")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@id='login-form-password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@id='login-form-submit']")
    private WebElement loginSubmitBtn;

    @FindBy(xpath = "//img[contains(@alt, 'User profile for Auto Tester')]")
    private WebElement userIcon;

    @FindBy(xpath = "//div[contains(@class, 'aui-message-error')]/child::p")
    private WebElement errorMessage;

    @FindBy(xpath = "//li[@id='user-options']/child::a[@class='aui-nav-link login-link']")
    private WebElement loginLink;

    @FindBy(xpath = "//a[@id='view_profile']")
    private WebElement userProfile;

    public void navigateToLoginPage() {
        driver.get(baseUrl + "/login.jsp");
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameInput)).sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInput)).sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginSubmitBtn)).click();
    }

    public WebElement getUserIcon() {
        return wait.until(ExpectedConditions.visibilityOf(userIcon));
    }

    public WebElement getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(errorMessage));
    }

    public WebElement getLoginLink() {
        return wait.until(ExpectedConditions.elementToBeClickable(loginLink));
    }

    public void clickUserIcon() {
        WebElement userIconElement = wait.until(ExpectedConditions.elementToBeClickable(userIcon));
        userIconElement.click();
    }

    public void clickUserProfile() {
        WebElement userProfileElement = wait.until(ExpectedConditions.elementToBeClickable(userProfile));
        userProfileElement.click();
    }

    public void login(String username, String password) {
        enterUsername(username);

        if (password != null && !password.isEmpty()) {
            enterPassword(password);
        } else {
            WebElement passwordInput = wait.until(ExpectedConditions.visibilityOf(this.passwordInput));
            passwordInput.clear();
        }

        clickLoginButton();
    }
}