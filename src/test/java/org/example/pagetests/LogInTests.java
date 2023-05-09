package org.example.pagetests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.config.ConfigReader;
import org.example.pages.JiraLogin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogInTests {
    private WebDriver driver;
    private JiraLogin jiraLogin;
    private String username = ConfigReader.getUsername();
    private String password = ConfigReader.getPassword();
    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://jira-auto.codecool.metastage.net/login.jsp");
        jiraLogin = new JiraLogin(driver);
    }

    @Test
    public void loginTest() {
        jiraLogin.login(username, password);

        assertTrue(jiraLogin.getUserIcon().isDisplayed());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
