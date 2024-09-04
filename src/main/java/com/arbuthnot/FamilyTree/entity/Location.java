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
@Table(name = "Locations")
public class Location {

  // Fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;
  @Column(name = "location_name")
  private String locationName;
  @Column(name = "location_address1")
  private String locationAddress1;
  @Column(name = "location_address2")
  private String locationAddress2;
  @Column(name = "location_city")
  private String locationCity;
  @Column(name = "location_county")
  private String locationCounty;
  @Column(name = "location_state")
  private String locationState;
  @Column(name = "location_state_short")
  private String locationStateShort;
  @Column(name = "location_zip")
  private String locationZip;
  @JoinColumn(name = "location_country", referencedColumnName = "id")
  @ManyToOne(fetch = FetchType.EAGER)
  private Country locationCountry;
  @Transient
  private Integer locationCountryId;
  @Transient
  private String locationCountryName;

  public Location() {
    this.setId(0);
    this.setLocationName("");
    this.setLocationAddress1("");
    this.setLocationAddress2("");
    this.setLocationCity("");
    this.setLocationCounty("");
    this.setLocationState("");
    this.setLocationStateShort("");
    this.setLocationZip("");
    this.setLocationCountryId(0);

  }

  // Getters and Setters
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getLocationName() {
    return locationName;
  }

  public void setLocationName(String locationName) {
    this.locationName = locationName;
  }

  public String getLocationAddress1() {
    return locationAddress1;
  }

  public void setLocationAddress1(String locationAddress1) {
    this.locationAddress1 = locationAddress1;
  }

  public String getLocationAddress2() {
    return locationAddress2;
  }

  public void setLocationAddress2(String locationAddress2) {
    this.locationAddress2 = locationAddress2;
  }

  public String getLocationCity() {
    return locationCity;
  }

  public void setLocationCity(String locationCity) {
    this.locationCity = locationCity;
  }

  public String getLocationCounty() {
    return locationCounty;
  }

  public void setLocationCounty(String locationCounty) {
    this.locationCounty = locationCounty;
  }

  public String getLocationState() {
    return locationState;
  }

  public void setLocationState(String locationState) {
    this.locationState = locationState;
  }

  public String getLocationStateShort() {
    return locationStateShort;
  }

  public void setLocationStateShort(String locationStateShort) {
    this.locationStateShort = locationStateShort;
  }

  public String getLocationZip() {
    return locationZip;
  }

  public void setLocationZip(String locationZip) {
    this.locationZip = locationZip;
  }

  public Integer getLocationCountryId() {
    return locationCountryId;
  }

  public void setLocationCountryId(Integer locationCountry) {
    this.locationCountryId = locationCountry;
  }

  public String getLocationCountryName() {
    return locationCountry.getCountryName();
  }

  public void setLocationCountryName(String locationCountryName) {
    this.locationCountryName = locationCountryName;
  }

  public Country getLocationCountry() {
    return locationCountry;
  }

  public void setLocationCountry(Country locationCountry) {
    this.locationCountry = locationCountry;
  }

}
