package com.arbuthnot.FamilyTree.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arbuthnot.FamilyTree.dao.MarriageDAOImpl;
import com.arbuthnot.FamilyTree.dao.PersonMarriageDAOImpl;
import com.arbuthnot.FamilyTree.entity.Marriage;
import com.arbuthnot.FamilyTree.entity.PersonMarriage;
import com.arbuthnot.FamilyTree.utils.*;

import jakarta.transaction.Transactional;

@Service
public class MarriageServiceImpl implements EntityService<Marriage> {

  private MarriageDAOImpl marriageDao;
  private PersonMarriageDAOImpl pmDao;

  private DateTimeUtils dateTime = new DateTimeUtils();

  @Autowired
  public MarriageServiceImpl(MarriageDAOImpl marriageDao, PersonMarriageDAOImpl pmDao) {
    this.marriageDao = marriageDao;
    this.pmDao = pmDao;
  }

  @Override
  public boolean validateEntity(Marriage marriage) {

    if (marriage instanceof Marriage) {
      if ((marriage.getMarriageYear() != null && marriage.getMarriageYear() != 0) &&
          (marriage.getMarriageMonth() != null && marriage.getMarriageMonth() != 0) &&
          (marriage.getMarriageDay() != null && marriage.getMarriageDay() != 0)) {
        if (!dateTime.validateDate(marriage.getMarriageYear(), marriage.getMarriageMonth(),
            marriage.getMarriageDay())) {
          return false;
        }
      }
      if (marriage.getPersonMarriage() instanceof PersonMarriage) {
        PersonMarriage pm = marriage.getPersonMarriage();
        if (/* (pm.getSpouse1Id() != null && pm.getSpouse1Id() != 0 ) && */
        (pm.getSpouse2Id() != null && pm.getSpouse2Id() != 0)) {
          System.out.println("\n....  PersonMarriage spouses verrified  ....\n");
          return true;
        }
      }
    } else {
      return false;
    }
    return false;
  }

  public boolean validateEntity(List<Marriage> marriages) {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  @Transactional
  public Marriage saveEntity(Marriage marriage) {
    Marriage savedMarriage;
    if (validateEntity(marriage)) {
      PersonMarriage personMarriage = marriage.getPersonMarriage();
      if (marriage.getMarriageLocation().getId() == null || marriage.getMarriageLocation().getId() == 0) {
        marriage.setMarriageLocation(null);
      }
      savedMarriage = marriageDao.saveEntity(marriage);
      personMarriage.setMarriageId(savedMarriage.getId());
      personMarriage = pmDao.saveEntity(personMarriage);
      savedMarriage.setPersonMarriage(personMarriage);
      return savedMarriage;

    } else {
      return null;
    }
  }

  @Override
  public Marriage updateEntity(Marriage entity) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateEntity'");
  }

  @Override
  public void deleteEntity(Marriage entity) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteEntity'");
  }

  @Override
  public void addEntity(Marriage entity) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'addEntity'");
  }

  @Override
  public Marriage getEntityById(int id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getEntityById'");
  }

  @Override
  public List<Marriage> getAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAll'");
  }

  @Override
  public List<Marriage> getAllOrderBy(String column) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAllOrderBy'");
  }

  @Override
  public List<Marriage> getAllByColumnValue(String column, String value) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAllByColumnValue'");
  }
}
