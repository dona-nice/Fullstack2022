package com.example.tesy.species;

import com.example.tesy.inclasses.InclassesEntity;
import com.example.tesy.status.StatusEntity;
import com.example.tesy.status.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SpeciesService {

    private final SpeciesRepository speciesRepository;

    @Autowired
    public SpeciesService(SpeciesRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }

    public List<SpeciesEntity> getSpecies() {
        return speciesRepository.findAll();
    }

    public void addNewSpecies(SpeciesEntity speciesEntity) {
    }

    public void deleteSpecies(Long speciesId) {
        boolean exists = speciesRepository.existsById(speciesId);
        if (!exists) {
            throw new IllegalStateException("This status with Id " + speciesId + " do not exists!");
        }
        speciesRepository.deleteById(speciesId);
    }

    @Transactional
    public SpeciesEntity updateSpecies(Long speciesId, SpeciesEntity newSpecies) {
        SpeciesEntity updateSpecies = speciesRepository.findById(speciesId).orElseThrow(()
                -> new IllegalStateException("Species with ID " + speciesId + " do not exists!"));
        if  (newSpecies.getName() !=null &&
                !Objects.equals(updateSpecies.getName(),
                        newSpecies.getName())) {
            Optional<SpeciesEntity> speciesEntityOptional =
                    speciesRepository.findSpeciesByName(newSpecies.getName());
            if (speciesEntityOptional.isPresent()) {
                throw new IllegalStateException("This species name is Taken!");
            }
            updateSpecies.setName(newSpecies.getName());
        }
        return updateSpecies;
    }
}
