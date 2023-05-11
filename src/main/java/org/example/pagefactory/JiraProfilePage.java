package org.example.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JiraProfilePage extends BasePage {
    private final WebDriverWait wait;

    @FindBy(xpath = "//dd[@id='up-d-username']")
    private WebElement usernameElement;

    public JiraProfilePage() {
        super();
        wait = new WebDriverWait(driver, 10);
    }

    public String getUsernameText() {
        WebElement usernameElementVisible = wait.until(ExpectedConditions.visibilityOf(usernameElement));
        return usernameElementVisible.getText();
    }
}
