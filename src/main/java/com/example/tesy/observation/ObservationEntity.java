package com.example.tesy.observation;

import com.example.tesy.animal.AnimalEntity;
import com.example.tesy.observationtype.ObservationTypeEntity;
import com.example.tesy.people.PeopleEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity(name = "Observation")
public class ObservationEntity {

    @Id
    @SequenceGenerator(
            name="observation_sequence",
            sequenceName = "observation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "observation_sequence"
    )

    private Long observationId;

    private Date date;

    // What observation reveals
    private String value;

    //Relation with animal
    @ManyToOne(
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "animalId")
    private AnimalEntity animal;


    // Relation with observation type
    @ManyToMany
    @JoinTable(
            name = "observation_of_species",
            joinColumns = @JoinColumn(name = "observationId"),
            inverseJoinColumns = @JoinColumn(name = "typeId")
    )
    private Set<ObservationTypeEntity> observationIncluded =new HashSet<>();

    // Relation with people
    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "peopleId")
    private PeopleEntity people;

    public ObservationEntity(
            Long observationId,
            Date date,
            String value,
            AnimalEntity animal,
            PeopleEntity people,
            Set<ObservationTypeEntity> observationIncluded) {
        this.observationId = observationId;
        this.date = date;
        this.value = value;
        this.animal = animal;
        this.people = people;
        this.observationIncluded = observationIncluded;
    }

    public ObservationEntity(
            Date date,
            String value,
            AnimalEntity animal,
            PeopleEntity people,
            Set<ObservationTypeEntity> observationIncluded) {
        this.date = date;
        this.value = value;
        this.animal = animal;
        this.people = people;
        this.observationIncluded = observationIncluded;
    }

    public ObservationEntity() {

    }

    public Long getObservationId() {
        return observationId;
    }

    public void setObservationId(Long observationId) {
        this.observationId = observationId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public AnimalEntity getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalEntity animal) {
        this.animal = animal;
    }

    public Set<ObservationTypeEntity> getObservationIncluded() {
        return observationIncluded;
    }

    public void setObservationIncluded(Set<ObservationTypeEntity> observationIncluded) {
        this.observationIncluded = observationIncluded;
    }

    public PeopleEntity getPeople() {
        return people;
    }

    public void setPeople(PeopleEntity people) {
        this.people = people;
    }

    public void assignAnimal(AnimalEntity animal) {
        this.animal = animal;
    }

    public void assignPeople(PeopleEntity people) {
        this.people = people;
    }

    public void assignObservationType(ObservationTypeEntity observationType) { observationIncluded.add(observationType);
    }

    @Override
    public String toString() {
        return "ObservationEntity{" +
                "observationId=" + observationId +
                ", animal=" + animal +
                ", people=" + people +
                "' type=" + observationIncluded +
                ", date=" + date +
                ", value=" + value +
                '}';
    }


}

