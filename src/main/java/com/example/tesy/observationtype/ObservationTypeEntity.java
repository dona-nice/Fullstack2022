package com.example.tesy.observationtype;

import com.example.tesy.observation.ObservationEntity;
import com.example.tesy.speciesobsjoin.SpeciesObsJoinEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity(name = "ObservationType")
public class ObservationTypeEntity {
    @Id
    @SequenceGenerator(
            name= "observationtype_sequence",
            sequenceName = "observationtype_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "observationtype_sequence"
    )

    private Long typeId;

    private String obsTypeName;

    // Relation
    @ManyToMany
    @JoinTable(
            name = "observation_made",
            joinColumns = @JoinColumn(name = "typeId"),
            inverseJoinColumns = @JoinColumn(name = "speciesObsJoinId")
    )
    private Set<SpeciesObsJoinEntity> speciesObsJoinIncluded = new HashSet<>();

    public Set<SpeciesObsJoinEntity> getSpeciesObsJoinIncluded() {
        return speciesObsJoinIncluded;
    }

    public ObservationTypeEntity(Long typeId,
                                 String obsTypeName,
                                 Set<SpeciesObsJoinEntity> speciesObsJoinIncluded) {
        this.typeId = typeId;
        this.obsTypeName= obsTypeName;
        this.speciesObsJoinIncluded= speciesObsJoinIncluded;
    }

    public ObservationTypeEntity(String obsTypeName,
                                 Set<SpeciesObsJoinEntity> speciesObsJoinIncluded) {
        this.obsTypeName = obsTypeName;
        this.speciesObsJoinIncluded= speciesObsJoinIncluded;
    }

    public ObservationTypeEntity() {

    }

    public Long getTypeId() {
        return typeId;
    }

    public String getObsTypeName() {
        return obsTypeName;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public void setObsTypeName(String obsTypeName) {
        this.obsTypeName = obsTypeName;
    }

    public void assignSpeciesObsJoin(SpeciesObsJoinEntity speciesObsJoin) { speciesObsJoinIncluded.add(speciesObsJoin);
    }

    @Override
    public String toString() {
        return "ObservationTypeEntity{" +
                "typeId=" + typeId +
                ", obsType_name=" + obsTypeName +
                '}';
    }
}
