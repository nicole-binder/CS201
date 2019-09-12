/*
Person.java
 * Nicole Binder and Rosemary Wonnell, Carleton College, 2019-01-13
 * A class that creates a Person object specified by the variables of a given name,
   family name, year of birth, month of birth, and day of birth. Contains methods that return
   the age of the person, their last name, and their full name.
 * see PeopleSorter.java
*/


public class Person {
    private String givenName;
    private String familyName;
    private int yearOfBirth;
    private int monthOfBirth;
    private int dayOfBirth;

    public Person(String givenName, String familyName, int year, int month, int day) {
       // Initialize the instance variables here
       this.givenName = givenName;
       this.familyName = familyName;
       this.yearOfBirth = year;
       this.monthOfBirth = month;
       this.dayOfBirth = day;
    }

    public int getAge() {
       return 2018-this.yearOfBirth;
    }

    public String getLast(){
      return this.familyName;
        }

    public String getFullName() {
      if (this.givenName.isEmpty()) {
        return this.familyName;
      } else {
      return this.givenName + " " + this.familyName;
    } }


}
