package com.arbuthnot.FamilyTree.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arbuthnot.FamilyTree.dao.EntityDAO;
import com.arbuthnot.FamilyTree.dao.PersonDAOImpl;
import com.arbuthnot.FamilyTree.entity.Person;

import jakarta.persistence.EntityManager;

//@RestController
// @RequestMapping("familytree")
public class PersonController {

  @Autowired
  private EntityManager entityManager;

  private EntityDAO entityDAO;

  @GetMapping()
  public String welcome() {
    return "Welcome to you Family Tree";
  }

  @GetMapping("/persons")
  public String getPersons() {
    List<?> people = new ArrayList<Person>();
    people = new PersonDAOImpl(entityManager).getEntityList();
    for (Object o : people) {
      if (o instanceof Person) {
        o = (Person) o;
        System.out.println(((Person) o).toString());
      }
      return "persons";
    }
    return "persons";
  }

}
