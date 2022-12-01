package com.example.tesy.speciesobsjoin;

import com.example.tesy.observationtype.ObservationTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class SpeciesObsJoinService {

    private final SpeciesObsJoinRepository speciesObsJoinRepository;

    @Autowired
    public SpeciesObsJoinService(SpeciesObsJoinRepository speciesObsJoinRepository) {
        this.speciesObsJoinRepository = speciesObsJoinRepository;
    }

    public List<SpeciesObsJoinEntity> getSpeciesObsJoin() {
        return speciesObsJoinRepository.findAll();
    }

    public void registerNewSpeciesObsJoin(SpeciesObsJoinEntity speciesObsJoin) {
    }

    public void deleteSpeciesObsJoin(Long speciesObsJoinId) {
        boolean exists = speciesObsJoinRepository.existsById(speciesObsJoinId);
        if (!exists) {
            throw new IllegalStateException("This speciesobsjoin with Id " + speciesObsJoinId + " do not exists!");
        }
        speciesObsJoinRepository.deleteById(speciesObsJoinId);
    }

    @Transactional
    public SpeciesObsJoinEntity updateSpeciesObsJoin(Long speciesObsJoinId, SpeciesObsJoinEntity newSpeciesObsJoin) {
        return speciesObsJoinRepository.findById(speciesObsJoinId).orElseThrow(()
                -> new IllegalStateException("SpeciesObsJoin with ID " + speciesObsJoinId + " do not exists!"));
    }
}
