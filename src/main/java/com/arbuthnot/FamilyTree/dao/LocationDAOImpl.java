
package com.arbuthnot.FamilyTree.dao;

import java.util.List;

import com.arbuthnot.FamilyTree.entity.Location;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

public class LocationDAOImpl implements EntityDAO {

  private EntityManager entityManager;

  public LocationDAOImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Location findEntityById(Integer id) {
    return entityManager.find(Location.class, id);
    // TODO add code to get the country name and set locationCountryName
  }

  @Override
  public List<Location> getEntityList() {
    TypedQuery<Location> getLocations = entityManager.createQuery("FROM Location", Location.class);
    return getLocations.getResultList();
  }

  @Override
  @Transactional
  public void saveEntity(Object object) {
    if (object instanceof Location) {
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
