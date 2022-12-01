package com.example.tesy.animal;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {

        long countByInTesyDateBetween(Date start, Date end);

        long countByInTesyDate(Date date);

    AnimalEntity findAnimalByAnimalId(Long animalId);
}
