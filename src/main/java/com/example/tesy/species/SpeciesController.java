package com.example.tesy.species;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/species")
public class SpeciesController {

    private final SpeciesService speciesService;
    private final SpeciesRepository speciesRepository;

    @Autowired
    public SpeciesController(SpeciesService speciesService, SpeciesRepository speciesRepository) {
        this.speciesService = speciesService;
        this.speciesRepository = speciesRepository;
    }

    @GetMapping
    public List<SpeciesEntity> getSpeciesEntitys() {
        return speciesService.getSpecies();
    }

    // Get by Id
    @GetMapping(path = "{speciesId}")
    public SpeciesEntity findSpeciesById(@PathVariable (value =
            "speciesId") Long speciesId) {
        return this.speciesRepository.findById(speciesId).orElse(null);
    }
    @PostMapping
    public SpeciesEntity registerNewSpecies(
            @RequestBody SpeciesEntity speciesEntity) {
        return this.speciesRepository.save(speciesEntity);
    }

    @DeleteMapping(path = "{speciesId}")
    public void deleteSpecies(
            @PathVariable("speciesId") Long speciesId) {
        speciesService.deleteSpecies(speciesId);
    }

    @PutMapping(path = "/{speciesId}")
    public ResponseEntity<SpeciesEntity> updateSpecies(
            @PathVariable("speciesId") Long speciesId,
            @RequestBody SpeciesEntity newSpecies) {
        return ResponseEntity.ok().body(speciesService.updateSpecies(speciesId, newSpecies));
    }
}
