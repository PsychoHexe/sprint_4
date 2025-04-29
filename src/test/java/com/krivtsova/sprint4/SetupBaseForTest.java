package com.krivtsova.sprint4;

import org.junit.After;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
;

public class SetupBaseForTest {
    
    protected WebDriver driver;

    protected String url = "https://qa-scooter.praktikum-services.ru/";
    private final String nameDriverType;

    public SetupBaseForTest() {
        String browser = System.getProperty("browser");
        if(browser == null) browser = 
        "chrome"; 
        //"firefox"; 
        //"edge";
        nameDriverType = browser.toLowerCase();
    }

    @Before
    public void setupOptionDriver(){        
        switch (nameDriverType) {
            case "chrome":{
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
                driver = new ChromeDriver(options);
                break;
            }
            case "firefox":{
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
                driver = new FirefoxDriver(options);        
                break;
            }
            case "edge":{
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
                driver = new EdgeDriver(options);        
                break;
            }
            default:
                assertFalse("Не указано имя драйвера", true);
                break;
        }        
        // переход на страницу тестового приложения
        driver.get(url);
    }

    
    @After
    public void driverQuit() {
        driver.quit();
    }
}