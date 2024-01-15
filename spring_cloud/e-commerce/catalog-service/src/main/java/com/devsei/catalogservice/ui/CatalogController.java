package com.devsei.catalogservice.ui;

import com.devsei.catalogservice.application.CatalogService;
import com.devsei.catalogservice.domain.CatalogJpaEntity;
import com.devsei.catalogservice.dto.CatalogRes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/catalog-service")
@RestController
public class CatalogController {
    private final CatalogService catalogService;
    private final Environment environment;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in Catalog Service on PORT %s",
                environment.getProperty("local.server.port"));
    }

    @GetMapping("/catalogs")
    public ResponseEntity<List<CatalogRes>> findAll() {
        List<CatalogRes> res = new ArrayList<>();
        catalogService.getAllCatalog().forEach(catalog -> res.add(CatalogRes.from(catalog)));
        return ResponseEntity.ok(res);
    }
}
