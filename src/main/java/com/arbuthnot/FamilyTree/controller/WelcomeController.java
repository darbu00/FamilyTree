package com.arbuthnot.FamilyTree.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.arbuthnot.FamilyTree.dao.CountryDAOImpl;
import com.arbuthnot.FamilyTree.dao.MarriageDAOImpl;
import com.arbuthnot.FamilyTree.dao.PersonDAOImpl;
import com.arbuthnot.FamilyTree.dao.TestDAOImpl;
import com.arbuthnot.FamilyTree.entity.Birth;
import com.arbuthnot.FamilyTree.entity.Death;
import com.arbuthnot.FamilyTree.entity.Location;
import com.arbuthnot.FamilyTree.entity.Marriage;
//import com.arbuthnot.FamilyTree.dao.PersonRepository;
import com.arbuthnot.FamilyTree.entity.Person;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;

@Controller
@RequestMapping("/familytree")
public class WelcomeController {

  @Autowired
  private EntityManager entityManager;

  @GetMapping("/welcome")
  public String welcome(Model model) {
    model.addAttribute("date", java.time.LocalDateTime.now());

    return "welcome";
  }

  @GetMapping("/persons")
  public String getAllPersons(Model model) {
    List<Person> people = new ArrayList<Person>();
    people = new PersonDAOImpl(entityManager).getEntityList();
    // System.out.println("The size of people is : " + people.size());
    model.addAttribute("people", people);
    return "personsNew";
  }

  @GetMapping("/test")
  public List<Person> testGetChildren(Model model) {
    List<Person> children = new ArrayList<Person>();
    children = new TestDAOImpl(entityManager).findChildrenByParentIdTest(1030);
    // System.out.println("The size of people is : " + people.size());
    model.addAttribute("children", children);
    return children;
  }

  @GetMapping("/personDetails")
  public String personDetails(@RequestParam("id") Integer id, Model model) {
    Person person = new PersonDAOImpl(entityManager).findEntityById(id);
    Person father = person.getFather();
    System.out.println("Father : " + father);
    Person mother = person.getMother();
    System.out.println("Mother : " + mother);
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
      // System.out.println("..... Got a birth record from the database .....");
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
      // System.out.println("..... Got a death record from the database .....");
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
      // System.out.println("..... Got a father record from the database .....");
      // System.out.println("Father : " + father.toString());
    } else {
      father = new Person();
    }

    if (mother != null) {
      // System.out.println("..... Got a mother record from the database .....");
      // System.out.println("Mother : " + mother.toString());
    } else {
      mother = new Person();
    }

    marriagesSpouses = new MarriageDAOImpl(entityManager).getMarriagesBySpouseId(person.getId());

    // Separate marriage records and spouse records into separate variables
    if (marriagesSpouses.size() >= 1) {
      for (int i = 0; i < marriagesSpouses.size(); i += 2) {
        for (int j = 0; j < marriagesSpouses.get(0).size(); j++) {
          // System.out.println(marriagesSpouses.get(i).get(j));
          // System.out.println(marriagesSpouses.get(i + 1).get(j));
          marriages.add((Marriage) marriagesSpouses.get(i).get(j));
          spouses.add((Person) marriagesSpouses.get(i + 1).get(j));
        }

      }

    }

    // Look for siblings
    if (father != null && mother != null) {
      if (father.getId() != null || mother.getId() != null) {
        siblings = new PersonDAOImpl(entityManager).getSiblings(person.getId(), father.getId(), mother.getId());
      }
    }

    // Look for children
    children = new PersonDAOImpl(entityManager).findChildrenByParentId(person.getId());

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
    // System.out.println(person);
    return "personDetails";
  }

  @PostMapping("/save")
  public String savePerson(@ModelAttribute("person") Person person) {

    // code here for saving updates to person
    //

    return "redirect:/familytree/persons";
  }

}
