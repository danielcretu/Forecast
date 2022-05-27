package com.example.demo.forecast;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FORECAST")
public class Forecast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "EXACTDATE")
    LocalDate exactDate;
    @Column(name = "EXACTTIME")
    LocalTime exactTime;
    @Column(name = "MAXTEMP")
    private double maxTemp;  //Celsius;
    @Column(name = "MINTEMP")
    private double minTemp; //Celsius;
    @Column(name = "ATMOSPHEREEVENTS")
    private boolean atmosphereEvents;
    @Column(name = "OTHERINFO")
    private String otherInfo;
    @Column(name = "DATASOURCE")
    private  DataSource dataSource;
}
