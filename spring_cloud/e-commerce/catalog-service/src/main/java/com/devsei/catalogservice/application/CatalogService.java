package com.devsei.catalogservice.application;

import com.devsei.catalogservice.domain.CatalogJpaEntity;

public interface CatalogService {
    Iterable<CatalogJpaEntity> getAllCatalog();
}
