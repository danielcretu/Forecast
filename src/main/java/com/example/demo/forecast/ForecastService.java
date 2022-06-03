package com.example.demo.forecast;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForecastService {


    private final ForecastRepo forecastRepo;

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

    public boolean checkIfExists(int id){
        return forecastRepo.existsById(id);
    }

    public Forecast updateForecast(int id, Forecast forecast){
        Forecast forecast1 = forecastRepo.findById(id);
        if (forecast.getExactDate() != null){
            forecast1.setExactDate(forecast.getExactDate());
        }
        if (forecast.getExactTime() != null){
            forecast1.setExactTime(forecast.getExactTime());
        }
        if (forecast.getMaxTemp() != 0.00){
            forecast1.setMaxTemp(forecast.getMaxTemp());
        }
        if (forecast.getMinTemp() != 0.00){
            forecast1.setMinTemp(forecast.getMinTemp());
        }
        if (forecast.getOtherInfo() != null){
            forecast1.setOtherInfo(forecast.getOtherInfo());
        }
        if (forecast.getDataSource() != null){
            forecast1.setDataSource(forecast.getDataSource());
        }
        forecast1.setAtmosphereEvents(forecast.isAtmosphereEvents());
        return forecastRepo.save(forecast1);
    }
}
