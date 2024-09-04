
package com.arbuthnot.FamilyTree.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arbuthnot.FamilyTree.entity.Marriage;
import com.arbuthnot.FamilyTree.entity.Person;
import com.arbuthnot.FamilyTree.entity.PersonMarriage;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class MarriageDAOImpl implements EntityDAO {

  private EntityManager entityManager;

  @Autowired
  public MarriageDAOImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Marriage findEntityById(Integer id) {
    return entityManager.find(Marriage.class, id);
  }

  @Override
  public List<Marriage> getEntityList() {
    List<Marriage> marriages = new ArrayList<Marriage>();
    List<PersonMarriage> personMarriages = new ArrayList<PersonMarriage>();
    TypedQuery<PersonMarriage> getMarriages = entityManager.createQuery("FROM PersonMarriage", PersonMarriage.class);

    personMarriages = getMarriages.getResultList();
    if (personMarriages.size() >= 1) {
      for (PersonMarriage pm : personMarriages) {
        marriages.add(findEntityById(1000));
      }
    }
    return marriages;
  }

  @Override
  @Transactional
  public void saveEntity(Object object) {
    if (object instanceof Marriage) {
      entityManager.persist(object);
    }
  }

  @Override
  public void updateEntity(Object object) {
    // TODO Auto-generated method stub

  }

  @Override
  public List<Marriage> getEntityByColumn(String column, String columnValue) {
    // TODO Auto-generated method stub
    return null;
  }

  public List<List<?>> getMarriagesBySpouseId(int id) {
    List<List<?>> marriagesSpouses = new ArrayList<List<?>>();
    List<Marriage> marriages = new ArrayList<Marriage>();
    List<Person> spouses = new ArrayList<Person>();
    List<PersonMarriage> personMarriages = new ArrayList<PersonMarriage>();

    TypedQuery<PersonMarriage> getMarriages = entityManager.createQuery("FROM PersonMarriage " +
        "where spouse1Id=:id or spouse2Id=:id", PersonMarriage.class);
    getMarriages.setParameter("id", id);
    personMarriages = getMarriages.getResultList();

    if (personMarriages.size() >= 1) {
      for (PersonMarriage pm : personMarriages) {
        marriages.add(findEntityById(pm.getMarriageId()));
        if (pm.getSpouse1Id() == id) {
          spouses.add(new PersonDAOImpl(entityManager).findEntityById(pm.getSpouse2Id()));
        } else {
          spouses.add(new PersonDAOImpl(entityManager).findEntityById(pm.getSpouse1Id()));
        }
        System.out.println(spouses.get(spouses.size() - 1));
      }
      marriagesSpouses.add(marriages);
      marriagesSpouses.add(spouses);
    }

    return marriagesSpouses;
  }

}
