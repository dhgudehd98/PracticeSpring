package com.sh.app.selenium;




import com.sh.app.chroling.data.InterparkList;
import com.sh.app.chroling.data.NaverList;
import com.sh.app.chroling.entity.ProductEntity;
import com.sh.app.chroling.entity.ProductInformation;
import com.sh.app.chroling.repository.DataRepository;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Interpark implements CommandLineRunner {

    private  final DataRepository dataRepository;

    // interpark url 저장해놓는 객체
    InterparkList list = new InterparkList();
    // interpark url을 가지고 있는 list 설정
    List<String> url = list.list();

    List<ProductEntity> productList = new ArrayList<>();


    @Override
    public void run(String... args) throws Exception {

        //ChromeDriver 옵션 설정 및 연결
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless"); // 브라우저 UI를 표시하지 않음 (headless 모드)
        WebDriver driver = new ChromeDriver(options);

        try {
            for(int i =0; i<url.size(); i++) {

                //url에 담겨 있는 list 가져와서 연결
                driver.get(url.get(i));
                System.out.println("현재 url" + url);


                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.itemInfo > div.itemInfoTop > div.itemInfoMain > div.title")));

                String site = "인터파크";
                // 상품 요소 선택
                List<WebElement> items = driver.findElements(By.cssSelector("div.resultContent > ul.tourCompSearchList > li"));

                for (WebElement item : items) {
                    try {

                        // 상세 링크
                        WebElement linkElement = item.findElement(By.cssSelector("a"));
                        String detailLink = linkElement.getAttribute("href");

                        // 이미지 URL
                        WebElement imageElement = item.findElement(By.cssSelector("img"));
                        String imageUrl = imageElement.getAttribute("src");

                        // 제목

                        WebElement titleElement = item.findElement(By.cssSelector("div.itemInfo > div.itemInfoTop > div.itemInfoMain > div.title"));
                        String title = titleElement.getText().trim();

                        // 가격
                        WebElement priceElement = item.findElement(By.cssSelector("div.itemInfoPrice > div.final > strong"));
                        int price = Integer.valueOf(priceElement.getText().replaceAll("[^0-9]", "")); // 숫자만 추출

                        // 몇 박 몇 일 및 출발 지역
                        WebElement placeElement = item.findElement(By.cssSelector("div.itemInfoMain > div.place"));
                        List<WebElement> placeSpans = placeElement.findElements(By.tagName("span"));

                        String duration = "";
                        String departureLocation = "";

                        if (placeSpans.size() > 0) {
                            duration = placeSpans.get(0).getText().trim();
                        } else {
                            System.out.println("기간 정보가 없습니다.");
                        }

                        if (placeSpans.size() > 1) {
                            departureLocation = placeSpans.get(1).getText().trim();
                        } else {
                            System.out.println("출발 지역 정보가 없습니다.");
                        }


                        ProductInformation travelInformation = ProductInformation.builder()
                                .title(title) // 여행 제목
                                .nights(duration) // 몇박 몇일
                                .price(price) // 가격
                                .thumbnailUrl(imageUrl) //여행지 이미지
                                .detailUrl(detailLink) // 상품 상세페이지
                                .area(departureLocation) // 출발지역
                                .build();
                        //인터파크에는 시작 날짜랑 여행일 존재하지 않음 -> 몇박 몇일인지만 존재
                        ProductEntity chrolingData = ProductEntity.builder()
                                .sourceSite(site)
                                .productInformation(travelInformation)
                                .build();

                        dataRepository.save(chrolingData);
                        //반환하기 위한 Entity를 담은 객체
                        productList.add(chrolingData);
                    } catch (Exception e) {
                        // 오류가 발생하면 출력
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 브라우저 종료
            driver.quit();
        }


    }
}