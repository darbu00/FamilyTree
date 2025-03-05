package com.arbuthnot.FamilyTree.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arbuthnot.FamilyTree.dao.CountryDAOImpl;
import com.arbuthnot.FamilyTree.dao.LocationDAOImpl;
import com.arbuthnot.FamilyTree.entity.Country;
import com.arbuthnot.FamilyTree.entity.Location;
import com.arbuthnot.FamilyTree.utils.FileUtils;

@Service
public class LocationServiceImpl implements EntityService<Location> {

  private LocationDAOImpl locationDao;

  @Autowired
  private CountryDAOImpl countryDao;

  @Autowired
  public LocationServiceImpl(LocationDAOImpl locationDao) {
    this.locationDao = locationDao;
  }

  @Override
  public boolean validateEntity(Location location) {
    System.out.println("\n.... verifying location : " + location);
    if (!location.getLocationCountry().getCountryName().trim().isBlank()) {
      return true;
    } else {
      return false;
    }
  }

  @Transactional
  @Override
  public Location saveEntity(Location location) {
    List<String> validState = validateState(location.getLocationState());
    if (validState.size() == 2) {
      location.setLocationState(validState.get(0).trim());
      location.setLocationStateShort(validState.get(1).trim());
    } else {
      System.out.println("\n...   Could not validate the entered state   ...\n");
      location.setLocationState("");
      location.setLocationStateShort("");
    }
    if (location.getId() == null || location.getId() > 0) {
      location.setLocationCountry(getValidCountry(location.getLocationCountry()));
      return locationDao.updateEntity(location);
    } else {
      location.setLocationCountry(getValidCountry(location.getLocationCountry()));
      return locationDao.saveEntity(location);
    }
  }

  @Transactional
  @Override
  public Location updateEntity(Location location) {
    location.setLocationCountry(getValidCountry(location.getLocationCountry()));
    return locationDao.updateEntity(location);
  }

  @Override
  public void deleteEntity(Location entity) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteEntity'");
  }

  @Override
  public void addEntity(Location entity) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'addEntity'");
  }

  @Override
  public Location getEntityById(int id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getEntityById'");
  }

  @Override
  public List<Location> getAll() {
    return locationDao.getEntityList();
  }

  @Override
  public List<Location> getAllOrderBy(String column) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAllOrderBy'");
  }

  @Override
  public List<Location> getAllByColumnValue(String column, String value) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAllByColumnValue'");
  }

  public Country getValidCountry(Country country) {
    if (country instanceof Country) {
      if ((country.getId() == null || country.getId() == 0) && country.getCountryName().trim() != null) {
        country = setCountryByCountryName(country.getCountryName().trim());
      } else if (country.getId() != null && country.getId() != 0) {
        if (country.getCountryName().trim() == countryDao.findEntityById(country.getId()).getCountryName().trim()) {
          System.out.println("\n....  Country is already in DB    ....\n");
          country = null;
        } else {
          System.out.println("\n....    Country Id does not match country Name in DB    ....\n");
          country = setCountryByCountryName(country.getCountryName().trim());
        }

      }
      return country;
    } else {
      return null;
    }

  }

  public Country setCountryByCountryName(String countryName) {
    Country country = new Country();
    List<Country> countries = countryDao.getEntityByColumn("countryName",
        countryName.trim());
    if (countries.size() > 0) {
      if (countries.size() == 1) {
        Country c = (Country) countries.get(0);
        country = c;
      } else {
        System.out.println("\n....     Got more than one Country that matches that name    ....\n");
        country = null;
      }
    } else if (countries.size() == 0) {
      System.out.println("\n....    Could not find Country    ....\n");
      country = null;
    }
    return country;
  }

  public List<String> validateState(String state) {
    List<String> validState = new ArrayList<String>();
    FileUtils fileUtils = new FileUtils();
    List<String> validStates = new ArrayList<String>();

    String rootDir = fileUtils.getProjectRoot();
    String resourcesDir = "/src/main/resources";

    try {
      validStates = fileUtils.readFile(rootDir + resourcesDir + "/static/us_states.txt");
    } catch (Exception e) {
      System.out.println("\n....  Error reading file us_states.txt  ....\n");
      e.printStackTrace();
    }

    if (validStates.size() > 0) {
      lineLoop: for (String line : validStates) {
        if (!line.isBlank()) {
          String stateShort = line.split(",")[0];
          String stateName = line.split(",")[1];
          if (state.trim().toLowerCase().equals(stateShort.trim().toLowerCase())
              || state.trim().toLowerCase().equals(stateName.trim().toLowerCase())) {
            validState.add(stateName);
            validState.add(stateShort);
            break lineLoop;
          }
        }
      }

    }
    return validState;
  }

}
