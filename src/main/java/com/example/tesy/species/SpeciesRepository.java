package com.example.tesy.species;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpeciesRepository extends JpaRepository<SpeciesEntity, Long> {
    Optional<SpeciesEntity> findSpeciesByName(String name);
}
