package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement; // aggiunto
import org.openqa.selenium.JavascriptExecutor; //aggiunto
import org.openqa.selenium.Alert; //aggiunto

public class TestApplication {
    private static ChromeDriver driver;
    private static int timeout = 100;

    @BeforeClass
    public static void setDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fabio.DESKTOP-IG5FOB6\\Desktop\\chromedriver.exe");
    }

    @Before
    public void openBrowser(){
        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", "C:\\Users\\Fabio.DESKTOP-IG5FOB6\\Desktop");
        options.setExperimentalOption("prefs", chromePrefs);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);

        driver.get("http://localhost/login");	
        driver.findElement(By.id("email")).sendKeys("fabiodavide97@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Anonimo1!");
        driver.findElement(By.cssSelector("input[type=submit]")).click();

        WebDriverWait wait = new WebDriverWait(driver, timeout);

        String urlPaginaDiRedirezione = "http://localhost/main";
        try {
            wait.until(ExpectedConditions.urlToBe(urlPaginaDiRedirezione));
        } catch(TimeoutException e) {
            Assert.fail();
        }
    } 

    @After
    public void closeBrowser(){
        driver.close();
    } 

    //TEST PAGINA CLASSIFICA
    @Test
    public void testClassifica() {
    
    assertEquals("http://localhost/main", driver.getCurrentUrl());

   
    driver.findElement(By.id("downloadButton2")).click();

    
    new WebDriverWait(driver, timeout).until(ExpectedConditions.urlToBe("http://localhost/classifica"));

    
    assertEquals("http://localhost/classifica", driver.getCurrentUrl());

    
    new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.id("classificaTable")));

    
    List<WebElement> tableRows = driver.findElements(By.xpath("//table[@id='classificaTable']/tbody/tr"));
    assertFalse("La tabella non è popolata", tableRows.isEmpty());

    
    WebElement logoutButton = new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(By.id("logoutButton")));
    logoutButton.click();
    new WebDriverWait(driver, timeout).until(ExpectedConditions.urlToBe("http://localhost/login"));
    assertEquals("http://localhost/login", driver.getCurrentUrl());
}

//TEST PAGINA STORICO
@Test
    public void navigateToStoricoPage() {
    
    
    assertEquals("http://localhost/main", driver.getCurrentUrl());

    
    WebElement storicoButton = driver.findElement(By.id("downloadButton3")); 
    storicoButton.click();

    WebDriverWait wait = new WebDriverWait(driver, timeout);
    wait.until(ExpectedConditions.urlContains("http://localhost/storico")); 
}
    public void testStoricoPage() {
        navigateToStoricoPage();

        
        WebElement storicoTable = driver.findElement(By.id("storicoTable"));
        Assert.assertTrue(storicoTable.isDisplayed());

        
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#storicoTable tbody tr")));

        List<WebElement> rows = storicoTable.findElements(By.tagName("tr"));
        Assert.assertTrue(rows.size() > 0);

        
    }

    @Test
    public void testLogoutFunctionality() {
        navigateToStoricoPage();

        WebElement logoutButton = driver.findElement(By.id("logoutButton"));
        logoutButton.click();

    }

    //TEST PAGINA NUOVA PARTITA
    public void navigateToGameMode() {
        assertEquals("http://localhost/main", driver.getCurrentUrl());
        driver.findElement(By.id("downloadButton1")).click();
        new WebDriverWait(driver, timeout).until(ExpectedConditions.urlToBe("http://localhost/game_mode"));
        assertEquals("http://localhost/game_mode", driver.getCurrentUrl());
    }
   
    @Test
    public void testNavigateToChoose() {
        navigateToGameMode();
        driver.findElement(By.id("nuovaPartitaButton")).click(); 
        new WebDriverWait(driver, timeout).until(ExpectedConditions.urlToBe("http://localhost/choose"));
        assertEquals("http://localhost/choose", driver.getCurrentUrl());
}

    @Test
    public void testNavigateToAllRobots() {
     navigateToGameMode();
        driver.findElement(By.id("classificaButton")).click(); 
        new WebDriverWait(driver, timeout).until(ExpectedConditions.urlToBe("http://localhost/all_robots"));
        assertEquals("http://localhost/all_robots", driver.getCurrentUrl());
}

    //TEST PAGINA CHOOSE
    @Test
    public void testButtonInteractions() {
        driver.get("http://localhost/choose");

        
        List<WebElement> classButtons = driver.findElements(By.className("lista-item"));
        assertFalse(classButtons.isEmpty());
        WebElement firstClassButton = classButtons.get(0);
        firstClassButton.click();

        
        assertTrue(firstClassButton.getAttribute("class").contains("highlighted"));

        
        WebElement firstRobotButton = driver.findElement(By.cssSelector("button.lista-item.my-button")); 
        firstRobotButton.click();

       
        assertTrue(firstRobotButton.getAttribute("class").contains("highlighted"));

    }

}
 