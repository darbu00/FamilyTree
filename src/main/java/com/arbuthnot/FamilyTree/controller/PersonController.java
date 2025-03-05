package com.arbuthnot.FamilyTree.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.arbuthnot.FamilyTree.api.PersonApi;
import com.arbuthnot.FamilyTree.dao.CountryDAOImpl;
import com.arbuthnot.FamilyTree.dao.EntityDAO;
import com.arbuthnot.FamilyTree.dao.LocationDAOImpl;
import com.arbuthnot.FamilyTree.dao.MarriageDAOImpl;
import com.arbuthnot.FamilyTree.dao.PersonDAOImpl;
import com.arbuthnot.FamilyTree.entity.Birth;
import com.arbuthnot.FamilyTree.entity.Death;
import com.arbuthnot.FamilyTree.entity.Location;
import com.arbuthnot.FamilyTree.entity.Marriage;
import com.arbuthnot.FamilyTree.entity.PersonMarriage;
import com.arbuthnot.FamilyTree.entity.Person;
import com.arbuthnot.FamilyTree.service.EntityService;
import com.arbuthnot.FamilyTree.service.LocationServiceImpl;
import com.arbuthnot.FamilyTree.service.PersonServiceImpl;
import com.arbuthnot.FamilyTree.entity.Country;
import com.arbuthnot.FamilyTree.utils.ListUtils;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Controller
@RequestMapping("familytree/person")
public class PersonController {

  @Autowired
  private EntityManager entityManager;

  // private EntityDAO<Person> entityDao;
  private PersonServiceImpl personService;
  private LocationServiceImpl locationService;

  @Autowired
  public PersonController(PersonServiceImpl personService, LocationServiceImpl locationService) {
    this.personService = personService;
    this.locationService = locationService;
  }

  @GetMapping("/personList")
  public String getAllPersons(Model model) {
    List<Person> people = new ArrayList<Person>();
    people = personService.getAll();

    model.addAttribute("people", people);
    return "familytree/person/personList";
  }

  @GetMapping("/personAdd")
  public String addPersons(Model model) {

    Person person = new Person();
    List<Person> people = new ArrayList<Person>();
    people = personService.getAll();

    List<Location> locations = new ArrayList<Location>();
    locations = locationService.getAll();

    List<Country> countries = new ArrayList<Country>();
    countries = new CountryDAOImpl(entityManager).getEntityList();

    person.setFather(new Person());
    person.setMother(new Person());
    person.setBirth(new Birth());
    person.setDeath(new Death());
    person.setCurrentLocation(new Location());
    person.setMarriages(new ArrayList<Marriage>());
    Person father = new Person();
    Person mother = new Person();
    Birth birth = new Birth();
    Death death = new Death();
    Location currentLocation = new Location();
    PersonMarriage personMarriage = new PersonMarriage();
    Marriage marriage = new Marriage();

    model.addAttribute("person", person);
    model.addAttribute("father", father);
    model.addAttribute("mother", mother);
    model.addAttribute("birth", birth);
    model.addAttribute("death", death);
    model.addAttribute("currentLocation", currentLocation);
    model.addAttribute("people", people);
    model.addAttribute("locations", locations);
    model.addAttribute("countries", countries);
    model.addAttribute("personMarriage", personMarriage);
    model.addAttribute("marriage", marriage);

    return "familytree/person/personAdd";
  }

  @GetMapping("/personDetails")
  public String personDetails(@RequestParam("id") Integer id, Model model) {
    Person person = new PersonDAOImpl(entityManager).findEntityById(id);
    Person father = new PersonDAOImpl(entityManager).findEntityById(person.getFatherId());
    Person mother = new PersonDAOImpl(entityManager).findEntityById(person.getMotherId());
    Birth birth = person.getBirth();
    Death death = person.getDeath();
    List<List<?>> marriagesSpouses = new ArrayList<List<?>>();
    List<Marriage> marriages = new ArrayList<Marriage>();
    List<Person> spouses = new ArrayList<Person>();
    List<Person> children = new ArrayList<Person>();
    List<Person> siblings = new ArrayList<Person>();
    Location birthLocation = new Location();
    Location deathLocation = new Location();
    Location currentLocation;

    if (birth != null) {
      if (birth.getBirthLocationId() != 0 && birth.getBirthLocationId() != null) {
        birthLocation = birth.getBirthLocation();
        if (birthLocation.getLocationCountryId() != 0) {
          birthLocation.setLocationCountryName(
              new CountryDAOImpl(entityManager).findEntityById(birthLocation.getLocationCountryId())
                  .getCountryName());
        }
      }
    } else {
      birth = new Birth();
    }

    if (death != null) {
      if (death.getDeathLocationId() != 0 && death.getDeathLocationId() != null) {
        deathLocation = death.getDeathLocation();
        if (deathLocation.getLocationCountryId() != 0) {
          deathLocation.setLocationCountryName(
              new CountryDAOImpl(entityManager).findEntityById(deathLocation.getLocationCountryId())
                  .getCountryName());
        }
      }
    } else {
      death = new Death();
    }

    if (father != null) {
    } else {
      father = new Person();
    }

    if (mother != null) {
    } else {
      mother = new Person();
    }

    marriagesSpouses = new MarriageDAOImpl(entityManager).getMarriagesBySpouseId(person.getId());

    // Separate marriage records and spouse records into separate variables
    if (marriagesSpouses.size() >= 1) {
      for (int i = 0; i < marriagesSpouses.size(); i += 2) {
        for (int j = 0; j < marriagesSpouses.get(0).size(); j++) {
          marriages.add((Marriage) marriagesSpouses.get(i).get(j));
          spouses.add((Person) marriagesSpouses.get(i + 1).get(j));
        }
      }
    }

    // Look for siblings
    if (father != null && mother != null) {
      if (father.getId() != null || mother.getId() != null) {
        siblings = new PersonDAOImpl(entityManager).getSiblings(person.getId(),
            ((father.getId() != null) ? father.getId() : -1), ((mother.getId() != null) ? mother.getId() : -1));
      }
    }
    siblings = new ListUtils().sortPeopleByYearBirthAsc(siblings);

    // Look for children
    children = new PersonDAOImpl(entityManager).findChildrenByParentId(person.getId());
    children = new ListUtils().sortPeopleByYearBirthAsc(children);

    currentLocation = person.getCurrentLocation();
    if (person.getCurrentLocation() != null) {
      if (currentLocation.getLocationCountryId() != 0) {
        currentLocation.setLocationCountryName(
            new CountryDAOImpl(entityManager).findEntityById(currentLocation.getLocationCountryId()).getCountryName());
      }
    } else {
      currentLocation = new Location();
    }

    model.addAttribute("person", person);
    if (birth != null) {
      model.addAttribute("birth", birth);
    }
    if (death != null) {
      model.addAttribute("death", death);
    }
    if (father != null) {
      model.addAttribute("father", father);
    }
    if (mother != null) {
      model.addAttribute("mother", mother);
    }
    if (marriages != null) {
      model.addAttribute("marriages", marriages);
    }
    if (spouses != null) {
      model.addAttribute("spouses", spouses);
    }
    if (siblings != null) {
      model.addAttribute("siblings", siblings);
    }
    if (children != null) {
      model.addAttribute("children", children);
    }
    if (birthLocation != null) {
      model.addAttribute("birthLocation", birthLocation);
    }
    if (deathLocation != null) {
      model.addAttribute("deathLocation", deathLocation);
    }
    if (currentLocation != null) {
      model.addAttribute("currentLocation", currentLocation);
    }
    return "familytree/person/personDetails";
  }

  @PostMapping("/add")
  public String addPerson(@ModelAttribute("person") Person person,

      @ModelAttribute("currentLocation") Location currentLocation,

      @ModelAttribute("birth") Birth birth, @ModelAttribute("death") Death death,
      @ModelAttribute("marriage") Marriage marriage,
      @ModelAttribute("personMarriage") PersonMarriage personMarriage

  ) {

    personService.addPerson(person, currentLocation, birth, death, marriage, personMarriage);
    return "redirect:/familytree/person/personList";
  }

}
