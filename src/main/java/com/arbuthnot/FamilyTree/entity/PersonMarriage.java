package com.arbuthnot.FamilyTree.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Person_Marriage")
public class PersonMarriage {
  @Id
  @Column(name = "marriage_id")
  private Integer marriageId;
  //
  //
  @Column(name = "spouse1_id")
  private Integer spouse1Id;

  @Column(name = "spouse2_id")
  private Integer spouse2Id;

  public Integer getMarriageId() {
    return marriageId;
  }

  public void setMarriageId(Integer marriageId) {
    this.marriageId = marriageId;
  }

  public Integer getSpouse1Id() {
    return spouse1Id;
  }

  public void setSpouse1Id(Integer spouse1Id) {
    this.spouse1Id = spouse1Id;
  }

  public Integer getSpouse2Id() {
    return spouse2Id;
  }

  public void setSpouse2Id(Integer spouse2Id) {
    this.spouse2Id = spouse2Id;
  }

}
