package org.example.pagetests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.config.ConfigReader;
import org.example.pages.JiraLogin;
import org.example.pages.JiraProfile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;

public class JiraLoginTests {
    private WebDriver driver;
    private JiraLogin jiraLogin;
    private JiraProfile jiraProfile;
    private static final String SUCCESSFUL = "successful";
    private static final String UNSUCCESSFUL = "unsuccessful";
    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://jira-auto.codecool.metastage.net/login.jsp");
        jiraLogin = new JiraLogin(driver);
        jiraProfile = new JiraProfile(driver);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/jiraLogin.csv", numLinesToSkip = 1)
    void loginTests(String username, String password, String expectedOutcome) {
        username = "{username}".equals(username) ? ConfigReader.getUsername() : username;
        password = "{password}".equals(password) ? ConfigReader.getPassword() : password;

        jiraLogin.login(username, password);

        if (SUCCESSFUL.equals(expectedOutcome)) {
            assertTrue(jiraLogin.getUserIcon().isDisplayed());
            jiraLogin.clickUserIcon();
            jiraLogin.clickUserProfile();
            assertEquals("https://jira-auto.codecool.metastage.net/secure/ViewProfile.jspa",driver.getCurrentUrl());
            assertEquals(username, jiraProfile.getUsernameText());
        } else if (UNSUCCESSFUL.equals(expectedOutcome)) {
            assertTrue(jiraLogin.getErrorMessage().isDisplayed());
            jiraLogin.login(ConfigReader.getUsername(), ConfigReader.getPassword());
        } else {
            fail("Invalid expected outcome value: " + expectedOutcome);
        }
    }

    @Test
    void emptyLoginTest(){
        jiraLogin.clickLoginButton();
        assertTrue(jiraLogin.getLoginLink().isDisplayed());
        assertTrue(jiraLogin.getErrorMessage().isDisplayed());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
