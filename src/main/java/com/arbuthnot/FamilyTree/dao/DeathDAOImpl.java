
package com.arbuthnot.FamilyTree.dao;

import java.util.List;

import com.arbuthnot.FamilyTree.entity.Death;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

public class DeathDAOImpl implements EntityDAO {

  private EntityManager entityManager;

  public DeathDAOImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Death findEntityById(Integer id) {
    return entityManager.find(Death.class, id);
  }

  @Override
  public List<Death> getEntityList() {
    TypedQuery<Death> getDeaths = entityManager.createQuery("FROM Death", Death.class);
    return getDeaths.getResultList();
  }

  @Override
  @Transactional
  public void saveEntity(Object object) {
    if (object instanceof Death) {
      entityManager.persist(object);
    }
  }

  @Override
  public void updateEntity(Object object) {
    // TODO Auto-generated method stub

  }

  @Override
  public List<?> getEntityByColumn(String column, String columnValue) {
    // TODO Auto-generated method stub
    return null;
  }

}
