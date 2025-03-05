package com.arbuthnot.FamilyTree.entity;

import org.hibernate.annotations.Cascade;

import jakarta.persistence.CascadeType;
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
    this.setLocationCountryName("");
    this.setLocationCountry(new Country());
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

  public void setLocationCountryId(Integer locationCountryId) {
    this.locationCountryId = locationCountryId;
  }

  public String getLocationCountryName() {
    if (locationCountry != null) {
      return locationCountry.getCountryName();
    } else {
      return "";
    }
  }

  public void setLocationCountryName(String locationCountryName) {
    this.locationCountryName = locationCountryName;
  }

  public Country getLocationCountry() {
    if (this.locationCountry != null) {
      setLocationCountryId(this.locationCountry.getId());
      setLocationCountryName(this.locationCountry.getCountryName());
    }
    return locationCountry;
  }

  public void setLocationCountry(Country locationCountry) {
    this.locationCountry = locationCountry;
  }

  @Override
  public String toString() {
    return "Location [id=" + id + ", locationName=" + locationName + ", locationAddress1=" + locationAddress1
        + ", locationAddress2=" + locationAddress2 + ", locationCity=" + locationCity + ", locationCounty="
        + locationCounty + ", locationState=" + locationState + ", locationStateShort=" + locationStateShort
        + ", locationZip=" + locationZip + ", locationCountry=" + locationCountry + ", locationCountryId="
        + locationCountryId + ", locationCountryName="
        + locationCountryName + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((locationName == null) ? 0 : locationName.hashCode());
    result = prime * result + ((locationAddress1 == null) ? 0 : locationAddress1.hashCode());
    result = prime * result + ((locationAddress2 == null) ? 0 : locationAddress2.hashCode());
    result = prime * result + ((locationCity == null) ? 0 : locationCity.hashCode());
    result = prime * result + ((locationCounty == null) ? 0 : locationCounty.hashCode());
    result = prime * result + ((locationState == null) ? 0 : locationState.hashCode());
    result = prime * result + ((locationZip == null) ? 0 : locationZip.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Location other = (Location) obj;
    if (locationName == null) {
      if (other.locationName != null)
        return false;
    } else if (!locationName.equals(other.locationName))
      return false;
    if (locationAddress1 == null) {
      if (other.locationAddress1 != null)
        return false;
    } else if (!locationAddress1.equals(other.locationAddress1))
      return false;
    if (locationAddress2 == null) {
      if (other.locationAddress2 != null)
        return false;
    } else if (!locationAddress2.equals(other.locationAddress2))
      return false;
    if (locationCity == null) {
      if (other.locationCity != null)
        return false;
    } else if (!locationCity.equals(other.locationCity))
      return false;
    if (locationCounty == null) {
      if (other.locationCounty != null)
        return false;
    } else if (!locationCounty.equals(other.locationCounty))
      return false;
    if (locationState == null) {
      if (other.locationState != null)
        return false;
    } else if (!locationState.equals(other.locationState))
      return false;
    if (locationZip == null) {
      if (other.locationZip != null)
        return false;
    } else if (!locationZip.equals(other.locationZip))
      return false;
    return true;
  }

}
