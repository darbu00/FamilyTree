
package com.arbuthnot.FamilyTree.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arbuthnot.FamilyTree.entity.Country;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class CountryDAOImpl implements EntityDAO<Country> {

  private EntityManager entityManager;

  @Autowired
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
  public Country saveEntity(Country object) {
    if (object instanceof Country) {
      entityManager.persist(object);
    }
    return new Country();
  }

  @Override
  public Country updateEntity(Country object) {
    // TODO Auto-generated method stub
    return new Country();
  }

  @Override
  public List<Country> getEntityByColumn(String column, String columnValue) {
    TypedQuery<Country> getCountries = entityManager.createQuery("FROM Country where " + column + "=:columnValue",
        Country.class);
    getCountries.setParameter("columnValue", columnValue);
    return getCountries.getResultList();
  }

}
