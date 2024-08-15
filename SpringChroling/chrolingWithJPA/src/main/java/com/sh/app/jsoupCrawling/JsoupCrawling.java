//package com.sh.app.jsoupCrawling;
//
//import lombok.extern.slf4j.Slf4j;
//import org.jsoup.Connection;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
///**
// * Connect를 이용해서 url을 연결하고, get() 메소드를 통해서 Connect -> Document로 변환
// * Document의 select 메소드를 통해서 -> 원하는 HTML의 class, id의 요소들을 가져옴.
// */
//@Component
//@Slf4j
//public class JsoupCrawling {
//
//    public static Connection getConnection(String url) throws Exception {
//        return Jsoup.connect(url);
//    }
//
//    public static Elements getElements(Connection connection, String url, String query) throws Exception {
//        // 전달 받은 url이 null값 또는 빈값인지 아닌지 확인하고, 유효한 경우 JSoup Connection 연결 ,
//        // hasText(url) 메소드 : null값이 아니고 , 빈 값이 아니다 -> true 반환 , null값, or 빈 값 -> false 반환
//        Connection con = StringUtils.hasText(url) ? getConnection(url) : connection;
//
//        Document dom = con.get(); // Connection에 대한 부분을 Doucment 반환형으로 변경
//
//        return dom.select(query); // Element요소로 반환
//    }
//
//    public static void main(String[] args) throws Exception {
//        String url = "https://www.inflearn.com/";
//        // 수정된 CSS 선택자: figure 태그에 클래스 image와 is_thumbnail이 모두 적용된 경우를 선택
//        String query = "figure.image.is_thumbnail > img";
//
//        Elements selects = getElements(null, url, query);
//
//        // Elements 내의 각 요소에서 src 속성을 추출
//        for (Element element : selects) {
//            String imgSrc = element.attr("src");
//            System.out.println(imgSrc);
//        }
//    }
//}
