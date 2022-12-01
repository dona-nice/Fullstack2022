package com.example.tesy.people;

import com.example.tesy.role.RoleEntity;
import com.example.tesy.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final PeopleRepository peopleRepository;

    private final RoleRepository roleRepository;
    @Autowired
    public PeopleController(PeopleService peopleService,
                            PeopleRepository peopleRepository,
                            RoleRepository roleRepository) {
        this.peopleService = peopleService;
        this.peopleRepository = peopleRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public List<PeopleEntity> getPeople() {
        return peopleService.getPeople();
    }

    @GetMapping (path = "/{peopleId}")
    public PeopleEntity getPeople(@PathVariable("peopleId") Long peopleId){
        PeopleEntity peopleEntity = getPeople().stream()
                .filter(people -> peopleId.equals(people.getPeopleId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Person with id" + peopleId + "does not exist !"));
        return peopleEntity;
    }

    @PostMapping
    public ResponseEntity registerNewPeople(@RequestBody PeopleEntity people) {
        peopleService.addNewPeople(people);
        return ResponseEntity.ok().body(people);
    }

    @DeleteMapping(path = "/{peopleId}")
    public void deletePeople(@PathVariable("peopleId") Long peopleId) {
        peopleService.deletePeople(peopleId);
    }

    @PutMapping(path = "{peopleId}")
    public ResponseEntity editPeople(
            @PathVariable("peopleId") Long peopleId,
            @RequestBody PeopleEntity newPeople){
            return ResponseEntity.ok().body(peopleService.updatePeople(peopleId,newPeople));
    }

    @PutMapping(path = "/{peopleId}/role/{roleId}")
    PeopleEntity assignRoleTopeople(
            @PathVariable Long peopleId,
            @PathVariable Integer roleId
    ) {
        PeopleEntity people = peopleRepository.getReferenceById(peopleId);
        RoleEntity role = roleRepository.findRoleByRoleId(roleId);
        people.setAssignedRole(role);
        return  peopleRepository.save(people);
    }

    @DeleteMapping(path = "/{peopleId}/role/{roleId}")
    PeopleEntity deleteRoleFromPeople(
            @PathVariable Long peopleId,
            @PathVariable Integer roleId
    ) {
        PeopleEntity people = peopleRepository.getReferenceById(peopleId);
        RoleEntity role = roleRepository.getReferenceById(roleId);

        people.delAssignedRole(role);
        return peopleRepository.save(people);
    }

    @PutMapping(path = "/{peopleId}/role")
    public void assignRolesToPeople(@PathVariable("peopleId") Long peopleId,
                                    @RequestBody Map<String, Object>[] rolesRaw) throws ParseException {
        PeopleEntity people = peopleRepository.getReferenceById(peopleId);
        people.clearAssignedRoles();
        for (Map<String, Object> roleRaw: rolesRaw){
            String stringToConvert = String.valueOf(roleRaw.get("roleId"));
            Integer roleId = Integer.parseInt(stringToConvert);
            RoleEntity role = roleRepository.findRoleByRoleId(roleId);
            people.setAssignedRole(role);
        }
        peopleRepository.save(people);
    }

}
