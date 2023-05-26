package org.example.pagetests;

import org.example.config.ConfigReader;
import org.example.pagefactory.JiraCreateIssuePage;
import org.example.pagefactory.JiraIssuePage;
import org.example.pagefactory.JiraLoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

public class JiraCreateIssueTests {

    private JiraCreateIssuePage jiraCreateIssue;
    private JiraIssuePage jiraIssuePage;
    private final String USERNAME = ConfigReader.getUsername();
    private final String PASSWORD = ConfigReader.getPassword();

    @BeforeEach
    public void setUp() {
        JiraLoginPage jiraLogin = new JiraLoginPage();
        jiraLogin.navigateToLoginPage();
        jiraLogin.login(USERNAME, PASSWORD);

        jiraCreateIssue = new JiraCreateIssuePage();
        jiraCreateIssue.navigateToCreateIssuePage();
        jiraIssuePage = new JiraIssuePage();

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/jiraSuccessfulCreateIssue.csv", numLinesToSkip = 1)
    public void testSuccessfulCreateIssues(String projectName, String issueType, String summary) {
        jiraCreateIssue.selectProject(projectName);
        jiraCreateIssue.selectIssueType(issueType);
        jiraCreateIssue.clickNextButton();
        jiraCreateIssue.setSummary(summary);
        jiraCreateIssue.clickCreateButton();

        assertTrue(jiraIssuePage.getProjectName().contains(projectName));
        assertEquals(issueType, jiraIssuePage.getIssueType());
        assertEquals(summary, jiraIssuePage.getSummary());
        assertEquals(ConfigReader.getReporterName(), jiraIssuePage.getReporter());
    }

    @Test
    void testCreateIssuesWithEmptySummary() {
        jiraCreateIssue.selectProject("Main Testing Project");
        jiraCreateIssue.selectIssueType("Task");
        jiraCreateIssue.clickNextButton();
        jiraCreateIssue.clickCreateButton();

        assertEquals("summary: You must specify a summary of the issue.", jiraCreateIssue.getErrorText());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/jiraCreateIssueTypesInProjects.csv", numLinesToSkip = 1)
    public void testCreateIssueTypesInProjects(String projectName, String issueType, String summary) {
        jiraCreateIssue.selectProject(projectName);
        jiraCreateIssue.selectIssueType(issueType);
        assertTrue(jiraCreateIssue.getProjectName().contains(projectName));
        assertEquals(issueType, jiraCreateIssue.getIssueType());

        jiraCreateIssue.clickNextButton();
        jiraCreateIssue.setSummary(summary);
        assertEquals(summary, jiraCreateIssue.getSummary());
    }

    @AfterEach
    public void tearDown() {
        jiraCreateIssue.quitDriver();
    }
}
