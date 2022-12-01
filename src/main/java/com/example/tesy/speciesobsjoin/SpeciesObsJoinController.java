package com.example.tesy.speciesobsjoin;

import com.example.tesy.observation.ObservationEntity;
import com.example.tesy.observation.ObservationRepository;
import com.example.tesy.species.SpeciesEntity;
import com.example.tesy.species.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/speciesobsjoin")
public class SpeciesObsJoinController {

    private final SpeciesObsJoinService speciesObsJoinService;

    private final SpeciesObsJoinRepository speciesObsJoinRepository;
    private final SpeciesRepository speciesRepository;
    private final ObservationRepository observationRepository;
    @Autowired
    public SpeciesObsJoinController(SpeciesObsJoinService speciesObsJoinService, SpeciesObsJoinRepository speciesObsJoinRepository, SpeciesRepository speciesRepository, ObservationRepository observationRepository) {
        this.speciesObsJoinService = speciesObsJoinService;
        this.speciesObsJoinRepository = speciesObsJoinRepository;
        this.speciesRepository = speciesRepository;
        this.observationRepository = observationRepository;
    }
    @PostMapping
    public SpeciesObsJoinEntity registerNewSpeciesObsJoin(
            @RequestBody SpeciesObsJoinEntity speciesObsJoin) {
        return speciesObsJoinRepository.save(speciesObsJoin);
    }

    @GetMapping
    public List<SpeciesObsJoinEntity> getSpeciesObsJoin() {
        return speciesObsJoinService.getSpeciesObsJoin();
    }

    // Get by Id
    @GetMapping(path = "{speciesObsJoinId}")
    public SpeciesObsJoinEntity findSpeciesObsJoinById(@PathVariable (value =
            "speciesObsJoinId") Long speciesObsJoinId) {
        return this.speciesObsJoinRepository.findById(speciesObsJoinId).orElse(null);
    }

    @DeleteMapping(path = "{speciesObsJoinId}")
    public void deleteNewSpeciesObsJoin(
            @PathVariable("speciesObsJoinId") Long speciesObsJoinId) {
        speciesObsJoinService.deleteSpeciesObsJoin(speciesObsJoinId);
    }

    @PutMapping(path = "{speciesObsJoinId}")
    public ResponseEntity<SpeciesObsJoinEntity> updateSpeciesObsJoin(
            @PathVariable("speciesObsJoinId") Long speciesObsJoinId,
            @RequestBody SpeciesObsJoinEntity newSpeciesObsJoin) {
        return ResponseEntity.ok().body(speciesObsJoinService.updateSpeciesObsJoin(speciesObsJoinId, newSpeciesObsJoin));
    }


    //Update by species Id
    @PutMapping(path = "/{speciesObsJoinId}/species/{speciesId}")
    SpeciesObsJoinEntity assignSpeciesToSpeciesObsJoin(
            @PathVariable Long speciesId,
            @PathVariable Long speciesObsJoinId
    ) {
        SpeciesObsJoinEntity speciesobsjoin  = speciesObsJoinRepository.findById(speciesObsJoinId).get();
        SpeciesEntity species = speciesRepository.findById(speciesId).get();
        speciesobsjoin.assignSpecies(species);
        return speciesObsJoinRepository.save(speciesobsjoin);
    }

    //Update by observation Id
    @PutMapping(path = "/{speciesObsJoinId}/observation/{observationId}")
    SpeciesObsJoinEntity assignObservationToSpeciesObsJoin(
            @PathVariable Long observationId,
            @PathVariable Long speciesObsJoinId
    ) {
        SpeciesObsJoinEntity speciesobsjoin  = speciesObsJoinRepository.findById(speciesObsJoinId).get();
        ObservationEntity observation = observationRepository.findById(observationId).get();
        speciesobsjoin.assignObservation(observation);
        return speciesObsJoinRepository.save(speciesobsjoin);
    }
}
