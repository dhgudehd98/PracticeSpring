package com.sh.app.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

public class WebScraper {
    public static void main(String[] args) {
        // ChromeDriver 경로 설정
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");

        // ChromeDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // 브라우저 UI를 표시하지 않음 (headless 모드)
        WebDriver driver = new ChromeDriver(options);

//        String url = "https://pkgtour.naver.com/domestic-list?destination=14&departureDate=2024.08.03.%2C2024.08.17."; // 실제 URL로 변경
        String url = "https://pkgtour.naver.com/domestic-list?destination=14&departureDate=2024.09.30.&departure=all";
        driver.get(url);

        List<Product> products = new ArrayList<>();

        // 상품 목록 가져오기
        List<WebElement> items = driver.findElements(By.cssSelector("li.item.DomesticProduct"));
        for (WebElement item : items) {
            String siteName = "네이버 티켓 패키지";
            String title = item.findElement(By.cssSelector("b.name")).getText();
            String imageLink = item.findElement(By.cssSelector("img.img")).getAttribute("src");
            String price = item.findElement(By.cssSelector("div.base strong.value")).getText();
            String startDate = item.findElement(By.cssSelector("span.option")).getText(); // 수정 필요
            // <a class="anchor"> 링크 가져오기
            WebElement linkElement = item.findElement(By.cssSelector("a.anchor"));
            String detailLink = linkElement.getAttribute("href");

            Product product = new Product(siteName,title, imageLink, price, startDate, detailLink);
            products.add(product);
        }

        // 상품 출력
        for (Product product : products) {
            System.out.println(product);
            product.toString();
        }

        // 브라우저 닫기
        driver.quit();
    }
}
