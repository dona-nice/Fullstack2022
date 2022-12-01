package com.example.tesy.speciesobsjoin;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeciesObsJoinRepository extends JpaRepository<SpeciesObsJoinEntity, Long> {
    SpeciesObsJoinEntity findSpeciesObsJoinBySpeciesObsJoinId(Long speciesObsJoinId);
}
