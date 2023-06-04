package com.starmadegeek.practice.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurvivorRepository extends JpaRepository<Survivor, Long> {
    List<Survivor> findByIsInfected(boolean isInfected);
}
