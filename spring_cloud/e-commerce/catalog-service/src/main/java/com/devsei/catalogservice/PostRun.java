package com.devsei.catalogservice;

import com.devsei.catalogservice.domain.CatalogJpaEntity;
import com.devsei.catalogservice.domain.CatalogRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class PostRun {
    private final CatalogRepository catalogRepository;

    @PostConstruct
    public void run() {
        CatalogJpaEntity entity1 = CatalogJpaEntity.builder()
                .productId("CATALOG-001")
                .productName("Berlin")
                .stock(100)
                .unitPrice(1500)
                .build();
        CatalogJpaEntity entity2 = CatalogJpaEntity.builder()
                .productId("CATALOG-002")
                .productName("Tokyo")
                .stock(110)
                .unitPrice(1000)
                .build();
        CatalogJpaEntity entity3 = CatalogJpaEntity.builder()
                .productId("CATALOG-003")
                .productName("Stockholm")
                .stock(120)
                .unitPrice(2000)
                .build();
        catalogRepository.save(entity1);
        catalogRepository.save(entity2);
        catalogRepository.save(entity3);
    }
}
