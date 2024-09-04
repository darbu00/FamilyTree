package com.arbuthnot.FamilyTree.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Country")
public class Country {

  // Fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;
  @Column(name = "country_name")
  private String countryName;
  @Column(name = "country_short")
  private String countryShortName;
  @Column(name = "country_region")
  private String countryRegion;
  @Column(name = "country_subregion")
  private String countrySubRegion;
  @Column(name = "country_intermediate_region")
  private String countryIntegerermediateRegion;

  // Constructor
  public Country() {

  }

  // Getters and Setters
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  public String getCountryShortName() {
    return countryShortName;
  }

  public void setCountryShortName(String countryShortName) {
    this.countryShortName = countryShortName;
  }

  public String getCountryRegion() {
    return countryRegion;
  }

  public void setCountryRegion(String countryRegion) {
    this.countryRegion = countryRegion;
  }

  public String getCountrySubRegion() {
    return countrySubRegion;
  }

  public void setCountrySubRegion(String countrySubRegion) {
    this.countrySubRegion = countrySubRegion;
  }

  public String getCountryIntegerermediateRegion() {
    return countryIntegerermediateRegion;
  }

  public void setCountryIntegerermediateRegion(String countryIntegerermediateRegion) {
    this.countryIntegerermediateRegion = countryIntegerermediateRegion;
  }

}
