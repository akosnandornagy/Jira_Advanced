package org.example.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class JiraIssuePage extends BasePage {

    @FindBy(xpath = "//*[@id='project-name-val']")
    private WebElement projectName;

    @FindBy(xpath = "//*[@id='type-val']")
    private WebElement issueType;

    @FindBy(xpath = "//*[@id='summary-val']")
    private WebElement summary;

    @FindBy(xpath = "//*[contains(@id, 'issue_summary_reporter_')]")
    private WebElement reporter;

    @FindBy(xpath = "//*[@id='opsbar-operations_more']")
    private WebElement moreDropdown;

    @FindBy(xpath = "//*[@id='delete-issue']")
    private WebElement deleteLink;

    @FindBy(xpath = "//*[@id='delete-issue-submit']")
    private WebElement deleteIssueSubmit;

    @FindBy(xpath = "//div[@id='aui-flag-container']//div[contains(@class, 'aui-message-success')")
    private WebElement successMessage;

    public String getProjectName() {
        return projectName.getText();
    }

    public String getIssueType() {
        return issueType.getText();
    }

    public String getSummary() {
        return summary.getText();
    }

    public String getReporter() {
        return reporter.getText();
    }

    public void deleteIssue() {
        wait.until(ExpectedConditions.elementToBeClickable(moreDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(deleteLink)).click();
        wait.until(ExpectedConditions.elementToBeClickable(deleteIssueSubmit)).click();
    }
}

