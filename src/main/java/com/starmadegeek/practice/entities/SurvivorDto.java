package com.starmadegeek.practice.entities;

import lombok.Data;

@Data
public class SurvivorDto {
    String name;
    int age;
    String gender;
    double latitude;
    double longitude;
    int water;
    int food;
    int medication;
    int ammunition;
}
