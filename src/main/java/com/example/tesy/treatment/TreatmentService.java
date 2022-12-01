package com.example.tesy.treatment;

import com.example.tesy.animal.AnimalEntity;
import com.example.tesy.animal.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.DatabaseMetaData;
import java.util.Date;
import java.util.List;

@Service
public class TreatmentService {
    private final TreatmentRepository treatmentRepository;

    @Autowired
    public TreatmentService (TreatmentRepository treatmentRepository){
        this.treatmentRepository = treatmentRepository;

    }
    public void addNewTreatmentEntity(TreatmentEntity treatmentEntity){

    }

    public List<TreatmentEntity> getTreatmentEntitys(){
        return treatmentRepository.findAll();
    }

    public void deleteTreatmentEntity(Long treatmentId){


    }
    public void updateTreatmentEntity(Long treatmentId, Long animalId, Long peopleId, Date date,
                                      String description, Double amountOfMedication){

    }


}
