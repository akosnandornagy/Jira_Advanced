package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JiraProfile {
    private final WebDriverWait wait;
    private final By username = By.xpath("//dd[@id='up-d-username']");

    public JiraProfile(WebDriver driver) {
        this.wait = new WebDriverWait(driver, 10);
    }

    public String getUsernameText() {
        WebElement usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(username));
        return usernameElement.getText();
    }
}