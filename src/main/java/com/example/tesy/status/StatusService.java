package com.example.tesy.status;

import com.example.tesy.species.SpeciesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StatusService {
    
    private final StatusRepository statusRepository;

    @Autowired
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<StatusEntity> getStatus() {
        return statusRepository.findAll();
    }

    public void addNewStatus(StatusEntity statusEntity) {
    }

    public void deleteStatus(Long statusId) {
        boolean exists = statusRepository.existsById(statusId);
        if (!exists) {
            throw new IllegalStateException("This status with Id " + statusId + " do not exists!");
        }
        statusRepository.deleteById(statusId);
    }

    @Transactional
    public StatusEntity updateStatus(Long statusId, StatusEntity newStatus) {
        StatusEntity updateStatus = statusRepository.findById(statusId).orElseThrow(()
                -> new IllegalStateException("Status with ID " + statusId + " do not exists!"));
        if  (newStatus.getName() !=null &&
                !Objects.equals(updateStatus.getName(),
                        newStatus.getName())) {
            Optional<SpeciesEntity> statusEntityOptional =
                    statusRepository.findStatusByName(newStatus.getName());
            if (statusEntityOptional.isPresent()) {
                throw new IllegalStateException("This status name is Taken!");
            }
            updateStatus.setName(newStatus.getName());
        }
        return updateStatus;
    }
}
