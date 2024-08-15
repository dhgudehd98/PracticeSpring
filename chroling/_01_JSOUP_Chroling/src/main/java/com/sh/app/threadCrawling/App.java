package com.sh.app.threadCrawling;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.event.WindowFocusListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 멀티 쓰레드를 이용하여 다양한 웹사이트에서 크롤링 해오기
 * 1. 구현 방법 -> URL마다 메소드를 설정하여 작업에 제출 : 크롤링을 막아놓은 홈페이지가 있을 수 있으니 JSOUP 보다는 Selenium을 이용해서 크롤링
 * 2. 임계영역을 지정 할 필요 없음 -> 서로 다른 홈페이지에 접근하기 때문에 임계영역이 아님
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        Inflearn inflearn = new Inflearn();
        Interpark interpark = new Interpark();

        ExecutorService service = Executors.newFixedThreadPool(2);

        service.submit(inflearn);
        service.submit(interpark);

        service.shutdown();

    }
}