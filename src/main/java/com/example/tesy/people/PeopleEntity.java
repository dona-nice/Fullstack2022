package com.example.tesy.people;

import com.example.tesy.role.RoleEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity(name = "People")
@Table(name ="People",
        uniqueConstraints = {
                @UniqueConstraint(name = "username_unique",
                        columnNames = "username")
        })
public class PeopleEntity {
    @Id
    @SequenceGenerator(
            name = "people_sequence",
            sequenceName = "people_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "people_sequence"
    )
    @Column (name = "PeopleId")
    private Long peopleId;

    private String username;

    private String passwd;

    private String realName;

    /*
    // Relation with Observation
    @JsonIgnore
    @OneToMany(mappedBy = "people")
    private Set<ObservationEntity> observationEntity = new HashSet<>();

    public Set<ObservationEntity> getObservationEntity() {
        return observationEntity;
    }

     */


    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "People_Role",
            joinColumns = {@JoinColumn(name = "PeopleId")},
            inverseJoinColumns = {@JoinColumn(name = "RoleId")}
    )
    private Set<RoleEntity> assignedRole = new HashSet<RoleEntity>();

    public PeopleEntity(Long peopleId,
                        String username,
                        String passwd,
                        String realName) {
        this.peopleId = peopleId;
        this.username = username;
        this.passwd = passwd;
        this.realName = realName;
    }

    public PeopleEntity(String username, String passwd, String realName) {
        this.username = username;
        this.passwd = passwd;
        this.realName = realName;
    }

    public PeopleEntity() {

    }

    public Long getPeopleId() {
        return peopleId;
    }

    public  String getUsername() {
        return username;
    }

    public  String getPasswd() {
        return passwd;
    }

    public String getRealName() {
        return realName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setAssignedRole(RoleEntity role) {
        assignedRole.add(role);
    }

    public Set<RoleEntity> getAssignedRole() {
        return assignedRole;
    }

    public void delAssignedRole(RoleEntity role) {
        assignedRole.remove(role);
    }
    public void clearAssignedRoles() {assignedRole.clear();}

    @Override
    public String toString() {
        return "PeopleEntity{" +
                "peopleId=" + peopleId +
                ", username='" + username + '\'' +
                ", passwd='" + passwd + '\'' +
                ", realName='" + realName + '\'' +
                ", assignedRole=" + assignedRole +
                '}';
    }
}
