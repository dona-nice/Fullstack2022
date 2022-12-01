package com.example.tesy.inclasses;

import javax.persistence.*;

@Table
@Entity(name="Inclasses")
public class InclassesEntity {
    @Id
    @SequenceGenerator(
            name = "inclasses_sequence",
            sequenceName = "inclasses_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "inclasses_sequence"
    )

    private Long inclassId;

    private String className;

    public InclassesEntity(Long inclassId, String className) {
        this.inclassId = inclassId;
        this.className = className;
    }

    public InclassesEntity(String className) {
        this.className = className;
    }

    public InclassesEntity() {

    }

    public Long getInclassId() {
        return inclassId;
    }

    public void setInclassId(Long inclassId) {
        this.inclassId = inclassId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "InclassesEntity{" +
                "inclassId=" + inclassId +
                ", name='" + className + '\'' +
                '}';
    }
}
