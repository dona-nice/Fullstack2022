package com.example.tesy.species;

import javax.persistence.*;

@Table
@Entity(name="Species")
public class SpeciesEntity {
    @Id
    @SequenceGenerator(
            name = "species_sequence",
            sequenceName = "species_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "species_sequence"
    )

    private Long speciesId;
    private String name;

    public SpeciesEntity(Long speciesId, String name) {
        this.speciesId = speciesId;
        this.name = name;
    }

    public SpeciesEntity(String name) {
        this.name = name;
    }

    public SpeciesEntity() {

    }

    public Long getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(Long speciesId) {
        this.speciesId = speciesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SpeciesEntity{" +
                "speciesId=" + speciesId +
                ", name='" + name + '\'' +
                '}';
    }
}
