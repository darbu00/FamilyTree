package com.arbuthnot.FamilyTree.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import com.arbuthnot.FamilyTree.dao.PersonDAOImpl;
import com.arbuthnot.FamilyTree.entity.Person;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityManager;

@Repository
public class PersonApi {

  // @Autowired
  // EntityManager entityManager;

  public String getAllPersonsList(EntityManager entityManager) {
    List<Person> allPersonsList = new ArrayList<Person>();
    ObjectMapper om = new ObjectMapper();

    allPersonsList = new PersonDAOImpl(entityManager).getEntityList();

    // try {
    // System.out.println(om.writeValueAsString(allPersonsList));
    // } catch (JsonProcessingException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }

    try {
      return om.writeValueAsString(allPersonsList);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return "";
    }

  }
}
