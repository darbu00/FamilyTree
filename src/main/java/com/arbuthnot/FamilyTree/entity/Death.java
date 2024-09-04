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
@Table(name = "Deaths")
public class Death {

  // Fields
  //
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;
  @Column(name = "death_year")
  private Integer deathYear;
  @Column(name = "death_month")
  private Integer deathMonth;
  @Column(name = "death_day")
  private Integer deathDay;
  // @Column(name = "death_location")
  @JoinColumn(name = "death_location", referencedColumnName = "id")
  @ManyToOne(targetEntity = Location.class, fetch = FetchType.EAGER)
  private Location deathLocation;
  @Transient
  private Integer deathLocationId;
  @Column(name = "death_notes")
  private String deathNotes;
  @Column(name = "death_accurate")
  private Integer deathAccurate;

  public Death() {
    this.setId(0);
    this.setDeathYear(0);
    this.setDeathMonth(0);
    this.setDeathDay(0);
    this.setDeathLocationId(0);
    this.setDeathNotes("");
    this.setDeathAccurate(0);
  }

  // Getters and Setters
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getDeathYear() {
    return deathYear;
  }

  public void setDeathYear(Integer deathYear) {
    this.deathYear = deathYear;
  }

  public Integer getDeathMonth() {
    return deathMonth;
  }

  public void setDeathMonth(Integer deathMonth) {
    this.deathMonth = deathMonth;
  }

  public Integer getDeathDay() {
    return deathDay;
  }

  public void setDeathDay(Integer deathday) {
    this.deathDay = deathday;
  }

  public Integer getDeathLocationId() {
    if (deathLocation != null) {
      return deathLocation.getId();
    } else {
      return 0;
    }
  }

  public void setDeathLocationId(Integer deathLocationId) {
    this.deathLocationId = deathLocationId;
  }

  public String getDeathNotes() {
    return deathNotes;
  }

  public void setDeathNotes(String deathNotes) {
    this.deathNotes = deathNotes;
  }

  public Integer isDeathAccurate() {
    return deathAccurate;
  }

  public void setDeathAccurate(Integer deathAccurate) {
    this.deathAccurate = deathAccurate;
  }

  public Integer getDeathAccurate() {
    return deathAccurate;
  }

  public Location getDeathLocation() {
    return deathLocation;
  }

  public void setDeathLocation(Location deathLocation) {
    this.deathLocation = deathLocation;
  }

}
