package com.devsei.catalogservice.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<CatalogJpaEntity, Long> {
    CatalogJpaEntity findByProductId(String productId);
}
