package com.example.tesy.observationtype;

import com.example.tesy.speciesobsjoin.SpeciesObsJoinEntity;
import com.example.tesy.speciesobsjoin.SpeciesObsJoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/observationtype")
public class ObservationTypeController {

    private final ObservationTypeService observationTypeService;

    private final ObservationTypeRepository observationTypeRepository;
    private final SpeciesObsJoinRepository speciesObsJoinRepository;

    @Autowired
    public ObservationTypeController(ObservationTypeService observationTypeService, ObservationTypeRepository observationTypeRepository, SpeciesObsJoinRepository speciesObsJoinRepository) {
        this.observationTypeService = observationTypeService;
        this.observationTypeRepository = observationTypeRepository;
        this.speciesObsJoinRepository = speciesObsJoinRepository;
    }

    // Get the list
    @GetMapping
    public List<ObservationTypeEntity> getObservationType() {
        return observationTypeService.getObservationType();
    }

    // Get by Id
    @GetMapping(path = "{typeId}")
    public ObservationTypeEntity findObservationTypeById(@PathVariable (value =
            "typeId") Long typeId) {
        return this.observationTypeRepository.findById(typeId).orElse(null);
    }

    @PostMapping
    public ObservationTypeEntity registerNewObservationTypeEntity(
            @RequestBody ObservationTypeEntity observationType) {
        return observationTypeRepository.save(observationType);
    }

    @DeleteMapping(path = "{typeId}")
    public void deleteObservationType(
            @PathVariable("typeId") Long typeId) {
        observationTypeService.deleteObservationType(typeId);
    }

    //Update by Id
    @PutMapping(path = "{typeId}")
    public ResponseEntity<ObservationTypeEntity> updateObservationType(
            @PathVariable("typeId") Long typeId,
            @RequestBody ObservationTypeEntity newObservationType) {
        return ResponseEntity.ok().body(observationTypeService.updateObservationType(typeId, newObservationType));
    }

    //Update by speciesObsJoin Id
    @PutMapping(path = "/{typeId}/speciesobsjoin/{speciesObsJoinId}")
    ObservationTypeEntity assignSpeciesObsJoinToObservationType(
            @PathVariable Long typeId,
            @PathVariable Long speciesObsJoinId
    ) {
        ObservationTypeEntity observationType = observationTypeRepository.findById(typeId).get();
        SpeciesObsJoinEntity speciesObsJoin = speciesObsJoinRepository.findById(speciesObsJoinId).get();
        observationType.assignSpeciesObsJoin(speciesObsJoin);
        return observationTypeRepository.save(observationType);
    }
}
