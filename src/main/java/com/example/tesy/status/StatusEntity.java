package com.example.tesy.status;

import javax.persistence.*;

@Table
@Entity(name = "Status")
public class StatusEntity {
    @Id
    @SequenceGenerator(
            name = "status_sequence",
            sequenceName = "status_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "status_sequence"
    )
    private Long statusId;
    private String name;

    public StatusEntity(Long statusId, String name) {
        this.statusId = statusId;
        this.name = name;
    }

    public StatusEntity(String name) {
        this.name = name;
    }

    public StatusEntity() {

    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StatusEntity{" +
                "statusId=" + statusId +
                ", name='" + name + '\'' +
                '}';
    }
}
