package org.example.pagefactory;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class JiraCreateIssuePage extends BasePage {

    @FindBy(xpath = "//*[@id='project-field']")
    private WebElement projectField;

    @FindBy(xpath = "//*[@id='issuetype-field']")
    private WebElement issueTypeField;

    @FindBy(xpath = "//*[@id='issue-create-submit']")
    private WebElement nextBtn;

    @FindBy(xpath = "//*[@id='summary']")
    private WebElement summaryField;

    @FindBy(xpath = "//*[@id='issue-create-submit']")
    private WebElement createButton;

    public JiraCreateIssuePage() {
        super();
    }

    public void navigateToCreateIssuePage() {
        driver.get(baseUrl + "/secure/CreateIssue.jspa");
    }

    public void selectProject(String projectName) {
        wait.until(ExpectedConditions.elementToBeClickable(projectField)).click();
        projectField.sendKeys(projectName);
        projectField.sendKeys(Keys.ENTER);
    }

    public void selectIssueType(String issueType) {
        wait.until(ExpectedConditions.elementToBeClickable(issueTypeField)).click();
        issueTypeField.sendKeys(issueType);
        issueTypeField.sendKeys(Keys.ENTER);
    }

    public void clickNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(nextBtn)).click();
    }

    public void setSummary(String summaryText) {
        wait.until(ExpectedConditions.visibilityOf(summaryField));
        summaryField.sendKeys(summaryText);
        summaryField.sendKeys(Keys.ENTER);
    }

    public void clickCreateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();
    }
}
