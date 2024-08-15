package com.sh._ncp_server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class FileName {

//    public static void main(String[] args) {
//        String img = name();
//        System.out.println(img);
//    }
    public static List<String> name() {


        List<String> listImages = new ArrayList<>();
        String[] image = {"google.png", "kakao.png", "naver.png"};


        String imgSrc = "https://kr.object.ncloudstorage.com/up-bucket/socialImage/";
        // StringBuilder를 사용하여 URL을 구성합니다.
        for (int i = 0; i < image.length; i++) {
            StringBuilder str = new StringBuilder();
            str.append(imgSrc)
                    .append("/")  // 버킷과 패키지 경로를 구분하는 슬래시 추가
                    .append(image[i]);
            listImages.add(str.toString());
        }


        // URL을 문자열로 변환하여 반환합니다.
        return listImages;
    }

}