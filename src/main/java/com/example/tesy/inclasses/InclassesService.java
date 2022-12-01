package com.example.tesy.inclasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class InclassesService {

    private final InclassesRepository inclassesRepository;

    @Autowired
    public InclassesService(InclassesRepository inclassesRepository) {

        this.inclassesRepository = inclassesRepository;
    }

    public List<InclassesEntity> getInClassesEntity() { return inclassesRepository.findAll(); }

    public InclassesEntity addNewInClassesEntity(InclassesEntity inclassesEntity) {
        Optional<InclassesEntity> inclassesOptional =
                inclassesRepository.findInclassByClassName(inclassesEntity.getClassName());
        if (inclassesOptional.isPresent()) {
            throw new IllegalStateException("This inclass is taken!");
        }
        if (inclassesEntity.getClassName() != null &&
        inclassesEntity.getClassName().length() > 0) {
            inclassesRepository.save(inclassesEntity);
        } else throw new IllegalStateException("The class name is taken!");
        return inclassesEntity;
    }

    @Transactional
    public InclassesEntity updateInClassesEntity(Long inclassId, InclassesEntity newClass) {
        InclassesEntity updateInClasses = inclassesRepository.findById(inclassId).orElseThrow(()
                 -> new IllegalStateException("Class with ID" + inclassId + "do not exists!"));
        if  (newClass.getClassName() !=null &&
                !Objects.equals(updateInClasses.getClassName(),
                        newClass.getClassName())) {
            Optional<InclassesEntity> inclassesEntityOptional =
                    inclassesRepository.findInclassByClassName(newClass.getClassName());
            if (inclassesEntityOptional.isPresent()) {
                throw new IllegalStateException("This class name is Taken!");
            }
            updateInClasses.setClassName(newClass.getClassName());
        }
        return updateInClasses;
    }

    public void deleteInClassesEntity(Long inclassId) {
        boolean exists = inclassesRepository.existsById(inclassId);
        if (!exists) {
            throw new IllegalStateException("This class with Id " + inclassId + " do not exists!");
        }
        inclassesRepository.deleteById(inclassId);
    }
}
