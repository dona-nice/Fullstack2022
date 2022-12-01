package com.example.tesy.observationtype;

import com.example.tesy.species.SpeciesEntity;
import com.example.tesy.status.StatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ObservationTypeService {
    private final ObservationTypeRepository observationTypeRepository;

    @Autowired
    public ObservationTypeService(ObservationTypeRepository observationTypeRepository) {
        this.observationTypeRepository = observationTypeRepository;
    }
    
    public List<ObservationTypeEntity> getObservationType() {
        return observationTypeRepository.findAll();
    }

    public void deleteObservationType(Long typeId) {
        boolean exists = observationTypeRepository.existsById(typeId);
        if (!exists) {
            throw new IllegalStateException("This observation type with Id " + typeId + " do not exists!");
        }
        observationTypeRepository.deleteById(typeId);
    }

    @Transactional
    public ObservationTypeEntity updateObservationType(Long typeId, ObservationTypeEntity newObservationType) {
        ObservationTypeEntity updateObservationType = observationTypeRepository.findById(typeId).orElseThrow(()
                -> new IllegalStateException("Observation type with ID " + typeId + " do not exists!"));
        if  (newObservationType.getObsTypeName() !=null &&
                !Objects.equals(updateObservationType.getObsTypeName(),
                        newObservationType.getObsTypeName())) {
            updateObservationType.setObsTypeName(newObservationType.getObsTypeName());
        }
        return updateObservationType;
    }
}
