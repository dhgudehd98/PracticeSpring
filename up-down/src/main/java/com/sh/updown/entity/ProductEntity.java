package com.sh.updown.entity;


import com.sh.updown.dto.ProductDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "tbl_product")
@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private ProductInformation productInformation;

    @Column(name = "source_site" ,length = 500)
    private String sourceSite; // 출처 페이지
    @CreationTimestamp
    private LocalDate createDate;
    @Column(name = "is_visible")
    private boolean isVisible; // 페이지 노출 가능 여부
    @Column(name = "view_count")
    private int viewCount;  // 조회수

    public ProductEntity toEntity(ProductDto productDto) {
        return ProductEntity.builder()
                .id(productDto.getId())
                .sourceSite(productDto.getSourceSite())
                .productInformation(productDto.getProductInformationDto())
                .viewCount(productDto.getViewCount())
                .isVisible(productDto.isVisible())
                .build();
    }
}