package com.arbuthnot.FamilyTree.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "Births")
public class Birth {
  // Fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;
  @Column(name = "birth_year")
  private Integer birthYear;
  @Column(name = "birth_month")
  private Integer birthMonth;
  @Column(name = "birth_day")
  private Integer birthDay;
  // @Column(name = "birth_location")
  @JoinColumn(name = "birth_location", referencedColumnName = "id")
  @ManyToOne(targetEntity = Location.class, fetch = FetchType.EAGER)
  private Location birthLocation;
  @Transient
  private Integer birthLocationId;
  @Column(name = "birth_notes")
  private String birthNotes;
  @Column(name = "birth_accurate")
  private Integer birthAccurate;

  public Birth() {
    this.setId(0);
    this.setBirthYear(0);
    this.setBirthMonth(0);
    this.setBirthDay(0);
    this.setBirthLocationId(0);
    this.setBirthNotes("");
    this.setBirthAccurate(0);
  }

  // Getters and Setters
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getBirthYear() {
    return birthYear;
  }

  public void setBirthYear(Integer birthYear) {
    this.birthYear = birthYear;
  }

  public Integer getBirthMonth() {
    return birthMonth;
  }

  public void setBirthMonth(Integer birthMonth) {
    this.birthMonth = birthMonth;
  }

  public Integer getBirthDay() {
    return birthDay;
  }

  public void setBirthDay(Integer birthday) {
    this.birthDay = birthday;
  }

  public Integer getBirthLocationId() {
    if (birthLocation != null) {
      return birthLocation.getId();
    } else {
      return 0;
    }
  }

  public void setBirthLocationId(Integer birthLocationId) {
    this.birthLocationId = birthLocationId;
  }

  public String getBirthNotes() {
    return birthNotes;
  }

  public void setBirthNotes(String birthNotes) {
    this.birthNotes = birthNotes;
  }

  public Integer isBirthAccurate() {
    return birthAccurate;
  }

  public void setBirthAccurate(Integer birthAccurate) {
    this.birthAccurate = birthAccurate;
  }

  public Integer getBirthAccurate() {
    return birthAccurate;
  }

  public Location getBirthLocation() {
    return birthLocation;
  }

  public void setBirthLocation(Location birthLocation) {
    this.birthLocation = birthLocation;
  }

}
