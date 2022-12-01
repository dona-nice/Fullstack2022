package com.example.tesy.treatment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/treatment")
public class TreatmentController {
    @Autowired
    private TreatmentRepository treatmentRepository;

    // Create treatment
    @PostMapping
    public TreatmentEntity addTreatment (@RequestBody TreatmentEntity treatment){
        return this.treatmentRepository.save(treatment);
    }

    // Get all treatments
    @GetMapping
    public List<TreatmentEntity> findTreatments (){
        return treatmentRepository.findAll();
    }
    //Get treatment by id
    @GetMapping("/{id}")
    public TreatmentEntity findAnimalById(@PathVariable (value = "id") long treatmentId){
        return this.treatmentRepository.findById(treatmentId).orElse(null);
    }

    // put treatment

    @PutMapping("/{id}")
    public TreatmentEntity updateTreatment (@RequestBody TreatmentEntity treatment, @PathVariable("id") long treatmentId) {
        TreatmentEntity currentTreatment = this.treatmentRepository.findById(treatmentId).
                orElse(null);
        currentTreatment.setAnimal(treatment.getAnimal());
        currentTreatment.setDate(treatment.getDate());
        currentTreatment.setDescription(treatment.getDescription());
        //currentTreatment.setPeople(treatment.getPeople());
        currentTreatment.setAmountOfMedication(treatment.getAmountOfMedication());
        return this.treatmentRepository.save(currentTreatment);
    }
    // Delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<TreatmentEntity> deleteTreatment(@PathVariable("id")long treatmentId){
        TreatmentEntity currentTreatment = this.treatmentRepository.findById(treatmentId).orElse(null);
        this.treatmentRepository.delete(currentTreatment);
        return ResponseEntity.ok().build();

    }



}
