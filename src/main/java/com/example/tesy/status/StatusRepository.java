package com.example.tesy.status;

import com.example.tesy.species.SpeciesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<StatusEntity, Long> {
    Optional<SpeciesEntity> findStatusByName(String name);
}
