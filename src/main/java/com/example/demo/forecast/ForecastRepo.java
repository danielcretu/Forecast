package com.example.demo.forecast;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ForecastRepo extends JpaRepository<Forecast, Integer> {
    Forecast findById(int id);
    boolean existsById(int id);
}
