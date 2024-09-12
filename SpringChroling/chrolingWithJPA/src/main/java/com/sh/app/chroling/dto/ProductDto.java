package com.sh.app.chroling.dto;

import com.sh.app.chroling.entity.Destination;
import com.sh.app.chroling.entity.ProductInformation;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private Destination destination;
    private String sourceSite; // 출처 페이지
    private ProductInformation productInformationDto;
    private int viewCount;  // 조회수
}