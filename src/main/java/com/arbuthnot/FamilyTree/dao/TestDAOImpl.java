package com.arbuthnot.FamilyTree.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arbuthnot.FamilyTree.entity.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Repository
public class TestDAOImpl implements EntityDAO {

  private EntityManager entityManager;

  @Autowired
  public TestDAOImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public void saveEntity(Object object) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'saveEntity'");
  }

  @Override
  public Object findEntityById(Integer id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findEntityById'");
  }

  @Override
  public List<?> getEntityList() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getEntityList'");
  }

  @Override
  public void updateEntity(Object object) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateEntity'");
  }

  @Override
  public List<?> getEntityByColumn(String column, String columnValue) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getEntityByColumn'");
  }

  public List<Person> findChildrenByParentIdTest(Integer parent) {
    List<Person> children = new ArrayList<Person>();
    Query getPeople = entityManager.createNativeQuery(
        // "select * from Persons p join Births b on p.birth_id=b.id " +
        // "join Locations l on b.birth_location=l.id " +
        // "where p.father=:id order by b.birth_year asc",
        // "SELECT p.id, p.name_first, p.name_middle, p.name_last, p.birth_id, " +
        "SELECT p.*, p.birth_id, " +

            "b.id as birthId, b.birth_year, b.birth_month, b.birth_day" +
            // ", l.id as lid, l.location_city, l.location_state, l.location_country" +
            " FROM Persons p JOIN Births b ON " +
            "p.birth_id = birthId " +
            // "join Locations l on b.birth_location = lid " +
            "where p.father_id=:id order by b.birth_year ASC",
        Person.class);
    getPeople.setParameter("id", parent);

    for (Object p : getPeople.getResultList()) {
      children.add((Person) p);
    }
    return children;
  }

}
