package org.example.pagetests;

import org.example.config.ConfigReader;
import org.example.pagefactory.JiraIssuePage;
import org.example.pagefactory.JiraLoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JiraBrowseIssueTests {
    private JiraIssuePage jiraBrowseIssues;
    private final String USERNAME = ConfigReader.getUsername();
    private final String PASSWORD = ConfigReader.getPassword();

    @BeforeEach
    public void setUp() {
        JiraLoginPage jiraLogin = new JiraLoginPage();
        jiraLogin.navigateToLoginPage();
        jiraLogin.login(USERNAME, PASSWORD);

        jiraBrowseIssues = new JiraIssuePage();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/jiraSuccessfulBrowseIssues.csv", numLinesToSkip = 1)
    void browseSuccessfulIssueTests(String issueId) {
        jiraBrowseIssues.navigateToUrlOfIssue(issueId);

        String actual = jiraBrowseIssues.getIssueId();

        assertEquals(issueId, actual);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/jiraUnsuccessfulBrowseIssues.csv", numLinesToSkip = 1)
    void browseUnsuccessfulIssueTests(String issueId) {
        jiraBrowseIssues.navigateToUrlOfIssue(issueId);

        String actual = jiraBrowseIssues.errorMessage();
        String expectedErrorMessage = "You can't view this issue";

        assertEquals(expectedErrorMessage, actual);
    }

    @AfterEach
    public void tearDown() {
        jiraBrowseIssues.quitDriver();
    }
}
