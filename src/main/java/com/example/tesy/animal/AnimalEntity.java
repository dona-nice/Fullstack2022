package com.example.tesy.animal;

import com.example.tesy.observation.ObservationEntity;
//import com.example.tesy.people.PeopleEntity;
import com.example.tesy.species.SpeciesEntity;
import com.example.tesy.status.StatusEntity;
import com.example.tesy.inclasses.InclassesEntity;
import com.example.tesy.treatment.TreatmentEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
//import java.lang.reflect.Type;
import java.util.*;

@Table
@Entity(name="Animal")
public class AnimalEntity {
    @Id
    @SequenceGenerator(
            name = "animal_sequence",
            sequenceName = "animal_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "animal_sequence"
    )

    private Long animalId;

    @SequenceGenerator(
            name = "tesyId_sequence",
            sequenceName = "tesyId_sequence",
            allocationSize = 1
    )
    private String tesyId;

    private Date inDate;

    private Date inTesyDate;

    private Date outTesyDate;

    // Origin of the animal
    private String fromWhere;

    // Name of the cat population
    private String population;

    // Why the animal was captured
    private String reason;

    //Calling name of the animal
    private String callingNameOfTheAnimal;

    // Identification microchip.
    private String microchipNumber;



    private String ageClass;

    //age_class String At this point we're hardcoding this one: pregnant, adult, young, dam. In Finnish: tiine, aikuinen, pentu, emo
    // Age of the animal. Can be either number or text
    private Number animalAge;

    //sex String Well, since there are only four options it's safe to hardcode this one. (male, female, probably male, probably female)

    // Colorcodes are quite complicated issue. We can use string for this.
    private String animalColor;

    //Breed of the animal
    private String breedAnimal;

    //The reason why euthanized
    private String euthanizedReasons;

    //Information for Sey statistics. Written by the user.
    private String seyStatistics;

    // Extra notes
    private String extraNotes;

    //create_date Date When the record is created. Time from the server. Never changed.
    @CreatedDate
    private Date createDate;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "speciesID")
    private SpeciesEntity species;

//    private List<TreatmentEntity> treatmentsList = new ArrayList<TreatmentEntity>();

    public AnimalEntity() {

    }




    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name ="statusId")
    private StatusEntity status;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name ="inclassId")
    private InclassesEntity inclass;


    public AnimalEntity(Long animalId,
     String tesyId,
      Date inDate,
       Date inTesyDate,
        Date outTesyDate,
         String fromWhere,
          String population,
           String reason,
            String callingNameOfTheAnimal,
             String microchipNumber,
              Number animalAge,
               String animalColor,
                String breedAnimal,
                 String euthanizedReasons,
                  String seyStatistics,
                   String extraNotes,
                    Date createDate,
                     SpeciesEntity species,
                      List<TreatmentEntity> treatmentsList,
                       List<ObservationEntity> observationsList
            /*, PeopleEntity people*/,
             StatusEntity status,
              InclassesEntity inclass) {
        this.animalId = animalId;
        this.tesyId = tesyId;
        this.inDate = inDate;
        this.inTesyDate = inTesyDate;
        this.outTesyDate = outTesyDate;
        this.fromWhere = fromWhere;
        this.population = population;
        this.reason = reason;
        this.callingNameOfTheAnimal = callingNameOfTheAnimal;
        this.microchipNumber = microchipNumber;
        this.animalAge = animalAge;
        this.animalColor = animalColor;
        this.breedAnimal = breedAnimal;
        this.euthanizedReasons = euthanizedReasons;
        this.seyStatistics = seyStatistics;
        this.extraNotes = extraNotes;
        this.createDate = createDate;
        this.species = species;
//        this.treatmentsList = treatmentsList;
//        this.observationsList = observationsList;
        // this.people = people;
        this.status = status;
        this.inclass = inclass;
    }

    public AnimalEntity(String tesyId, Date inDate, Date inTesyDate, Date outTesyDate, String fromWhere, String population, String reason, String callingNameOfTheAnimal, String microchipNumber, Number animalAge, String animalColor, String breedAnimal, String euthanizedReasons, String seyStatistics, String extraNotes, Date createDate, SpeciesEntity species, List<TreatmentEntity> treatmentsList, List<ObservationEntity> observationsList
            /*, PeopleEntity people*/, StatusEntity status, InclassesEntity inclass) {
        this.tesyId = tesyId;
        this.inDate = inDate;
        this.inTesyDate = inTesyDate;
        this.outTesyDate = outTesyDate;
        this.fromWhere = fromWhere;
        this.population = population;
        this.reason = reason;
        this.callingNameOfTheAnimal = callingNameOfTheAnimal;
        this.microchipNumber = microchipNumber;
        this.animalAge = animalAge;
        this.animalColor = animalColor;
        this.breedAnimal = breedAnimal;
        this.euthanizedReasons = euthanizedReasons;
        this.seyStatistics = seyStatistics;
        this.extraNotes = extraNotes;
        this.createDate = createDate;
        this.species = species;
//        this.treatmentsList = treatmentsList;
//        this.observationsList = observationsList;
        //   this.people = people;
        this.status = status;
        this.inclass = inclass;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public String getAgeClass() {
        return ageClass;
    }

    @JsonBackReference


    public String getTesyId() {
        return tesyId;
    }

    public void setTesyId(String tesyId) {
        this.tesyId = tesyId;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Date getInTesyDate() {
        return inTesyDate;
    }

    public void setInTesyDate(Date inTesyDate) {
        this.inTesyDate = inTesyDate;
    }

    public Date getOutTesyDate() {
        return outTesyDate;
    }

    public void setOutTesyDate(Date outTesyDate) {
        this.outTesyDate = outTesyDate;
    }

    public String getFromWhere() {
        return fromWhere;
    }

    public void setFromWhere(String fromWhere) {
        this.fromWhere = fromWhere;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCallingNameOfTheAnimal() {
        return callingNameOfTheAnimal;
    }

    public void setCallingNameOfTheAnimal(String callingNameOfTheAnimal) {
        this.callingNameOfTheAnimal = callingNameOfTheAnimal;
    }

    public String getMicrochipNumber() {
        return microchipNumber;
    }

    public void setMicrochipNumber(String microchipNumber) {
        this.microchipNumber = microchipNumber;
    }

    public Number getAnimalAge() {
        return animalAge;
    }

    public void setAnimalAge(Number animalAge) {
        this.animalAge = animalAge;
    }

    public String getAnimalColor() {
        return animalColor;
    }

    public void setAnimalColor(String animalColor) {
        this.animalColor = animalColor;
    }

    public String getBreedAnimal() {
        return breedAnimal;
    }

    public void setBreedAnimal(String breedAnimal) {
        this.breedAnimal = breedAnimal;
    }

    public String getEuthanizedReasons() {
        return euthanizedReasons;
    }

    public void setEuthanizedReasons(String euthanizedReasons) {
        this.euthanizedReasons = euthanizedReasons;
    }

    public String getSeyStatistics() {
        return seyStatistics;
    }

    public void setSeyStatistics(String seyStatistics) {
        this.seyStatistics = seyStatistics;
    }

    public String getExtraNotes() {
        return extraNotes;
    }

    public void setExtraNotes(String extraNotes) {
        this.extraNotes = extraNotes;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }

    public SpeciesEntity getSpecies() {
        return species;
    }

    public void setSpecies(SpeciesEntity species) {
        this.species = species;
    }

    public InclassesEntity getInclass() {
        return inclass;
    }

    public void setInclass(InclassesEntity inclass) {
        this.inclass = inclass;
    }





    @Override
    public String toString() {
        return "AnimalEntity{" +
                "animalId=" + animalId +
                ", tesy='" + tesyId + '\'' +
                ", inDate=" + inDate +
                ", inTesyDate=" + inTesyDate +
                ", outTesyDate=" + outTesyDate +
                ", fromWhere='" + fromWhere + '\'' +
                ", population='" + population + '\'' +
                ", reason='" + reason + '\'' +
                ", callingNameOfTheAnimal='" + callingNameOfTheAnimal + '\'' +
                ", microchipNumber='" + microchipNumber + '\'' +
                ", animalAge=" + animalAge +
                ", animalColor='" + animalColor + '\'' +
                ", breedAnimal='" + breedAnimal + '\'' +
                ", euthanizedReasons='" + euthanizedReasons + '\'' +
                ", seyStatistics='" + seyStatistics + '\'' +
                ", extraNotes='" + extraNotes + '\'' +
                ", createDate=" + createDate +
                ", species=" + species +
//                ", treatmentsList=" + treatmentsList +
//                ", observationsList=" + observationsList +
                // ", people=" + people +
                ", status=" + status +
                '}';
    }


}
