package com.arbuthnot.FamilyTree.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arbuthnot.FamilyTree.dao.PersonDAOImpl;
import com.arbuthnot.FamilyTree.entity.Birth;
import com.arbuthnot.FamilyTree.entity.Death;
import com.arbuthnot.FamilyTree.entity.Location;
import com.arbuthnot.FamilyTree.entity.Marriage;
import com.arbuthnot.FamilyTree.entity.Person;
import com.arbuthnot.FamilyTree.entity.PersonMarriage;

@Service
public class PersonServiceImpl implements EntityService<Person> {

  private PersonDAOImpl personDao;

  private LocationServiceImpl locationService;
  private BirthServiceImpl birthService;
  private DeathServiceImpl deathService;
  private MarriageServiceImpl marriageService;

  @Autowired
  public PersonServiceImpl(PersonDAOImpl personDAOImpl, LocationServiceImpl locationService,
      BirthServiceImpl birthService, DeathServiceImpl deathService, MarriageServiceImpl marriageService) {
    this.personDao = personDAOImpl;
    this.locationService = locationService;
    this.birthService = birthService;
    this.deathService = deathService;
    this.marriageService = marriageService;
  }

  @Override
  public boolean validateEntity(Person person) {
    // valid Person must have First and Last name
    if (person.getFirstName() != null && person.getFirstName().length() >= 1) {
      if (person.getLastName() != null && person.getLastName().length() >= 1) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  @Override
  public Person saveEntity(Person entity) {
    if (entity instanceof Person) {

    }
    return new Person();
  }

  public Person saveEntity(Person person, Location currentLocation, Birth birth, Death death,
      Marriage marriage) {
    if (person instanceof Person) {

      Location savedLocation;
      Birth savedBirth;
      Death savedDeath;
      Marriage savedMarriage;

      if (locationService.validateEntity(currentLocation)) {
        if (currentLocation != null && currentLocation.getId() != 0) {
          savedLocation = locationService.updateEntity(currentLocation);
        } else if (currentLocation != null) {
          savedLocation = locationService.saveEntity(currentLocation);
        } else {
          savedLocation = null;
        }
      } else {
        savedLocation = null;
      }

      if (birth != null && birthService.validateEntity(birth)) {
        savedBirth = birthService.saveEntity(birth);
      } else {
        savedBirth = null;
      }

      if (death != null && deathService.validateEntity(death)) {
        savedDeath = deathService.saveEntity(death);
      } else {
        savedDeath = null;
      }

      person.setCurrentLocation(savedLocation);
      person.setBirth(savedBirth);
      person.setDeath(savedDeath);

      Person savedPerson = personDao.saveEntity(person);
      marriage.addSpouseIdToPersonMarriage(savedPerson.getId());

      if (marriage != null && marriageService.validateEntity(marriage)) {
        System.out.println("\n....    Marriage Validated    ....\n");
        savedMarriage = marriageService.saveEntity(marriage);
        System.out.println(savedMarriage);
      } else {
        savedMarriage = null;
      }

      ArrayList<Marriage> savedMarriages = new ArrayList<Marriage>();
      savedMarriages.add(savedMarriage);

      person.setMarriages(savedMarriages);

    }
    System.out.println("\n..  Saved person is:  ..\n" + person.toString());
    return new Person();
    // return personDao.saveEntity(person);
  }

  @Override
  public Person updateEntity(Person entity) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateEntity'");
  }

  @Override
  public void deleteEntity(Person entity) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteEntity'");
  }

  @Override
  public void addEntity(Person person) {

  }

  public void addPerson(Person person, Location currentLocation, Birth birth, Death death,
      Marriage marriage, PersonMarriage personMarriage) {

    boolean personValid = false;
    boolean currentLocationValid = false;
    boolean birthValid = false;
    boolean deathValid = false;
    boolean marriagesValid = false;

    if (person instanceof Person) {
      // TODO have validatePerson return an array list of
      // errors for person, birth, death, location, mariages
      // so they can be displayed to user
      if (validateEntity(person)) {
        // validate location information
        if (person.getPersonCurrentLocationId() != 0) {
          if (locationService.validateEntity(currentLocation)) {
            // this is a valid location that already exist in the DB
            // merge/update if necessary
            currentLocation.setId(person.getPersonCurrentLocationId());
            person.setCurrentLocation(currentLocation);
            currentLocationValid = true;
          }
        } else if (locationService.validateEntity(currentLocation)) {
          // this will be a brand new location and/or not autofilled from search
          // before saving this we should see if the location already exists
          // in the DB and can be reused
          currentLocationValid = true;
        }
        //
        // validate birth information
        if (birth != null) {
          if (birthService.validateEntity(birth)) {
            if (birth.getBirthLocationId() != 0) {
              birth.saveBirthLocationId(birth.getBirthLocationId());
            }
            person.setBirth(birth);
            birthValid = true;
          } else {
            person.setBirth(null);
          }
        }
        // validate death information
        if (death != null) {
          if (deathService.validateEntity(death)) {
            if (death.getDeathLocationId() != 0) {
              death.saveDeathLocationId(death.getDeathLocationId());
            }
            person.setDeath(death);
            deathValid = true;
          } else {
            person.setDeath(null);
          }
        }
        // validate marriage information
        if (marriage != null) {
          if (personMarriage != null) {
            // TODO FIX THIS
            System.out.println("\n....    Setting spouse1 in personMarriage    ....\n");
            personMarriage.setSpouse1Id(1052);
          }
          marriage.setPersonMarriage(personMarriage);
          if (marriageService.validateEntity(marriage)) {
            ArrayList<Marriage> marriages = new ArrayList<Marriage>();
            marriages.add(marriage);
            person.setMarriages(marriages);
            marriagesValid = true;
          } else {
            person.setMarriages(null);
          }
        }
      } // end valid person

    }
    saveEntity(person, currentLocation, birth, death, marriage);
  }

  @Override
  public Person getEntityById(int id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getEntityById'");
  }

  @Override
  public List<Person> getAll() {
    return personDao.getEntityList();
  }

  @Override
  public List<Person> getAllOrderBy(String column) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAllOrderBy'");
  }

  @Override
  public List<Person> getAllByColumnValue(String column, String value) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAllByColumnValue'");
  }

}
