package org.example.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class JiraProfilePage extends BasePage {

    @FindBy(xpath = "//dd[@id='up-d-username']")
    private WebElement usernameElement;

    public void navigateToProfilePage() {
        driver.get(baseUrl + "/ViewProfile.jspa");
    }

    public String getUsernameText() {
        WebElement usernameElementVisible = wait.until(ExpectedConditions.visibilityOf(usernameElement));
        return usernameElementVisible.getText();
    }
}
