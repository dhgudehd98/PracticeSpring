package com.sh.app.threadCrawling;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Inflearn implements Runnable {
    @Override
    public void run() {
        // ChromeDriver 경로 설정 (자신의 시스템에 맞는 경로로 설정 필요)
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");

        // WebDriver 생성 (ChromeDriver를 예로 듭니다)
        WebDriver driver = new ChromeDriver();

        try {
            System.out.println("인프런 크롤링 작업을 시작합니다.");
            // URL 접속
            driver.get("https://www.inflearn.com/");

            // CSS 선택자를 이용한 요소 선택
            //String query = "figure.image.is_thumbnail > img";
            List<WebElement> elements = driver.findElements(By.xpath("//div[@class='card-image']/figure[@class='image is_thumbnail']/img"));

            // 각 요소의 src 속성을 추출
            for (WebElement element : elements) {
                String imgSrc = element.getAttribute("src");
                System.out.println(imgSrc);
            }
        } finally {
            System.out.println("인프런 크롤링 작업을 종료합니다.");
            // 브라우저 종료
            driver.quit();
        }

    }
}