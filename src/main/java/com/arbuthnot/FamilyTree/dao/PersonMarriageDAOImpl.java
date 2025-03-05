package com.arbuthnot.FamilyTree.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arbuthnot.FamilyTree.entity.PersonMarriage;

import jakarta.persistence.EntityManager;

@Repository
// @Primary
public class PersonMarriageDAOImpl implements EntityDAO<PersonMarriage> {

  private EntityManager entityManager;

  @Autowired
  public PersonMarriageDAOImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public PersonMarriage saveEntity(PersonMarriage personMarriage) {
    return entityManager.merge(personMarriage);
  }

  @Override
  public PersonMarriage findEntityById(Integer id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findEntityById'");
  }

  @Override
  public List<PersonMarriage> getEntityList() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getEntityList'");
  }

  @Override
  public PersonMarriage updateEntity(PersonMarriage object) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateEntity'");
  }

  @Override
  public List<PersonMarriage> getEntityByColumn(String column, String columnValue) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getEntityByColumn'");
  }

}
