package com.devsei.catalogservice.application;

import com.devsei.catalogservice.domain.CatalogJpaEntity;
import com.devsei.catalogservice.domain.CatalogRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class CatalogServiceImpl implements CatalogService {
    private final CatalogRepository catalogRepository;

    @Override
    public Iterable<CatalogJpaEntity> getAllCatalog() {
        return catalogRepository.findAll();
    }
}
