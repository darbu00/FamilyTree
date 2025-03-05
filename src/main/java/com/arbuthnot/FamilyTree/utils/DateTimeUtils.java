package com.arbuthnot.FamilyTree.utils;

public class DateTimeUtils {

  public boolean validateDate(int year, int month, int day) {

    if (year > 0 && month > 0 && day > 0) {
      boolean leapYear = isLeapYear(year);
      int maxDay = 31;
      if (year < java.time.LocalDate.now().getYear()) {
        if (month <= 12) {
          switch (month) {
            case 2:
              if (leapYear) {
                maxDay = 29;
              } else {
                maxDay = 28;
              }
              break;
            case 4, 6, 9, 11:
              maxDay = 30;
              break;
          }
          if (day <= maxDay) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public static boolean isLeapYear(int year) {
    if (year % 4 == 0) {
      if (year % 100 == 0) {
        return year % 400 == 0;
      } else {
        return true;
      }
    }
    return false;
  }

}
