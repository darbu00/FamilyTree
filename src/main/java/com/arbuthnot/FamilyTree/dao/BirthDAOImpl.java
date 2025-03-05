package com.arbuthnot.FamilyTree.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arbuthnot.FamilyTree.entity.Birth;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class BirthDAOImpl implements EntityDAO<Birth> {

  private EntityManager entityManager;

  @Autowired
  public BirthDAOImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public List<Birth> getEntityByColumn(String column, String columnValue) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Birth findEntityById(Integer id) {
    return entityManager.find(Birth.class, id);
  }

  @Override
  public List<Birth> getEntityList() {
    TypedQuery<Birth> getBirths = entityManager.createQuery("FROM Birth", Birth.class);
    return getBirths.getResultList();
  }

  @Override
  // @Transactional
  public Birth saveEntity(Birth birth) {
    if (birth instanceof Birth) {
      entityManager.persist(birth);
    }
    return birth;
  }

  @Override
  public Birth updateEntity(Birth object) {
    // TODO Auto-generated method stub
    return new Birth();
  }

}
