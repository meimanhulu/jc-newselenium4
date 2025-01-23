package com.juaracoding;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;
import java.util.List;

public class FirstSelenium {

    static WebDriver driver;

    public static void main(String[] args) {
        System.out.println("Selenium 4");
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());

        String username = "standard_user";

        // locator
        login(username,"secret_sauce");

        // step validation
        WebElement titleWeb = driver.findElement(By.xpath("//span[@class='title']"));
        String actual = titleWeb.getText();
        String expected = "Products";
        assertEquals(actual,expected);


        // Collection
        List<WebElement> listProduct = driver.findElements(By.xpath("//div[@class='inventory_item_name ']"));
        System.out.println("Jumlah product = "+listProduct.size());
        for (int i = 0; i < listProduct.size(); i++) {
            System.out.println(listProduct.get(i).getText());
        }

        logout();
        WebElement elmUsername = driver.findElement(By.xpath("//input[@id='user-name']"));
        elmUsername.sendKeys("standard_user");
        elmUsername.sendKeys(Keys.CONTROL+"A");

//        login("locked_out_user","secret_sauce");

        System.out.println(username.contains("user"));

        // thread sleep
        delay(1000);

        driver.quit();

    }

    // method login
    private static void login(String username, String password){
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
    }

    private static void logout(){
        // step logout id=react-burger-menu-btn, logout_sidebar_link
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.linkText("Logout")).click();
    }

    public static void delay(long second){
        try {
            Thread.sleep(second*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // custom assertion if assertEquals()
    public static void assertEquals(String actual, String expected){
        if(actual.equals(expected)){
            System.out.println("passed");
        } else {
            System.out.println("failed");
        }
    }

}