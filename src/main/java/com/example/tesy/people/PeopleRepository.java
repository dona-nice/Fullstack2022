package com.example.tesy.people;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<PeopleEntity,Long> {

    Optional<PeopleEntity> findPeopleByUsername (String username);

}
