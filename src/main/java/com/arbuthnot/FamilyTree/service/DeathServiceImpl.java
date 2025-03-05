package com.arbuthnot.FamilyTree.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arbuthnot.FamilyTree.dao.DeathDAOImpl;
import com.arbuthnot.FamilyTree.entity.Death;
import com.arbuthnot.FamilyTree.utils.DateTimeUtils;

@Service
public class DeathServiceImpl implements EntityService<Death> {

  private DeathDAOImpl deathDao;
  private LocationServiceImpl locationService;
  private DateTimeUtils dateUtils = new DateTimeUtils();

  @Autowired
  public DeathServiceImpl(DeathDAOImpl deathDao, LocationServiceImpl locationService) {
    this.deathDao = deathDao;
    this.locationService = locationService;
  }

  @Override
  public boolean validateEntity(Death death) {
    if (death.getDeathYear() != null &&
        death.getDeathMonth() != null &&
        death.getDeathDay() != null) {
      if (!dateUtils.validateDate(death.getDeathYear(), death.getDeathMonth(), death.getDeathDay())) {
        System.out.println("\n....  Invald death date entered  ....\n");
        return false;
      } else {
        return true;
      }
    } else if (death.getDeathYear() != null && death.getDeathYear() > 0
        && death.getDeathYear() <= java.time.LocalDate.now().getYear()) {
      if (death.getDeathMonth() == null || (death.getDeathMonth() >= 1 && death.getDeathMonth() <= 12)) {
        if (death.getDeathYear() == java.time.LocalDate.now().getYear()) {
          if (death.getDeathMonth() <= java.time.LocalDate.now().getMonth().ordinal()) {
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
    if (death.getDeathLocation().getLocationCountry() != null) {
      if (death.getDeathLocation().getLocationCountry().getCountryName() != null
          && death.getDeathLocation().getLocationCountry().getCountryName().length() >= 4) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Death saveEntity(Death death) {
    if (validateEntity(death)) {
      if (death.getDeathLocation().getId() != null && locationService.validateEntity(death.getDeathLocation())) {
        death.setDeathLocation(locationService.updateEntity(death.getDeathLocation()));
      }
      if (death.getDeathYear() != null &&
          death.getDeathMonth() != null &&
          death.getDeathDay() != null) {
        if (dateUtils.validateDate(death.getDeathYear(), death.getDeathMonth(), death.getDeathDay())) {
          death.setDeathAccurate(1);
        } else {
          death.setDeathAccurate(0);
        }
      } else {
        death.setDeathAccurate(0);
      }
      return deathDao.saveEntity(death);
    } else {
      return null;
    }
  }

  @Override
  public Death updateEntity(Death entity) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateEntity'");
  }

  @Override
  public void deleteEntity(Death entity) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteEntity'");
  }

  @Override
  public void addEntity(Death entity) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'addEntity'");
  }

  @Override
  public Death getEntityById(int id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getEntityById'");
  }

  @Override
  public List<Death> getAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAll'");
  }

  @Override
  public List<Death> getAllOrderBy(String column) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAllOrderBy'");
  }

  @Override
  public List<Death> getAllByColumnValue(String column, String value) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAllByColumnValue'");
  }

}
