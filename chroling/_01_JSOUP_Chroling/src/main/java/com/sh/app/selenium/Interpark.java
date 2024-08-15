package com.sh.app.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Interpark {

    public static void main(String[] args) {
       System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");


        // ChromeDriver 인스턴스 생성
        WebDriver driver = new ChromeDriver();

        try {
            // 크롤링할 웹 페이지 URL
            String url = "https://travel.interpark.com/tour/main/domestic";
            driver.get(url);

            // 페이지 로딩 대기 (필요에 따라 WebDriverWait 사용 권장)
            Thread.sleep(5000); // 5초 대기, 필요에 따라 조정

            // 이미지 요소 찾기
            List<WebElement> imageElements = driver.findElements(By.xpath("//ul[@class='slide']/li[@class='item']/a/div[@class='thumbWrap']/img"));

            // 이미지 URL 추출 및 출력
            for (WebElement img : imageElements) {
                String imageUrl = img.getAttribute("src");
                System.out.println(imageUrl);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 브라우저 종료
            driver.quit();
        }
    }
}
