package com.example.tesy.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
//import java.util.Optional;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "api/animal")
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }
    
    // Create animal
    @PostMapping
    public AnimalEntity addAnimal (@RequestBody AnimalEntity animal){
        return this.animalRepository.save(animal);
    }

    // Get all
    @GetMapping
    public List<AnimalEntity> findAnimals (){
        return animalService.getAnimalEntities();
    }

    // Get by id
    @GetMapping("/{id}")
    public AnimalEntity findAnimalById(@PathVariable (value = "id") long animalId){
        return this.animalRepository.findById(animalId).orElse(null);
    }

    // Update by id
    @PutMapping("/{id}")
    public AnimalEntity updateAnimal (@RequestBody AnimalEntity animal, @PathVariable("id") long animalId) {
        AnimalEntity currentAnimal = this.animalRepository.findById(animalId).
                orElse(null);
        currentAnimal.setInDate(animal.getInDate());
        currentAnimal.setInTesyDate(animal.getInTesyDate());
        currentAnimal.setOutTesyDate(animal.getOutTesyDate());
        currentAnimal.setFromWhere(animal.getFromWhere());
        currentAnimal.setPopulation(animal.getPopulation());
        currentAnimal.setReason(animal.getReason());
        currentAnimal.setCallingNameOfTheAnimal(animal.getCallingNameOfTheAnimal());
        currentAnimal.setMicrochipNumber(animal.getMicrochipNumber());
        currentAnimal.setAnimalAge(animal.getAnimalAge());
        currentAnimal.setAnimalColor(animal.getAnimalColor());
        currentAnimal.setBreedAnimal(animal.getBreedAnimal());
        currentAnimal.setEuthanizedReasons(animal.getEuthanizedReasons());
        currentAnimal.setSeyStatistics(animal.getSeyStatistics());
        currentAnimal.setExtraNotes(animal.getExtraNotes());
        currentAnimal.setSpecies(animal.getSpecies());
        currentAnimal.setStatus(animal.getStatus());
        currentAnimal.setInclass(animal.getInclass());
        return this.animalRepository.save(currentAnimal);
    }
    
    // Delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<AnimalEntity> deleteAnimal(@PathVariable("id") long animalId) {
        AnimalEntity currentAnimal = this.animalRepository.findById(animalId).orElse(null);
        this.animalRepository.delete(currentAnimal);
        return ResponseEntity.ok().build();
    }

    // Get count of admitted animals
    @PostMapping("/total")
    public Map<String, Object> getTotals(@RequestBody Map<String, Object> payload) throws ParseException{
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse((String) payload.get("startDate"));
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse((String) payload.get("endDate"));
        long total = animalRepository.countByInTesyDateBetween(startDate, endDate);
        Map<String, Object> returnTotal = new HashMap<String, Object>();
        returnTotal.put("totalAnimals", total);
        return returnTotal;
    }

    // Get list of daily counts of admitted animals
    @PostMapping("/totaldaily")
    public List<Map<String, Object>> getDailyTotals(@RequestBody Map<String, Object> payload) throws ParseException{
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse((String) payload.get("startDate"));
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse((String) payload.get("endDate"));

        ArrayList<Map<String, Object>> list = new ArrayList();
        while (TimeUnit.DAYS.convert(endDate.getTime() - startDate.getTime(), TimeUnit.MILLISECONDS) >= 0){
            long total = animalRepository.countByInTesyDate(startDate);

            Map<String, Object> returnTotal = new HashMap<String, Object>();
            returnTotal.put("totalAnimals", total);
            returnTotal.put("date", new SimpleDateFormat("yyyy-MM-dd").format(startDate));
            list.add(returnTotal);
                
            startDate = new Date(startDate.getTime() + TimeUnit.DAYS.toMillis( 1 ));
        }
        return list;
    }
}
