package com.starmadegeek.practice.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Survivor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    Long id;
    String name;
    int age;
    String gender;
    @Embedded
    Location lastLocation;
    @Embedded
    Inventory inventory;
    boolean isInfected;
    int susCounter;
}
