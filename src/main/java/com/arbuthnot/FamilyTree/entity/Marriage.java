package com.arbuthnot.FamilyTree.entity;

import java.util.ArrayList;

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
@Table(name = "Marriages")
public class Marriage {

  // Fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;
  @Column(name = "marriage_year")
  private Integer marriageYear;
  @Column(name = "marriage_month")
  private Integer marriageMonth;
  @Column(name = "marriage_day")
  private Integer marriageDay;
  @JoinColumn(name = "marriage_location")
  @ManyToOne(targetEntity = Location.class, fetch = FetchType.EAGER)
  private Location marriageLocation;
  @Transient
  private Integer marriageLocationId;
  @Column(name = "marriage_notes")
  private String marriageNotes;
  @Column(name = "marriage_status")
  private String marriageStatus;
  @Transient
  PersonMarriage personMarriage;

  // Constructors
  public Marriage() {
    // this.marriageYear = 0;
    // this.marriageMonth = 0;
    // this.marriageDay = 0;
    this.marriageLocation = new Location();
  }

  // Getters and Setters
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getMarriageYear() {
    return marriageYear;
  }

  public void setMarriageYear(Integer marriageYear) {
    this.marriageYear = marriageYear;
  }

  public Integer getMarriageMonth() {
    return marriageMonth;
  }

  public void setMarriageMonth(Integer marriageMonth) {
    this.marriageMonth = marriageMonth;
  }

  public Integer getMarriageDay() {
    return marriageDay;
  }

  public void setMarriageDay(Integer marriageDay) {
    this.marriageDay = marriageDay;
  }

  public Integer getMarriageLocationId() {
    return marriageLocationId;
  }

  public void setMarriageLocationId(Integer marriageLocation) {
    this.marriageLocationId = marriageLocation;
  }

  public String getMarriageNotes() {
    return marriageNotes;
  }

  public void setMarriageNotes(String marriageNotes) {
    this.marriageNotes = marriageNotes;
  }

  public String getMarriageStatus() {
    return marriageStatus;
  }

  public void setMarriageStatus(String marrageStatus) {
    this.marriageStatus = marrageStatus;
  }

  public Location getMarriageLocation() {
    return marriageLocation;
  }

  public void setMarriageLocation(Location marriageLocation) {
    this.marriageLocation = marriageLocation;
  }

  public PersonMarriage getPersonMarriage() {
    return personMarriage;
  }

  public void setPersonMarriage(PersonMarriage personMarriage) {
    this.personMarriage = personMarriage;
  }

  @Override
  public String toString() {
    return "Marriage [id=" + id + ", marriageYear=" + marriageYear + ", marriageMonth=" + marriageMonth
        + ", marriageDay=" + marriageDay + ", marriageLocation=" + marriageLocation + ", marriageLocationId="
        + marriageLocationId + ", marriageStatus=" + marriageStatus + ", personMarriage=" + personMarriage + "]";
  }

  public void addSpouseIdToPersonMarriage(int id) {
    this.personMarriage.setSpouse1Id(id);
  }

}
