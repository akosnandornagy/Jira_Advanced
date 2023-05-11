package org.example.pagetests;

import org.example.config.ConfigReader;
import org.example.pagefactory.JiraLoginPage;
import org.example.pagefactory.JiraProfilePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

public class JiraLoginTests {
    private JiraLoginPage jiraLogin;
    private JiraProfilePage jiraProfile;
    private final String USERNAME = ConfigReader.getUsername();
    private final String PASSWORD = ConfigReader.getPassword();

    @BeforeEach
    public void setUp() {
        jiraLogin = new JiraLoginPage();
        jiraLogin.navigateToLoginPage();
        jiraProfile = new JiraProfilePage();
    }

    @Test
    void successfulLoginTest() {
        jiraLogin.login(USERNAME, PASSWORD);
        assertTrue(jiraLogin.getUserIcon().isDisplayed());
        jiraLogin.clickUserIcon();
        jiraLogin.clickUserProfile();
        assertEquals(USERNAME, jiraProfile.getUsernameText());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/jiraInvalidLogin.csv", numLinesToSkip = 1)
    void invalidLoginTests(String username, String password) {
        username = "{username}".equals(username) ? USERNAME : username;
        password = "{password}".equals(password) ? PASSWORD : password;

        jiraLogin.login(username, password);
        assertTrue(jiraLogin.getErrorMessage().isDisplayed());
        jiraLogin.login(USERNAME, PASSWORD);
    }

    @Test
    void emptyLoginTest() {
        jiraLogin.clickLoginButton();
        assertTrue(jiraLogin.getLoginLink().isDisplayed());
        assertTrue(jiraLogin.getErrorMessage().isDisplayed());
    }

    @AfterEach
    public void tearDown() {
        jiraLogin.quitDriver();
    }
}
