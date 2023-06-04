package com.starmadegeek.practice.web;

import com.starmadegeek.practice.entities.Location;
import com.starmadegeek.practice.entities.Survivor;
import com.starmadegeek.practice.entities.SurvivorDto;
import com.starmadegeek.practice.services.SurvivorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SurvivorController {
    @Autowired
    private final SurvivorService survivorService;

    public SurvivorController(SurvivorService survivorService) {
        this.survivorService = survivorService;
    }

    @GetMapping("/survivor")
    public List<Survivor> getSurvivors(){
        return this.survivorService.getAllSurvivors();
    }
    @GetMapping("/survivor/{id}")
    public Survivor getSurvivor(@PathVariable Long id){
        return this.survivorService.getSurvivorById(id);
    }

    @PostMapping("/survivor")
    public Survivor newSurvivor(@RequestBody SurvivorDto survivorDto){
        return this.survivorService.newSurvivor(survivorDto);
    }

    @PutMapping("/survivor/{id}")
    public Survivor updateLocation(@RequestBody Location location, @PathVariable Long id){
        return this.survivorService.updateLocation(id, location);
    }

    @PutMapping("/survivor/suspect/{id}")
    public Survivor suspectSurvivor(@PathVariable Long id){
        return this.survivorService.suspectSurvivor(id);
    }

    @GetMapping("report/infected")
    public double getNumberOfInfectedSurvivors(){
        int infectedSurvivors = this.survivorService.getInfectedSurvivorCount();
        int totalSurvivors = this.survivorService.getAllSurvivorCount();
        return (double) infectedSurvivors / (totalSurvivors);
    }

    @GetMapping("report/non-infected")
    public double getNumberOfNonInfectedSurvivors(){
        int nonInfectedSurvivors = this.survivorService.getNonInfectedSurvivorCount();
        int totalSurvivors = this.survivorService.getAllSurvivorCount();
        return (double) nonInfectedSurvivors / (totalSurvivors);
    }
    @GetMapping("/report/water")
    public double getWaterReport(){
        return this.survivorService.getResourceReportWater();
    }

    @GetMapping("/report/food")
    public double getFoodReport(){
        return this.survivorService.getResourceReportFood();
    }
    @GetMapping("/report/medication")
    public double getMedicationReport(){
        return this.survivorService.getResourceReportMedication();
    }
    @GetMapping("/report/ammunition")
    public double getAmmunitionReport(){
        return this.survivorService.getResourceReportAmmunition();
    }
    @GetMapping("/report/lost-points")
    public int getLostPoints(){
        return this.survivorService.getLostPoints();
    }

}
