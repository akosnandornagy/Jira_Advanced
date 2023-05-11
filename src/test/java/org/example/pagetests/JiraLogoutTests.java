package org.example.pagetests;

import org.example.config.ConfigReader;
import org.example.pagefactory.JiraLoginPage;
import org.example.pagefactory.JiraLogoutPage;
import org.example.pagefactory.JiraProfilePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JiraLogoutTests {
    WebDriver driver;
    private JiraLoginPage jiraLogin;
    private JiraLogoutPage jiraLogout;
    private final String USERNAME = ConfigReader.getUsername();
    private final String PASSWORD = ConfigReader.getPassword();

    @BeforeEach
    public void setUp() {
        JiraLoginPage jiraLogin = new JiraLoginPage();
        jiraLogin.navigateToLoginPage();
        jiraLogin.login(USERNAME, PASSWORD);

        jiraLogout = new JiraLogoutPage();
    }
    @Test
    void LogoutTest() {
    jiraLogout.clickUserIcon();
    jiraLogout.clickLogoutBtn();
    jiraLogout.getLogoutMessage();
    jiraLogout.checkLoginIcon();
    }

    @AfterEach
    public void tearDown() {
        jiraLogout.quitDriver();
    }
}
