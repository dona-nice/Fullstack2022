package com.example.tesy.inclasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/inclasses")
public class InclassesController {

    private final InclassesService inclassesService;
    private final InclassesRepository inclassesRepository;

    @Autowired
    public InclassesController(InclassesService inclassesService, InclassesRepository inclassesRepository) {
        this.inclassesService = inclassesService;
        this.inclassesRepository = inclassesRepository;
    }

    @GetMapping
    public List<InclassesEntity> getInClassesEntity() {
        return inclassesService.getInClassesEntity();
    }

    // Get by Id
    @GetMapping(path = "/{inclassId}")
    public InclassesEntity findInclassById(@PathVariable (value =
            "inclassId") Long inclassId) {
        return this.inclassesRepository.findById(inclassId).orElse(null);
    }

    @PostMapping
    public ResponseEntity registerNewInClassesEntity(
            @RequestBody InclassesEntity inclassesEntity) {
        inclassesService.addNewInClassesEntity(inclassesEntity);
        return ResponseEntity.ok().body(inclassesEntity);
    }

    @DeleteMapping(path = "/{inclassId}")
    public void deleteInClassesEntity(
            @PathVariable("inclassId") Long inclassId) {
        inclassesService.deleteInClassesEntity(inclassId);
    }

    @PutMapping(path = "/{inclassId}")
    public ResponseEntity updateInclassesEntity(
            @PathVariable("inclassId") Long inclassId,
            @RequestBody InclassesEntity newClass) {
        return ResponseEntity.ok().body(inclassesService.updateInClassesEntity(inclassId, newClass));
    }
}
