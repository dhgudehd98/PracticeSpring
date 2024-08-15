package com.sh.app.selenium;

import jakarta.persistence.*;
import lombok.*;



@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private String siteName;
    private String title; //제목
    private String imageLink;  // https://imageLink/
    private String price; // 400000
//    private String date; //여행 기간
    private String startDate; //2024/8/31
    private String detailLink; // https://detailLink

    @Override
    public String toString() {
        return "Product{" +
                "siteName='" + siteName + '\'' +
                ", title='" + title + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", price='" + price + '\'' +
                ", startDate='" + startDate + '\'' +
                ", detailLink='" + detailLink + '\'' +
                '}';
    }
}