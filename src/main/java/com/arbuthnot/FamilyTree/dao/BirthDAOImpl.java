package com.arbuthnot.FamilyTree.dao;

import java.util.List;

import com.arbuthnot.FamilyTree.entity.Birth;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

public class BirthDAOImpl implements EntityDAO {

  @Override
  public List<?> getEntityByColumn(String column, String columnValue) {
    // TODO Auto-generated method stub
    return null;
  }

  private EntityManager entityManager;

  public BirthDAOImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
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
  @Transactional
  public void saveEntity(Object object) {
    if (object instanceof Birth) {
      entityManager.persist(object);
    }
  }

  @Override
  public void updateEntity(Object object) {
    // TODO Auto-generated method stub

  }

}
