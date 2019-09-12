/*
PeopleSorter.java
 * Nicole Binder and Rosemary Wonnell, Carleton College, 2019-01-13
 * A class to sort a given file of people first by alphabetization of last name and then by age, where a person consists of variables
 specified by the "Person.java" class
 * see Person.java
*/



import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;


public class PeopleSorter {
    public static void main(String[] args) {
      //main class, reads in file in command line and calls methods to sort and print file
        if (args.length == 0) {
            System.err.println("Usage: java CommandLine inputFilePath");
            System.exit(1);
        }
        String personFilePath = args[0];
        // Call loadPersonList to build an ArrayList of Person objects
        // from the input file.
        ArrayList<Person> personList = loadPersonList(personFilePath);
        sortPersonList(personList);
        printPersonList(personList);
    }

    public static ArrayList<Person> loadPersonList(String personFilePath) {
        // Use File and Scanner to set up a Scanner for the input file
        File inputFile = new File(personFilePath);

        Scanner scanner = null;
        try {
            scanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(1);
          }
        // Iterate through the file, instantiating new Person objects and adding
        // them to personList.
        ArrayList<Person> personList = new ArrayList<Person>();
        while (scanner.hasNextLine()) {
           String line = scanner.nextLine();
           String[] variables = line.split(",");
           String familyName = variables[0];
           String givenName = variables[1];
           String yearString = variables[2];
           int year = Integer.parseInt(yearString);
           String mString = variables[3];
           int month = Integer.parseInt(mString);
           String dString = variables[4];
           int day = Integer.parseInt(dString);
           Person person = new Person(givenName, familyName, year, month, day);
           personList.add(person);
         }

        return personList;



    }

    public static int compare(Person a, Person b) {
      //called within sortPersonList to decide whether to sort by name or age
      int compareLastName = a.getLast().compareTo(b.getLast());
        if (compareLastName != 0) {
            return compareLastName;
        } else {
            return a.getAge()-b.getAge();
        }
    }

    public static void sortPersonList(ArrayList<Person> personList) {
      //insertion sort (and compare) code borrowed from https://stackoverflow.com/questions/32794313/using-an-insertion-sort-algorithm-using-more-than-one-field-from-an-array-of-obj
      //sort by either name or age attribute, depending on whether items are already sorted by last name
      int in, out;

      for (out = 1; out < personList.size(); out++) {
        Person temp = personList.get(out);
        in = out;

        while (in > 0 && compare(personList.get(in-1), temp) > 0) {
            personList.set(in, personList.get(in - 1));
            --in;
        }
          personList.set(in, temp);
        }



    }


    public static void printPersonList(ArrayList<Person> personList) {
      //prints sorted personList
      for (int k = 0;k<personList.size();k++){
        String fullName = personList.get(k).getFullName();
        int age = personList.get(k).getAge();
        System.out.print(fullName + " ");
        System.out.println(age);

      }

    }
}
