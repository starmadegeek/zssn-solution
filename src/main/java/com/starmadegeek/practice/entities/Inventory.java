package com.starmadegeek.practice.entities;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    int water;
    int food;
    int medication;
    int ammunition;

    public int getWaterPoints(){
        return water*4;
    }

    public int getFoodPoints(){
        return food*3;
    }

    public int getMedicationPoints(){
        return medication*2;
    }

    public int getAmmunitionPoints(){
        return ammunition;
    }

    public int getTotalPoints(){
        return getWaterPoints() + getFoodPoints() + getMedicationPoints() + getAmmunitionPoints();
    }
}
