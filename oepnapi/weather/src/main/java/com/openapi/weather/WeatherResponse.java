package com.openapi.weather;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class WeatherResponse {
    private LocalDate date;
    private int id;
    private double temp;

    @Builder
    public WeatherResponse(LocalDate date, int id, double temp) {
        this.date = date;
        this.id = id;
        this.temp = temp;
    }
}
