package com.openapi.weather;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double temp;
    private Long number;
    private LocalDateTime date;

    @Builder
    public Weather(Long id, double temp, Long number, LocalDateTime date) {
        this.id = id;
        this.temp = temp;
        this.number = number;
        this.date = date;
    }
}
