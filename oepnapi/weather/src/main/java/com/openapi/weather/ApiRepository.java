package com.openapi.weather;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiRepository extends JpaRepository<Weather, Long> {
}
