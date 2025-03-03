package com.arbuthnot.FamilyTree.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.arbuthnot.FamilyTree.entity.Person;

public class ListUtils {

  public List<Person> sortPeopleByYearBirthAsc(List<Person> list) {
    Collections.sort(list, new PersonAgeComparator());
    return list;
  }

  private class PersonAgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person a, Person b) {
      if (a.getBirth() != null && b.getBirth() != null) {
        if (a.getBirth().getBirthYear() > 0 && b.getBirth().getBirthYear() > 0) {
          return a.getBirth().getBirthYear() - b.getBirth().getBirthYear();
        } else {
          return 0;
        }
      } else {
        return 0;
      }
    }
  }
}
