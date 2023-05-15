package org.example.pagetests;

import org.example.config.ConfigReader;
import org.example.pagefactory.JiraLoginPage;
import org.example.pagefactory.JiraLogoutPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JiraLogoutTests {
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
    jiraLogout.checkLoginIcon();
    assertEquals("You are now logged out. Any automatic login has also been stopped.", jiraLogout.getLogoutMessage());
    }

    @AfterEach
    public void tearDown() {
        jiraLogout.quitDriver();
    }
}
