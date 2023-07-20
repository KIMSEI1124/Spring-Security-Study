package com.openapi.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ApiService {
    private final ApiRepository apiRepository;

    @Transactional
    public void save(long id, double temp, LocalDateTime date) {
        Weather weather = Weather.builder()
                .number(id)
                .temp(temp)
                .date(date)
                .build();
        apiRepository.save(weather);
    }

    public List<Weather> find() {
        return apiRepository.findAll();
    }
}
