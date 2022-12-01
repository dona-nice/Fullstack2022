package com.example.tesy.observation;

import com.example.tesy.animal.AnimalEntity;
import com.example.tesy.animal.AnimalRepository;
import com.example.tesy.observationtype.ObservationTypeEntity;
import com.example.tesy.observationtype.ObservationTypeRepository;
import com.example.tesy.people.PeopleEntity;
import com.example.tesy.people.PeopleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/observation")
public class ObservationController {

    private final ObservationService observationService;
    private final ObservationRepository observationRepository;
    private final AnimalRepository animalRepository;
    private final PeopleRepository peopleRepository;
    private final ObservationTypeRepository observationTypeRepository;

    public ObservationController(ObservationService observationService,
                                 ObservationRepository observationRepository,
                                 AnimalRepository animalRepository,
                                 PeopleRepository peopleRepository,
                                 ObservationTypeRepository observationTypeRepository) {
        this.observationService = observationService;
        this.observationRepository = observationRepository;
        this.animalRepository = animalRepository;
        this.peopleRepository = peopleRepository;
        this.observationTypeRepository = observationTypeRepository;
    }

    @PostMapping
    public ObservationEntity registerNewObservation(@RequestBody ObservationEntity observation) {
        return this.observationRepository.save(observation);
    }

    // Get all
    @GetMapping
    public List<ObservationEntity> getObservation() {
        return observationService.getObservations();
    }

    // Get by Id
    @GetMapping(path = "{observationId}")
    public ObservationEntity findObservationById(@PathVariable (value =
            "observationId") Long observationId) {
        return this.observationRepository.findById(observationId).orElse(null);
    }

    // Delete by Id
    @DeleteMapping(path = "{observationId}")
    public void deleteObservation(@PathVariable("observationId") Long observationId) {
        observationService.deleteObservation(observationId);
    }

    //Update by observation Id
    @PutMapping(path = "/{observationId}")
    public ResponseEntity updateObservation(
            @PathVariable("observationId") Long observationId,
            @RequestBody ObservationEntity newObservation) {
        return ResponseEntity.ok().body(observationService.updateObservation(
                observationId, newObservation));
    }

    //Update by animal Id, relational data reference to animal class
    @PutMapping(path = "/{observationId}/animal/{animalId}")
    AnimalEntity assignObservationToAnimal(
            @PathVariable Long observationId,
            @PathVariable Long animalId
    ) {
        ObservationEntity observation = observationRepository.getReferenceById(observationId);
        AnimalEntity animal= animalRepository.findAnimalByAnimalId(animalId);
        observation.assignAnimal(animal);
        return  animalRepository.save(animal);
    }

    // Update by people Id, relational data reference to people class
    @PutMapping(path = "/{observationId}/people/{peopleId}")
    ObservationEntity assignPeopleToObservation(
            @PathVariable Long peopleId,
            @PathVariable Long observationId
    ) {
        ObservationEntity observation = observationRepository.findById(observationId).get();
        PeopleEntity people = peopleRepository.findById(peopleId).get();
        observation.assignPeople(people);
        return observationRepository.save(observation);
    }

    // Update Observation by Observation Type Id. Relational data reference to Observation Type class.
    @PutMapping(path = "/{observationId}/observationtype/{typeId}")
    ObservationEntity assignObservationTypeToObservation(
            @PathVariable Long observationId,
            @PathVariable Long typeId
    ) {
        ObservationEntity observation = observationRepository.findById(observationId).get();
        ObservationTypeEntity observationType = observationTypeRepository.findById(typeId).get();
        observation.assignObservationType(observationType);
        return observationRepository.save(observation);
    }
}
