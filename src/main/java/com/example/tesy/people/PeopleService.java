package com.example.tesy.people;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class PeopleService {

    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public PeopleService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<PeopleEntity> getPeople(){
        return peopleRepository.findAll();
    }


    public PeopleEntity addNewPeople(PeopleEntity people) {
        System.out.println(people);
        String rowPasswd =people.getPasswd();
        people.setPasswd(passwordEncoder.encode(rowPasswd));

        Optional<PeopleEntity> peopleOptional = peopleRepository.findPeopleByUsername(people.getUsername());
        if (peopleOptional.isPresent()) {
            throw new IllegalStateException("This Username is registered before !");
        }
        if ( rowPasswd != null &&
                rowPasswd.length() > 0 )
                {people.setPasswd(passwordEncoder.encode(rowPasswd));
        } else throw new IllegalStateException("The password can not be empty!");
        peopleRepository.save(people);
        return people;
    }

    public void deletePeople(Long peopleId) {
        boolean exists = peopleRepository.existsById(peopleId);
        if (!exists) {
            throw new IllegalStateException("This Person whit id "+ peopleId+" do not exists!");
        }
        peopleRepository.deleteById(peopleId);
    }

    @Transactional
    public PeopleEntity updatePeople(Long peopleId, PeopleEntity newPeople){

        PeopleEntity updatePeople = peopleRepository.findById(peopleId)
                .orElseThrow(() -> new IllegalStateException("person with ID " + peopleId + " do not exists!"));

        if ( newPeople.getUsername() != null &&
                newPeople.getUsername().length() > 0 &&
                !Objects.equals(updatePeople.getUsername(), newPeople.getUsername())) {
                Optional <PeopleEntity> peopleEntityOptional = peopleRepository.findPeopleByUsername(newPeople.getUsername());
            if (peopleEntityOptional.isPresent()) {
                throw new IllegalStateException("This username is registered before !");
            }
            updatePeople.setUsername(newPeople.getUsername());
        }

        if ( newPeople.getPasswd() != null &&
                newPeople.getPasswd().length() > 0 &&
                !Objects.equals(updatePeople.getPasswd(), newPeople.getPasswd())) {
            updatePeople.setPasswd(passwordEncoder.encode(newPeople.getPasswd()));
        }

        if ( newPeople.getRealName() != null &&
                newPeople.getRealName().length() > 0 &&
                !Objects.equals(updatePeople.getRealName(), newPeople.getRealName())) {
            updatePeople.setRealName(newPeople.getRealName());
        }
        peopleRepository.save(updatePeople);
        return updatePeople;

    }

}
