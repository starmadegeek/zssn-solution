package com.starmadegeek.practice.services;

import com.starmadegeek.practice.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurvivorServiceImpl implements SurvivorService{
    @Autowired
    private final SurvivorRepository survivorRepository;

    public SurvivorServiceImpl(SurvivorRepository survivorRepository) {
        this.survivorRepository = survivorRepository;
    }

    @Override
    public List<Survivor> getAllSurvivors() {
        return this.survivorRepository.findAll();
    }

    @Override
    public Survivor getSurvivorById(Long id) {
        return this.survivorRepository.findById(id).orElse(null);
    }

    @Override
    public Survivor newSurvivor(SurvivorDto survivorDto) {
        Survivor newSurvivor = Survivor.builder()
                .name(survivorDto.getName())
                .age(survivorDto.getAge())
                .gender(survivorDto.getGender())
                .susCounter(0)
                .isInfected(false)
                .lastLocation(new Location(survivorDto.getLatitude(), survivorDto.getLongitude()))
                .inventory(new Inventory(survivorDto.getWater(), survivorDto.getFood(), survivorDto.getMedication(), survivorDto.getAmmunition()))
                .build();
        return this.survivorRepository.save(newSurvivor);
    }

    @Override
    public Survivor updateLocation(Long id, Location location) {
        Optional<Survivor> survivorOptional = this.survivorRepository.findById(id);
        if (survivorOptional.isPresent()) {
            Survivor survivor = survivorOptional.get();
            survivor.setLastLocation(location);
            return survivorRepository.save(survivor);
        }
        return null;
    }

    @Override
    public Survivor suspectSurvivor(Long id) {
        Optional<Survivor> survivorOptional = this.survivorRepository.findById(id);
        if (survivorOptional.isPresent()) {
            Survivor survivor = survivorOptional.get();
            survivor.setSusCounter(survivor.getSusCounter() + 1);
            if(survivor.getSusCounter() >= 3) survivor.setInfected(true);
            return survivorRepository.save(survivor);
        }
        return null;
    }

    @Override
    public int getInfectedSurvivorCount() {
        List<Survivor> survivors = this.survivorRepository.findByIsInfected(true);
        return survivors.size();
    }

    @Override
    public int getNonInfectedSurvivorCount() {
        List<Survivor> survivors = this.survivorRepository.findByIsInfected(false);
        return survivors.size();
    }

    @Override
    public int getAllSurvivorCount(){
        return getAllSurvivors().size();
    }

    @Override
    public double getResourceReportWater() {
        List<Survivor> survivors = this.survivorRepository.findByIsInfected(false);
        return survivors.stream().
                mapToInt(survivor ->
                        survivor.getInventory().getWater())
                .average().orElse(0.0);
    }

    @Override
    public double getResourceReportFood() {
        List<Survivor> survivors = this.survivorRepository.findByIsInfected(false);
        return survivors.stream().
                mapToInt(survivor ->
                        survivor.getInventory().getFood())
                .average().orElse(0.0);
    }

    @Override
    public double getResourceReportMedication() {
        List<Survivor> survivors = this.survivorRepository.findByIsInfected(false);
        return survivors.stream().
                mapToInt(survivor ->
                        survivor.getInventory().getMedication())
                .average().orElse(0.0);
    }

    @Override
    public double getResourceReportAmmunition() {
        List<Survivor> survivors = this.survivorRepository.findByIsInfected(false);
        return survivors.stream().
                mapToInt(survivor ->
                        survivor.getInventory().getAmmunition())
                .average().orElse(0.0);
    }

    @Override
    public int getLostPoints() {
        List<Survivor> infectedSurvivors = this.survivorRepository.findByIsInfected(true);
        return infectedSurvivors.stream().
                mapToInt(survivor ->
                        survivor.getInventory().getTotalPoints())
                .sum();
    }
}
