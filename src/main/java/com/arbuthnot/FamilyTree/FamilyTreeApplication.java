package com.arbuthnot.FamilyTree;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.arbuthnot.FamilyTree.dao.EntityDAO;
import com.arbuthnot.FamilyTree.entity.Person;

@SpringBootApplication
public class FamilyTreeApplication {

  public static void main(String[] args) {
    SpringApplication.run(FamilyTreeApplication.class, args);
  }

  // @Bean
  // public CommandLineRunner commandLineRunner(PersonDAOImpl personDAO) {

  // return runner -> {
  // getAllPeople(personDAO);
  // getAPerson(personDAO);
  // System.out.println("..... Inside Runner .....");
  // getPeopleByLastName(personDAO);
  // };
  // }

  @SuppressWarnings("unchecked")
  private void getAllPeople(EntityDAO personDAO) {
    List<Person> people = new ArrayList<Person>();
    people = (List<Person>) personDAO.getEntityList();

    for (Person person : people) {
      System.out.println(person.toString());
      // System.out.print(person.getFirstName() + " ");
      // System.out.print(person.getMiddleName() + " ");
      // System.out.print(person.getLastName() + " ");
      // System.out.print(person.getGeneration() + " ");
      // System.out.print(person.getNickName() + " \n");
    }
  }

  @SuppressWarnings("unchecked")
  private void getPeopleByLastName(EntityDAO personDAO) {
    List<Person> people = new ArrayList<Person>();
    System.out.println(".....     Inside getPeopleByLastName     .....");
    people = (List<Person>) personDAO.getEntityByColumn("lastName", "Arbuthnot");
    for (Person person : people) {
      System.out.println(person.toString());
    }

  }

}
