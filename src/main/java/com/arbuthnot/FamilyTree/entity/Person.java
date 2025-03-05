package com.arbuthnot.FamilyTree.entity;

import java.util.ArrayList;

import com.arbuthnot.FamilyTree.dao.PersonDAOImpl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
  @Transient
  private Person father;
  @Column(name = "father_id") // 0=tree start, -1=not in DB or not known
  private Integer fatherId;
  @Transient
  private Person mother;
  @Column(name = "mother_id") // 0=tree start, -1=not in DB or not known
  private Integer motherId;
  @JoinColumn(name = "current_location_id", referencedColumnName = "id")
  @ManyToOne(targetEntity = Location.class, fetch = FetchType.EAGER)
  private Location currentLocation; // 0=not in DB/unknown
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
  @Transient
  private ArrayList<Marriage> marriages;

  // constructors
  public Person() {
    this.firstName = "";
    this.lastName = "";
    this.middleName = "";
    this.generation = "";
    this.marriedName = "";
    this.nickName = "";
    this.fatherId = Integer.valueOf(-1);
    this.motherId = Integer.valueOf(-1);
    this.personCurrentLocationId = Integer.valueOf(0);
    this.personBirthId = Integer.valueOf(0);
    this.personDeathId = Integer.valueOf(0);
    this.personNotes = "";
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
    this.fatherId = fatherId;
    this.motherId = motherId;
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

  public Integer getFatherId() {
    return fatherId;
  }

  public void setFatherId(Integer father) {
    this.fatherId = father;
  }

  public Integer getMotherId() {
    return motherId;
  }

  public void setMotherId(Integer motherId) {
    this.motherId = motherId;
  }

  public Integer getPersonCurrentLocationId() {

    if (this.currentLocation != null) {
      return currentLocation.getId();
    } else if (personCurrentLocationId != null && personCurrentLocationId != 0) {
      return personCurrentLocationId;
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

  public ArrayList<Marriage> getMarriages() {
    return marriages;
  }

  public void setMarriages(ArrayList<Marriage> marriages) {
    this.marriages = marriages;
  }

  public void saveCurrentLocationId(int currentLocationId) {
    if (currentLocationId != 0) {
      this.currentLocation.setId(currentLocationId);
    }
  }

  @Override
  public String toString() {
    return "Person [id=" + id + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
        + ", generation=" + generation + ", marriedName=" + marriedName + ", nickName=" + nickName + ", gender="
        + gender + ", fatherId=" + fatherId + ", motherId=" + motherId + ", personCurrentLocationId="
        + personCurrentLocationId + ", currentLocation=" + currentLocation
        + ", birth=" + birth + ", death=" + death + ", personNotes=" + personNotes + ", marriages=" + marriages + "]";
  }

}
