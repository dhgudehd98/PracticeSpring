package com.sh.app.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class NaverTourCrawler {

    @GetMapping("/app")
    @ResponseBody
    public List<String> index() {
        // Set the path to your WebDriver executable
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        List<String> imgSrcList = new ArrayList<>();

        try {
            // Navigate to the website
            driver.get("https://www.inflearn.com/");

            // Wait for the page to load (You might need to implement a more robust wait)
            Thread.sleep(5000);  // This is just a simple wait, use WebDriverWait for a better approach

            // Find the desired elements
            String query = "figure.image.is_thumbnail > img";
            List<WebElement> elements = driver.findElements(By.cssSelector(query));


            for (WebElement element : elements) {
                String imgSrc = element.getAttribute("src");
                System.out.println(imgSrc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }

        // Return the list of image URLs
        return imgSrcList;
    }
}
