package org.example.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.example.config.DriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    // Apply baseUrl for all csv files 👇👇👇
    protected String baseUrl = "https://jira-auto.codecool.metastage.net"; // also set this baseUrl from ConfigReader

    public BasePage() {
        DriverManager driverManager = DriverManager.getInstance();
        this.driver = driverManager.getDriver();
        this.wait = driverManager.getWait();
        PageFactory.initElements(driver, this);
    }

    public void quitDriver() {
        DriverManager.getInstance().tearDown();
    }
}
