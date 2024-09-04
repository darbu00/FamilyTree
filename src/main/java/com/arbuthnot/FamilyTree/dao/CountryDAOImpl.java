
package com.arbuthnot.FamilyTree.dao;

import java.util.List;

import com.arbuthnot.FamilyTree.entity.Country;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

public class CountryDAOImpl implements EntityDAO {

  private EntityManager entityManager;

  public CountryDAOImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Country findEntityById(Integer id) {
    return entityManager.find(Country.class, id);
  }

  @Override
  public List<Country> getEntityList() {
    TypedQuery<Country> getCountrys = entityManager.createQuery("FROM Country", Country.class);
    return getCountrys.getResultList();
  }

  @Override
  @Transactional
  public void saveEntity(Object object) {
    if (object instanceof Country) {
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
