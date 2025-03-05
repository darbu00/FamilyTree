
package com.arbuthnot.FamilyTree.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arbuthnot.FamilyTree.entity.Death;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class DeathDAOImpl implements EntityDAO<Death> {

  private EntityManager entityManager;

  @Autowired
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
  public Death saveEntity(Death death) {
    if (death instanceof Death) {
      entityManager.persist(death);
    }
    return death;
  }

  @Override
  public Death updateEntity(Death object) {
    // TODO Auto-generated method stub
    return new Death();
  }

  @Override
  public List<Death> getEntityByColumn(String column, String columnValue) {
    // TODO Auto-generated method stub
    return null;
  }

}
