package com.example.tesy.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
//import java.util.Objects;
//import java.util.Optional;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalService (AnimalRepository animalRepository){
        this.animalRepository = animalRepository;

    }
    public void addNewAnimalEntity(AnimalEntity animalEntity){

    }
    public List<AnimalEntity> getAnimalEntities(){
        return animalRepository.findAll();
    }
    public void deleteAnimalEntity(Long animalId){

    }

    public void updateAnimalEntity(Long animalId, Date inDate,Date inTesyDate,
                                   Date outTesyDate,String fromWhere,String population,
                                   String reason,String callingNameOfTheAnimal ,
                                   String microchipNumber,String animalAge, String animalColor,
                                   String breedAnimal,String euthanizedReasons,
                                   String seyStatistics, String extraNotes, String species,
                                   String status){

    }
}
