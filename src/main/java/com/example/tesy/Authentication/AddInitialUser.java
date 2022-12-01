package com.example.tesy.Authentication;


import com.example.tesy.people.PeopleEntity;
import com.example.tesy.people.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AddInitialUser {
   private final PeopleRepository peopleRepository;

   private final PasswordEncoder passwordEncoder;

    @Autowired
    public AddInitialUser(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {

        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public void adduser(){
        PeopleEntity people = new PeopleEntity(100L,

                "admin",passwordEncoder.encode("p1230"),"Tesy Admin"
        );
        if (peopleRepository.findPeopleByUsername(people.getUsername()).isPresent()){}
        else peopleRepository.save(people);
    }

}
