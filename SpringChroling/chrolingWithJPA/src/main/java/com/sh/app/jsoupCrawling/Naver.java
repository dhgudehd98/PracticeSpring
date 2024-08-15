package com.sh.app.jsoupCrawling;



import com.sh.app.chroling.data.NaverList;
import com.sh.app.chroling.entity.ProductEntity;
import com.sh.app.chroling.entity.ProductInformation;
import com.sh.app.chroling.repository.DataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class Naver   {

//    private final DataRepository dataRepository;

    public static void main(String[] args) throws IOException {

        NaverList naver = new NaverList();
        List<String> url = naver.list();
        List<ProductEntity> productList = new ArrayList<>();

        for (int i = 0; i < url.size(); i++) {

            Document doc = Jsoup.connect(url.get(i)).get();

            // 데이터 추출
            Elements items = doc.select("li.item.DomesticProduct");
            for (Element item : items) {
                String site = "네이버 티켓 패키지";
                String title = item.selectFirst("b.name").text();
                String thumbnailUrl = item.selectFirst("img.img").attr("src");
                int price = Integer.parseInt(item.selectFirst("div.base strong.value").text().replaceAll("[^0-9]", "")); // 숫자만 추출
                String sellerSrc = item.selectFirst("div.agent img.logo").attr("src");
                // 날짜와 기간 추출
                Elements itemOptions = item.select("div.options span.option");
                String area = itemOptions.size() > 0 ? itemOptions.get(0).text() : ""; // 제주출발
                //출발 날짜
                String startDate = itemOptions.size() > 1 ? itemOptions.get(1).text().replaceAll("[가-힣]", "").replaceAll("\\.$", "") : ""; // 2024.08.06.화
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
                LocalDate start_date = LocalDate.parse(startDate, formatter);
                // 몇박 몇일인지 추가
                String nights = itemOptions.size() > 2 ? itemOptions.get(2).text().trim() : ""; // 2박 3일
                // <a class="anchor"> 링크 가져오기
                String detailUrl = item.selectFirst("a.anchor").attr("href");
                ProductInformation travelInfo = ProductInformation.builder()
                        .title(title)
                        .nights(nights) // 여행 기간
                        .start_date(start_date) // 시작 날짜
                        .price(price) // 가격
                        .thumbnailUrl(thumbnailUrl) // 이미지 링크
                        .detailUrl(detailUrl)
                        .travelAgency(sellerSrc)
                        .area(area) // 상세 링크
                        .build();
                ProductInformation information = ProductInformation.builder()
                        .title(title)
                        .nights(nights) // 여행 기간
                        .start_date(start_date) // 시작 날짜
                        .price(price) // 가격
                        .thumbnailUrl(thumbnailUrl) // 이미지 링크
                        .detailUrl(detailUrl)
                        .travelAgency(sellerSrc)
                        .area(area) // 상세 링크
                        .build();
                ProductEntity naverTour = ProductEntity.builder()
                        .sourceSite(site)
                        .productInformation(information)
                        .build();
                System.out.println(naverTour.toString());
//                log.debug("{}", naverTour.toString());
//                    dataRepository.save(naverTour);
                    productList.add(naverTour);
                }



        }
    }
}
