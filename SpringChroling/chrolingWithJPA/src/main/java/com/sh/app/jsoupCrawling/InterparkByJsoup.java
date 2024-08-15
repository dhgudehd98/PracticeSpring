//package com.sh.app.chroling.jsoup;
//
//import com.sh.app.chroling.entity.ProductDto;
//import com.sh.app.chroling.entity.ProductInformation;
//import com.sh.app.chroling.repository.DataRepository;
//import lombok.RequiredArgsConstructor;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class InterparkByJsoup  {
//
//    private final DataRepository dataRepository;
//
//    public static void main(String[] args) {
//        String url = "https://travel.interpark.com/tour/search?category=domestic&q=%EC%A0%9C%EC%A3%BC%EB%8F%84&domain=t&startDate=20240814&endDate=&departure=%EC%B6%9C%EB%B0%9C%EC%A7%80+%EC%A0%84%EC%B2%B4";
//        String site = "인터파크";
//
//        try {
//            // 웹 페이지 열기
//            System.out.println("Connecting to URL: " + url);
//            Document document = Jsoup.connect(url).get();
//            System.out.println("Successfully connected to URL");
//
//            // 상품 요소 선택
//            Elements items = document.select("div.resultContent ul.tourCompSearchList li");
//            System.out.println("Found items: " + items.size());
//
//            for (Element item : items) {
//                try {
//                    // 상세 링크
//                    Element linkElement = item.selectFirst("a");
//                    String detailLink = linkElement.attr("href");
//
//                    // 이미지 URL
//                    Element imageElement = item.selectFirst("a > div.itemImage > img");
//                    String imageUrl = imageElement.attr("src");
//
//                    // 제목
//                    Element titleElement = item.selectFirst("a > div.itemInfo > div.title");
//                    String title = titleElement.text().trim();
//
//                    // 가격
//                    Element priceElement = item.selectFirst("a > div.itemInfo > div.itemInfoPrice > div.final > strong");
//                    String price = priceElement.text().replaceAll("[^0-9]", "").trim();
//
//                    // 몇 박 몇 일 및 출발 지역
//                    Element placeElement = item.selectFirst("a > div.itemInfo > div.place");
//                    Elements placeSpans = placeElement.select("span");
//
//                    String duration = "";
//                    String departureLocation = "";
//
//                    if (placeSpans.size() > 0) {
//                        duration = placeSpans.get(0).text().trim();
//                    } else {
//                        System.out.println("기간 정보가 없습니다.");
//                    }
//
//                    if (placeSpans.size() > 1) {
//                        departureLocation = placeSpans.get(1).text().trim();
//                    } else {
//                        System.out.println("출발 지역 정보가 없습니다.");
//                    }
//
//                    ProductInformation productInformation = ProductInformation.builder()
//                            .title(title)
//                            .nights(duration)
//                            // 시작 날짜 없고
//                            .price(price)
//                            .thumbnailUrl(imageUrl)
//                            .detailUrl(detailLink)
//                            .area(departureLocation)
//                            .build();
//
//                    ProductDto productDto = ProductDto.builder()
//                            .sourceSite(site)
//                            .productInformation(productInformation)
//                            .build();
//
//                    System.out.println(productDto.toString());
////                    dataRepository.save(chrolingData);
//                } catch (Exception e) {
//                    // 오류가 발생하면 출력
//                    e.printStackTrace();
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
