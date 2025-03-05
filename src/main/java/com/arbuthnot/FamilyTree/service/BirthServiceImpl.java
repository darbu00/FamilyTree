package com.arbuthnot.FamilyTree.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arbuthnot.FamilyTree.dao.BirthDAOImpl;
import com.arbuthnot.FamilyTree.entity.Birth;
import com.arbuthnot.FamilyTree.utils.*;

@Service
public class BirthServiceImpl implements EntityService<Birth> {

  private BirthDAOImpl birthDao;
  private LocationServiceImpl locationService;
  private DateTimeUtils dateUtils = new DateTimeUtils();

  @Autowired
  public BirthServiceImpl(BirthDAOImpl birthDao, LocationServiceImpl locationService) {
    this.birthDao = birthDao;
    this.locationService = locationService;
  }

  @Override
  public boolean validateEntity(Birth birth) {

    if (birth.getBirthYear() != null &&
        birth.getBirthMonth() != null &&
        birth.getBirthDay() != null) {
      if (!dateUtils.validateDate(birth.getBirthYear(), birth.getBirthMonth(), birth.getBirthDay())) {
        System.out.println("\n....  Invald Birth date entered  ....\n");
        return false;
      } else {
        return true;
      }
    } else if (birth.getBirthYear() != null && birth.getBirthYear() > 0
        && birth.getBirthYear() <= java.time.LocalDate.now().getYear()) {
      if (birth.getBirthMonth() == null || (birth.getBirthMonth() >= 1 && birth.getBirthMonth() <= 12)) {
        if (birth.getBirthYear() == java.time.LocalDate.now().getYear()) {
          if (birth.getBirthMonth() <= java.time.LocalDate.now().getMonth().ordinal()) {
            return true;
          } else {
            return false;
          }
        }
        return true;
      } else {
        return false;
      }
    }
    if (birth.getBirthLocation().getLocationCountry() != null) {
      if (birth.getBirthLocation().getLocationCountry().getCountryName() != null
          && birth.getBirthLocation().getLocationCountry().getCountryName().length() >= 4) {
        return true;
      }
    }
    return false;
  }

  @Override
  @Transactional
  public Birth saveEntity(Birth birth) {
    if (validateEntity(birth)) {
      if (birth.getBirthLocation().getId() != null && locationService.validateEntity(birth.getBirthLocation())) {
        birth.setBirthLocation(locationService.updateEntity(birth.getBirthLocation()));
      } else {
        birth.setBirthLocation(null);
      }
      if (birth.getBirthYear() != null &&
          birth.getBirthMonth() != null &&
          birth.getBirthDay() != null) {
        if (dateUtils.validateDate(birth.getBirthYear(), birth.getBirthMonth(), birth.getBirthDay())) {
          birth.setBirthAccurate(1);
        } else {
          birth.setBirthAccurate(0);
        }
      } else {
        birth.setBirthAccurate(0);
      }
      return birthDao.saveEntity(birth);
    } else {
      return null;
    }
  }

  @Override
  @Transactional
  public Birth updateEntity(Birth entity) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateEntity'");
  }

  @Override
  public void deleteEntity(Birth entity) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteEntity'");
  }

  @Override
  @Transactional
  public void addEntity(Birth entity) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'addEntity'");
  }

  @Override
  public Birth getEntityById(int id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getEntityById'");
  }

  @Override
  public List<Birth> getAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAll'");
  }

  @Override
  public List<Birth> getAllOrderBy(String column) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAllOrderBy'");
  }

  @Override
  public List<Birth> getAllByColumnValue(String column, String value) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAllByColumnValue'");
  }

}
