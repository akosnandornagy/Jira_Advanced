package org.example.pagetests;

import org.example.config.ConfigReader;
import org.example.pagefactory.JiraBrowseIssuePage;
import org.example.pagefactory.JiraLoginPage;
import org.example.pagefactory.JiraLogoutPage;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JiraBrowseIssueTests {
    WebDriver driver;
    private JiraLoginPage jiraLogin;
    private JiraBrowseIssuePage jiraBrowseIssues;
    private final String USERNAME = ConfigReader.getUsername();
    private final String PASSWORD = ConfigReader.getPassword();

    @BeforeEach
    public void setUp() {
        JiraLoginPage jiraLogin = new JiraLoginPage();
        jiraLogin.navigateToLoginPage();
        jiraLogin.login(USERNAME, PASSWORD);

        jiraBrowseIssues = new JiraBrowseIssuePage();
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/jiraSuccessfulBrowseIssues.csv", numLinesToSkip = 1)
    void browseSuccessfulIssueTests(String link, String issueId){
        jiraBrowseIssues.navigateToUrlOfIssue(link);

        String expected = jiraBrowseIssues.getIssueId();

        assertEquals(expected, issueId);
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/jiraUnsuccessfulBrowseIssues.csv", numLinesToSkip = 1)
    void browseUnsuccessfulIssueTests(String link, String errorMessage){
        jiraBrowseIssues.navigateToUrlOfIssue(link);

        String expected = jiraBrowseIssues.errorMessage();

        assertEquals(expected, errorMessage);
    }
    @AfterEach
    public void tearDown() {
        jiraBrowseIssues.quitDriver();
    }
}
