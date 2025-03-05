
package com.arbuthnot.FamilyTree.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arbuthnot.FamilyTree.entity.Country;
import com.arbuthnot.FamilyTree.entity.Location;

import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
//import jakarta.transaction.Transactional;

@Repository
public class LocationDAOImpl implements EntityDAO<Location> {
  // @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private CountryDAOImpl countryDao;

  @Autowired
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
  // @Transactional
  public Location saveEntity(Location location) {
    if (location instanceof Location) {
      Location savedLocation = entityManager.merge(location);
      return savedLocation;
    } else {
      return null;
    }
  }

  @Override
  // @Transactional
  public Location updateEntity(Location location) {
    return entityManager.merge(location);
  }

  @Override
  public List<Location> getEntityByColumn(String column, String columnValue) {
    // TODO Auto-generated method stub
    return null;
  }

}
