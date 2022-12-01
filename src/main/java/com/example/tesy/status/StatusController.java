package com.example.tesy.status;

import com.example.tesy.species.SpeciesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/status")
public class StatusController {

    private final StatusService statusService;
    private final StatusRepository statusRepository;

    @Autowired
    public StatusController(StatusService statusService, StatusRepository statusRepository) {
        this.statusService = statusService;
        this.statusRepository = statusRepository;
    }

    @GetMapping
    public List<StatusEntity> getStatus() {
        return statusService.getStatus();
    }

    // Get by Id
    @GetMapping(path = "{statusId}")
    public StatusEntity findStatusById(@PathVariable (value =
            "statusId") Long statusId) {
        return this.statusRepository.findById(statusId).orElse(null);
    }

    @PostMapping
    public StatusEntity registerNewStatus(
            @RequestBody StatusEntity statusEntity) {
        return this.statusRepository.save(statusEntity);
    }

    @DeleteMapping(path = "{statusId}")
    public void deleteStatus(
            @PathVariable("statusId") Long statusId) {
        statusService.deleteStatus(statusId);
    }

    @PutMapping(path = "{statusId}")
    public ResponseEntity<StatusEntity> updateStatus(
            @PathVariable("statusId") Long statusId,
            @RequestBody StatusEntity newStatus) {
        return ResponseEntity.ok().body(statusService.updateStatus(statusId, newStatus));
    }
}
