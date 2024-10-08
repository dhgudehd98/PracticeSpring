package com.sh.app.chroling.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


import java.time.LocalDate;

@Embeddable
@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductInformation {
    @Enumerated(EnumType.STRING)
    @Field(type = FieldType.Keyword)
    private Destination destination; // 여행지

    private Integer nights; // 숙박일

    @Field(type = FieldType.Date, format = {}, pattern = "uuuu-MM-dd")
    @Column(name = "start_date")
    private LocalDate startDate; // 여행 시작일

    private String title; // 제목

    private int price; // 가격

    @Column(name = "thumbnail_url", length = 500)
    private String thumbnailUrl; // 여행지 이미지

    @Column(name = "travel_agency", length = 500)
    private String travelAgency; // 여행사 이미지

    @Column(name = "detail_url", length = 500)
    private String detailUrl; // 상품상세페이지
}