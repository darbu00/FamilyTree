package com.arbuthnot.FamilyTree.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.arbuthnot.FamilyTree.entity.Location;
import com.arbuthnot.FamilyTree.entity.Person;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
// @Primary
public class PersonDAOImpl implements EntityDAO<Person> {
  private EntityManager entityManager;

  @Autowired
  public PersonDAOImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public List<Person> getEntityList() {
    TypedQuery<Person> getPeople = entityManager.createQuery("FROM Person", Person.class);
    return getPeople.getResultList();
  }

  @Override
  @Transactional
  public Person saveEntity(Person person) {
    if (person instanceof Person) {
      return entityManager.merge(person);
    } else {
      return null;
    }
  }

  @Override
  public Person findEntityById(Integer id) {
    if (id != null) {
      return entityManager.find(Person.class, id);
    } else {
      return null;
    }
  }

  @Override
  public Person updateEntity(Person object) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateEntity'");
  }

  @Override
  public List<Person> getEntityByColumn(String column, String value) {
    TypedQuery<Person> getPeople = entityManager.createQuery("FROM Person where " + column + "=:columnValue",
        Person.class);

    // getPeople.setParameter("columnName", column);
    getPeople.setParameter("columnValue", value);

    return getPeople.getResultList();
  }

  // TODO add sort by year/moth born
  public List<Person> getSiblings(int child, int father, int mother) {
    List<Person> siblings = new ArrayList<Person>();
    if (father > 0) {
      Query getPeople = entityManager.createNativeQuery("Select * from Persons where father_id=:id", Person.class);
      // TypedQuery<Person> getPeople = entityManager.createQuery("FROM Person where
      // father=:id", Person.class);
      getPeople.setParameter("id", father);
      for (Object p : getPeople.getResultList()) {
        if (((Person) p).getId() != child) {
          siblings.add((Person) p);
        }
      }
    }

    if (mother > 0) {
      Query getPeople = entityManager.createNativeQuery("Select * from Persons where mother_id=:id", Person.class);
      // TypedQuery<Person> getPeople = entityManager.createQuery("FROM Person where
      // mother=:id", Person.class);
      getPeople.setParameter("id", mother);
      for (Object p : getPeople.getResultList()) {
        boolean addSibling = true;
        if (((Person) p).getId() == child) {
          addSibling = false;
        } else {
          for (Person s : siblings) {
            if (((Person) p).getId() == s.getId()) {
              addSibling = false;
            }
          }
        }
        if (addSibling) {
          siblings.add((Person) p);
        }
      }
    }

    return siblings;
  }

  // TODO add sort by year/month born
  public List<Person> findChildrenByParentId(Integer parent) {
    List<Person> children = new ArrayList<Person>();
    Query getPeople = entityManager.createNativeQuery("Select * FROM Persons where father_id=:id or mother_id=:id",
        Person.class);
    getPeople.setParameter("id", parent);

    for (Object p : getPeople.getResultList()) {
      children.add((Person) p);
    }
    return children;
  }

  @Transactional
  public boolean addPerson(Person person) {
    boolean saved = false;

    if (person instanceof Person) {
      entityManager.refresh(person);
      entityManager.persist(person);
    }
    return saved;
  }

}
