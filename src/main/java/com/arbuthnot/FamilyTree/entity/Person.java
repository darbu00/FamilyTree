package com.arbuthnot.FamilyTree.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "Persons")
public class Person {

  // Fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;
  @Column(name = "name_first")
  private String firstName;
  @Column(name = "name_last")
  private String lastName;
  @Column(name = "name_middle")
  private String middleName;
  @Column(name = "generation")
  private String generation;
  @Column(name = "name_married")
  private String marriedName;
  @Column(name = "name_nick")
  private String nickName;
  @Column(name = "gender")
  private String gender;
  @JoinColumn(name = "father_id")
  @OneToOne(targetEntity = Person.class, fetch = FetchType.EAGER)
  private Person father;
  // @Column(name = "father")
  @Transient
  private Integer personFatherId;
  @JoinColumn(name = "mother_id")
  @OneToOne(targetEntity = Person.class, fetch = FetchType.EAGER)
  private Person mother;
  // @Column(name = "mother")
  @Transient
  private Integer personMotherId;
  @JoinColumn(name = "current_location_id", referencedColumnName = "id")
  @ManyToOne(targetEntity = Location.class, fetch = FetchType.EAGER)
  private Location currentLocation;
  @Transient
  private Integer personCurrentLocationId;
  @JoinColumn(name = "birth_id", referencedColumnName = "id")
  @ManyToOne(targetEntity = Birth.class, fetch = FetchType.EAGER)
  private Birth birth;
  // @Column(name = "birth_id")
  @Transient
  private Integer personBirthId;
  @JoinColumn(name = "death_id", referencedColumnName = "id")
  @ManyToOne(targetEntity = Death.class, fetch = FetchType.EAGER)
  private Death death;

  // @Column(name = "death_id")
  @Transient
  private Integer personDeathId;
  @Column(name = "notes")
  private String personNotes;

  // constructors
  public Person() {

  }

  public Person(Integer id, String firstName, String lastName, String middleName, String generation, String marriedName,
      String nickName, String gender, Integer fatherId, Integer motherId, Integer currentLocationId, Integer birthId,
      Integer deathId, String personNotes) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.middleName = middleName;
    this.generation = generation;
    this.marriedName = marriedName;
    this.nickName = nickName;
    this.gender = gender;
    this.personFatherId = fatherId;
    this.personMotherId = motherId;
    this.personCurrentLocationId = currentLocationId;
    this.personBirthId = birthId;
    this.personDeathId = deathId;
    this.personNotes = personNotes;

  }

  // Getters and Setters
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getGeneration() {
    return generation;
  }

  public void setGeneration(String generation) {
    this.generation = generation;
  }

  public String getMarriedName() {
    return marriedName;
  }

  public void setMarriedName(String marriedName) {
    this.marriedName = marriedName;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Integer getPersonFatherId() {
    if (this.father != null) {
      return father.getId();
    } else {
      return 0;
    }
  }

  public void setPersonFatherId(Integer father) {
    this.personFatherId = father;
  }

  public Integer getPersonMotherId() {
    if (this.mother != null) {
      return mother.getId();
    } else {
      return 0;
    }
  }

  public void setPersonMotherId(Integer motherId) {
    this.personMotherId = motherId;
  }

  public Integer getPersonCurrentLocationId() {

    if (this.currentLocation != null) {
      return currentLocation.getId();
    } else {
      return 0;
    }
  }

  public void setPersonCurrentLocationId(Integer currentLocationId) {
    this.personCurrentLocationId = currentLocationId;
  }

  public Integer getPersonBirthId() {
    if (this.birth != null) {
      return birth.getId();
    } else {
      return 0;
    }
  }

  public void setPersonBirthId(Integer birthId) {
    this.personBirthId = birthId;
  }

  public Integer getPersonDeathId() {
    if (this.death != null) {
      return death.getId();
    } else {
      return 0;
    }

  }

  public void setPersonDeathId(Integer deathId) {
    this.personDeathId = deathId;
  }

  public String getPersonNotes() {
    return personNotes;
  }

  public void setPersonNotes(String personNotes) {
    this.personNotes = personNotes;
  }

  public Person getFather() {
    return father;
  }

  public void setFather(Person father) {
    this.father = father;
  }

  public Person getMother() {
    return mother;
  }

  public void setMother(Person mother) {
    this.mother = mother;
  }

  public Location getCurrentLocation() {
    return currentLocation;
  }

  public void setCurrentLocation(Location currentLocation) {
    this.currentLocation = currentLocation;
  }

  public Birth getBirth() {
    return birth;
  }

  public void setBirth(Birth birth) {
    this.birth = birth;
  }

  public Death getDeath() {
    return death;
  }

  public void setDeath(Death death) {
    this.death = death;
  }

  @Override
  public String toString() {
    return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", middleName=" + middleName
        + ", generation=" + generation + ", marriedName=" + marriedName + ", nickName=" + nickName + ", gender="
        + gender + ", father=" + father + ", mother=" + mother + ", location=" + currentLocation
        + ", birthId="
        + birth
        + ", deathId=" + death + ", personNotes=" + personNotes + "]";
  }

}
