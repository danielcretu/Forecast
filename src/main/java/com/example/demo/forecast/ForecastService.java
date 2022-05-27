package com.example.demo.forecast;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForecastService {


    private ForecastRepo forecastRepo;

    public ForecastService(ForecastRepo forecastRepo) {
        this.forecastRepo = forecastRepo;
    }

    public List<Forecast> getAllForecasts(){
        return forecastRepo.findAll();
    }

    public Forecast getById(int id){
        return forecastRepo.findById(id);
    }

    public Forecast addForecast(Forecast forecast){
        return forecastRepo.save(forecast);
    }

    public void delete(int id){
        Forecast forecast = forecastRepo.findById(id);
        forecastRepo.delete(forecast);
    }
}
