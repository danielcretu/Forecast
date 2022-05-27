package com.example.demo.controller;


import com.example.demo.forecast.Forecast;
import com.example.demo.forecast.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ForecastController {

    @Autowired
    private ForecastService forecastService;

    @GetMapping("/forecasts")
    public List<Forecast> getAllForecasts(){
        return forecastService.getAllForecasts();
    }

    @GetMapping("/forecast/{id}")
    public Forecast getForecastById(@PathVariable int id){
        return forecastService.getById(id);
    }

    @PostMapping("/addForecast")
    public ResponseEntity<Forecast> addForecast(@RequestBody Forecast forecast){
        if (forecast.getExactDate().isBefore(LocalDate.now()) || forecast.getExactDate().isEqual(LocalDate.now())){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        forecastService.addForecast(forecast);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateForecast/{id}")
    public Forecast updateForecast(@PathVariable int id, @RequestBody Forecast forecast){
        Forecast oldForecast = forecastService.getById(id);
        forecast.setId(oldForecast.getId());
        return forecastService.addForecast(forecast);
    }

    @DeleteMapping("/deleteForecast/{id}")
    public ResponseEntity<Forecast> deleteForecast(@PathVariable int id){
        Forecast forecast = forecastService.getById(id);
        LocalDate thirtydaysbefore = LocalDate.now().minusDays(30);
        System.out.println(thirtydaysbefore);
        LocalDate thirtydaysafter = LocalDate.now().plusDays(30);
        System.out.println(thirtydaysafter);
        if (forecast.getExactDate().isAfter(thirtydaysbefore) && forecast.getExactDate().isBefore(thirtydaysafter)){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        forecastService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

