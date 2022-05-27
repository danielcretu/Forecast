package com.example.demo;

import com.example.demo.forecast.DataSource;
import com.example.demo.forecast.Forecast;
import com.example.demo.forecast.ForecastRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.time.LocalTime;

@DataJpaTest
@Rollback(value = false)
public class ControllerTest {
    @Autowired
    private ForecastRepo forecastRepo;
    Forecast forecast = new Forecast(1, LocalDate.of(2022,4,15), LocalTime.of(18,44), 29.2,23.4,true,"Storm", DataSource.CLUJ);
    Forecast forecast1 = new Forecast(2, LocalDate.of(2022,3,13), LocalTime.of(18,44), 25.4,18.2,false,"None", DataSource.BUCURESTI);
    Forecast forecast2 = new Forecast(3, LocalDate.of(2022,5,18), LocalTime.of(18,44), 21.3,24.2,false,"None", DataSource.IASI);
     Forecast forecast3 = new Forecast();

    @Test
    public void getAllTest(){
        beforeTest();
        int size = forecastRepo.findAll().size();
        Assertions.assertEquals(3,size);

    }

    @Test
    public void getByIdTest(){
        beforeTest();
        Forecast forecastbyid = forecastRepo.findById(1);
        Assertions.assertEquals(29.2,forecastbyid.getMaxTemp());
    }

    @Test
    public void addForecastTest(){
        int size = forecastRepo.findAll().size();
        Assertions.assertEquals(3,size);
        forecastRepo.save(forecast3);
        Assertions.assertEquals(4,size+1);
    }
    @Test
    public void deleteForecastTest(){
        forecastRepo.delete(forecast);
        Assertions.assertNull(forecastRepo.findById(1));
    }


    public void beforeTest(){
        forecastRepo.save(forecast);
        forecastRepo.save(forecast1);
        forecastRepo.save(forecast2);
    }


    public void afterTest(){
        forecastRepo.delete(forecast);
        forecastRepo.delete(forecast1);
        forecastRepo.delete(forecast2);
    }
}
