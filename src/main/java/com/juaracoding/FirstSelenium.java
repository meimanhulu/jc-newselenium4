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
        try {
            driver.get("https://www.saucedemo.com/");
            System.out.println("Judul halaman: " + driver.getTitle());

            loginAndAddToCart(driver, "standard_user", "secret_sauce");
            Thread.sleep(3000);

            calculateTotalPrice(driver);
            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

        public static void loginAndAddToCart(WebDriver driver, String username, String password) {
            try {
                WebElement usernameInput = driver.findElement(By.xpath("//*[@id='user-name']"));
                usernameInput.clear();
                usernameInput.sendKeys(username);
    
                WebElement passwordInput = driver.findElement(By.xpath("//*[@id='password']"));
                passwordInput.clear();
                passwordInput.sendKeys(password);
    
                WebElement loginButton = driver.findElement(By.xpath("//*[@id='login-button']"));
                loginButton.click();
    
                // Ambil daftar tombol "Add to Cart"
                List<WebElement> addToCartButtons = driver.findElements(By.xpath("//button[contains(@id, 'add-to-cart')]"));
    
                if (addToCartButtons.isEmpty()) {
                    System.out.println("Tidak ada tombol 'Add to Cart' yang ditemukan.");
                } else {
                    System.out.println("Klik tombol 'Add to Cart' untuk setiap produk:");
                    int productIndex = 1;
                    for (WebElement button : addToCartButtons) {
                        System.out.println("- Klik tombol untuk produk ke-" + productIndex);
                        button.click();
                        Thread.sleep(1000); // Delay opsional
                        productIndex++;
                    }
                }
    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
        public static void calculateTotalPrice(WebDriver driver) {
            try {
                // Ambil semua elemen harga produk
                List<WebElement> priceElements = driver.findElements(By.xpath("//*[@id='inventory_container']/div/div/div[2]/div[2]/div"));
    
                if (priceElements.isEmpty()) {
                    System.out.println("Tidak ada harga produk yang ditemukan.");
                } else {
                    double totalPrice = 0.0;
    
                    System.out.println("Daftar harga produk:");
                    for (WebElement priceElement : priceElements) {
                        String priceText = priceElement.getText(); // Contoh: "$29.99"
                        priceText = priceText.replace("$", ""); // Hapus simbol $
                        double price = Double.parseDouble(priceText); // Konversi ke double
                        totalPrice += price;
    
                        System.out.println("- Harga produk: $" + price);
                    }
    
                    System.out.println("Total harga semua produk: $" + totalPrice);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }