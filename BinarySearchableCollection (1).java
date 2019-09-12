/**
 * Nicole Binder and Margaret Anne Puzak 2019 Feb 18
 * BinarySearchableCollection.java
 *
 * Implements SearchableCollection interface using a Binary Search, and calls Search Timer to time the searches
 *
 */

import java.util.*;
import java.io.*;
import java.lang.*;

public class BinarySearchableCollection<KeyType extends Comparable<KeyType>, ValueType>
        implements SearchableCollection<KeyType, ValueType> {

    private ArrayList<KeyValuePair> list;

    public class KeyValuePair {
        public KeyType key;
        public ValueType value;

        public KeyValuePair(KeyType key, ValueType value) {
            this.key = key;
            this.value = value;
        }
    }

    public BinarySearchableCollection() {
        this.list = new ArrayList<KeyValuePair>();
    }

    public int size() {
        return this.list.size();
    }

    public void add(KeyType key, ValueType value) {
        KeyValuePair pair = new KeyValuePair(key, value);
        this.list.add(pair);

        KeyValuePair temp = new KeyValuePair(null, null);
        for (int i = 1; i < list.size(); i++) {
               temp.value = list.get(i).value;
               temp.key = list.get(i).key;
               int j = 0;
               for(j = i; j > 0; j--) {
                    if (temp.key.compareTo(list.get(j - 1).key) < 0) {
                         list.set(j, list.get(j - 1));
                         list.set(j-1, temp);
                    } else {
                          break;
                    }
                  }
          }
          /*for (int t = 0; t < list.size(); t++) {
            System.out.println(list.get(t).value);
          }*/
    }


    public boolean containsKey(KeyType searchKey) {
        return this.find(searchKey) != null;
    }

    public ValueType find(KeyType searchKey) {
        int first = 0;
        int last = list.size() - 1;
        int mid;
        while (first <= last) {
            mid = first + ((last - first) / 2);
            if (searchKey == list.get(mid).key) {
                return list.get(mid).value;
            } else if (searchKey.compareTo(list.get(mid).key) < 0) {
                last = mid - 1;
            } else {
                first = mid + 1;
            }
        }
    return null;
    }

    public static void main(String[] args) {
      BinarySearchableCollection<String, String> collection = new BinarySearchableCollection<String, String>();

      String fileName = args[0];
      String searchKey = args[1];
      int searchCount = Integer.parseInt(args[2]);
      File inputFile = new File(fileName);

      Scanner scanner = null;
      try {
          scanner = new Scanner(inputFile);
      }
      catch (FileNotFoundException e) {
          System.err.println(e);
          System.exit(1);
      }

      for ( int k = 0; k < searchCount; k++) {
          String word = scanner.nextLine();
          collection.add(word, word);
      }

      SearchTimer timer = new SearchTimer(collection, searchKey);
      timer.binarySearchTime();


        // Two simple tests of our BinarySearchableCollection implementation.

        // 1. Test BinarySearchableCollection on a collection of Strings
      /*  BinarySearchableCollection<String, Integer> stringCollection = new BinarySearchableCollection<String, Integer>();
        stringCollection.add("goat", 4);
        stringCollection.add("civet", 5);
        stringCollection.add("ocelot", 6);
        stringCollection.add("capybara", 8);
        stringCollection.add("elk", 3);

        String searchKey = "civet";
        if (stringCollection.containsKey(searchKey)) {
            System.out.println("Yay! Found " + searchKey);
        } else {
            System.out.println("Boo! Did not find " + searchKey);
        }

        searchKey = "cthulhu";
        if (stringCollection.containsKey(searchKey)) {
            System.out.println("Boo! Found " + searchKey);
        } else {
            System.out.println("Yay! Did not find " + searchKey);
        }

        // 2. Test BinarySearchableCollection on a collection of Persons
        BinarySearchableCollection<String, Person> personCollection = new BinarySearchableCollection<String, Person>();
        Person p = new Person("Isaac", "Newton", 1642);
        personCollection.add(p.getFamilyName(), p);
        p = new Person("Ada", "Lovelace", 1815);
        personCollection.add(p.getFamilyName(), p);
        p = new Person("Johnny", "von Neumann", 1903);
        personCollection.add(p.getFamilyName(), p);
        p = new Person("Jean", "Bartik", 1924);
        personCollection.add(p.getFamilyName(), p);

        searchKey = "Bartik";
        Person person = personCollection.find(searchKey);
        if (person != null) {
            System.out.println("Yay! Found " + person.getFullName());
        } else {
            System.out.println("Boo! Did not find " + searchKey);
        }

        searchKey = "Turing";
        person = personCollection.find(searchKey);
        if (person != null) {
            System.out.println("Boo! Found " + person.getFullName());
        } else {
            System.out.println("Yay! Did not find " + searchKey);
        }*/
    }
}
