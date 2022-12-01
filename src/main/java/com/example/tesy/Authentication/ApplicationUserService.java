package com.example.tesy.Authentication;

import com.example.tesy.people.PeopleEntity;
import com.example.tesy.people.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationUserService implements UserDetailsService {

    @Autowired
    PeopleRepository peopleRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<PeopleEntity> user = peopleRepository.findPeopleByUsername(username);
            user.orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found",username)));
        return user.map(ApplicationUser::new).get();
    }
}
