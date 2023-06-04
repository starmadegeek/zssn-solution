package com.starmadegeek.practice.services;

import com.starmadegeek.practice.entities.Location;
import com.starmadegeek.practice.entities.Survivor;
import com.starmadegeek.practice.entities.SurvivorDto;

import java.util.List;

public interface SurvivorService {
    List<Survivor> getAllSurvivors();
    Survivor getSurvivorById(Long id);
    Survivor newSurvivor(SurvivorDto survivorDto);
    Survivor updateLocation(Long id, Location location);
    Survivor suspectSurvivor(Long id);
    int getInfectedSurvivorCount();
    int getNonInfectedSurvivorCount();

    int getAllSurvivorCount();

    double getResourceReportWater();
    double getResourceReportFood();
    double getResourceReportMedication();
    double getResourceReportAmmunition();
    int getLostPoints();

}
