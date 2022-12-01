package com.example.tesy.inclasses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InclassesRepository extends JpaRepository<InclassesEntity,Long> {
    Optional<InclassesEntity> findInclassByClassName(String className);
}
