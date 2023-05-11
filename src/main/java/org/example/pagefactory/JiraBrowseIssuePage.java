package org.example.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JiraBrowseIssuePage extends BasePage{
    private final WebDriverWait wait;
    @FindBy(xpath = "//*[@id=\"issue-content\"]/div/div/h1")
    private WebElement errorMessage;
    @FindBy(xpath = "//a[@id='project-name-val']")
    private WebElement projectName;
    @FindBy(xpath = "//a[@id='key-val']")
    private WebElement issueId;

    public JiraBrowseIssuePage() {
        super();
        wait = new WebDriverWait(driver, 10);
    }
    public void navigateToUrlOfIssue(String url){
        driver.get(url);
    }
    public String errorMessage(){
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }
    public String getProjectName(){
        wait.until(ExpectedConditions.visibilityOf(projectName));
        return projectName.getText();
    }
    public String getIssueId(){
        wait.until(ExpectedConditions.visibilityOf(issueId));
        return issueId.getText();
    }
}
