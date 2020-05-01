/**
 * LinkedList.java
 * Nicole Binder, Katherine McFerrin 1/19/19
 * Adapted from a program by Jeff Ondich
 *
 * Introducing linked lists. We're not trying to do this generally
 * here. This is a linked list of String (not of anything else), and
 * that assumption is built into all the interfaces here.
 */

import java.io.*;
import java.util.*;

/**
 * LinkedList is a singly-linked list of String, designed to introduce
 * the basic mechanisms of link list construction and use.
 */
public class LinkedList {
    private Node head;

    private class Node {
        public String key;
        public Node next;

        public Node() {
            this.next = null;
            this.key = null;
        }

        public Node(String s) {
            this.key = s;
            this.next = null;
        }
    }

    /**
     * Default constructor
     */
    public LinkedList() {
        this.head = null;
    }

    /**
     * Appends the specified String to the end of this list
     * @param s the String to be inserted into this list.
     */
    public void add(String s) {
        // Get a new node ready for addition to the list.
        Node newNode = new Node(s);

        // There are two cases to handle: adding to an
        // empty list or adding to a non-empty list.
        if (this.head == null) {
            this.head = newNode;
        } else {
            // To add the new node to the end of the list, we have
            // to walk from the head of the list to the very last
            // node on the list, and then attach the new node there.
            Node currentNode = this.head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
        }
    }

    /**
     * Returns a string representation of this list.
     * @return a string representation of this list.
     */
    public String toString() {
        String stringRepresentation = "[";
        Node currentNode = this.head;
        while (currentNode != null) {
            if (currentNode != this.head) {
                stringRepresentation = stringRepresentation + ", ";
            }
            stringRepresentation = stringRepresentation + currentNode.key;
            currentNode = currentNode.next;
        }
        stringRepresentation = stringRepresentation + "]";
        return stringRepresentation;
    }

    /**
     * Prints this LinkedList to standard output, one String per line.
     */
    public void print() {
        Node currentNode = this.head;
        while (currentNode != null) {
            System.out.println(currentNode.key);
            currentNode = currentNode.next;
        }
    }

    /**
     * Prints this LinkedList to standard output, one String per line.
     * This method is here to demonstrate simple recursion with linked lists.
     */
    public void recursivePrint() {
        this.printStartingAtNode(this.head);
    }

    /**
     * Prints the tail of this LinkedList to standard output, one String
     * per line, starting with the specified Node. This is a private method
     * used only by recursivePrint.
     * @param node the node from which to begin printing.
     */
    private void printStartingAtNode(Node node) {
        if (node != null) {
            System.out.println(node.key);
            this.printStartingAtNode(node.next);
        }
    }

    ////// START OF THE CODE YOU NEED TO IMPLEMENT //////

    /**
     * Inserts the specified String at the specified position in this list.
     * @param index the 0-based position at which the string should be inserted.
     * @param s the String to be inserted into this list.
     */
    public void add(int index, String s) {
    	if (index < 0 && index > this.size() - 1) {
        System.err.println("Index Out of Range");
        System.exit(1);
      }
        Node newNode = new Node(s);
        if (this.head == null) {
            this.head = newNode;
        } else if (index == 0){
            Node temp = this.head;
            this.head = newNode;
            newNode.next = temp;
        }
        else {
            Node temp2 = new Node();
            Node currentNode = this.head;
            int counter = 0;
            while (currentNode.next != null) {
                if (counter == index-1) {
                  temp2.next = currentNode.next;
                  break;
                }
                currentNode = currentNode.next;
                counter++;
            }
            currentNode.next = newNode;
            newNode.next = temp2.next;
    }
}


    /**
     * Appends items in the specified list to the end of this list, in the same order
     * in which they appear in the other list.
     * @param otherList the list to appended onto this list.
     */
    public void add(LinkedList otherList) {
      if (this.head == null) {
          this.head = otherList.head;
      } else {
          Node currentNode = this.head;
          while (currentNode.next != null) {
              currentNode = currentNode.next;
          }
          currentNode.next = otherList.head;
    }
}

    /**
     * Removes all the items from this list.
     */
    public void clear() {
      this.head = null;
    }

    /**
     * Returns the String at the specified 0-based position in this list.
     * @param index the index of the String to return.
     * @return the String at the specified index, or null if the index is out of range.
     */
    public String get(int index) {
      String result = "";
      Node currentNode = this.head;
      int counter = 0;
      while (currentNode != null) {
          if (counter == index) {
                result = currentNode.key;
                break;
          }
          currentNode = currentNode.next;
          counter++;
      }
      return result;
    }

    /**
     * Removes the String at the specified 0-based position in this list.
     * @param index the index of the String to remove.
     */
    public void remove(int index) {
      if (index < 0 && index > this.size() - 1) {
        System.err.println("Index Out of Range");
        System.exit(1);
      }
        if (this.head == null) {
          System.err.println("This list in empty");
          System.exit(1);
        } else if (index == 0){
            Node currentNode = this.head;
            this.head = currentNode.next;
        }
        else {
            Node currentNode = this.head;
            int counter = 0;
            while (currentNode.next != null) {

                if (counter == index-1) {
                  Node temp = currentNode.next;
                  currentNode.next = temp.next;
                }
                counter++;
                currentNode = currentNode.next;
            }
    }
}

    /**
     * Returns the number of strings in this list.
     * @return the number of strings in this list.
     */
    public int size() {
    	Node currentNode = this.head;
    	int size = 0;
        if (currentNode != null){
            while (currentNode.next != null) {
                size++;
                currentNode = currentNode.next;
            }
            size++;
            }
        return size;
    }

    /**
     * Returns the number of strings in this list, but does its job recursively.
     * @return the number of strings in this list.
     */
    public int recursiveSize() {
        int size = this.recursiveStartingAtNode(this.head);
      return size;
    }

    private int recursiveStartingAtNode(Node node){
      if (node != null){
          return 1 + this.recursiveStartingAtNode(node.next);
      }
      return 0;
    }

    /**
     * Returns a LinkedList containing the Strings found in this list between the
     * specified fromIndex, inclusive, and toIndex, exclusive. (If fromIndex and
     * toIndex are equal, the returned list is empty.)
     *
     * For example, if this list is ["ant", "bat", "cat", "dog"], then
     * sublist(1,3) would return a new list ["bat", "cat"].
     *
     * @param fromIndex the index in this list at which the sublist should begin
     * @param toIndex the index in this list just after the end of the sublist
     * @return the sublist described above.
     */
    public LinkedList subList(int fromIndex, int toIndex) {
      LinkedList result = new LinkedList();
      if (fromIndex == toIndex){
        return result;
      }
      Node currentNode = this.head;
      int counter = 0;
      while (currentNode != null){
          if(counter == fromIndex){
              Node temp = new Node();
              temp.key = currentNode.key;
              result.head = temp;
              while (counter < toIndex ) {
                Node temp2 = new Node();
                temp2.key = currentNode.key;
                result.add(temp2.key);
                currentNode = currentNode.next;
                counter++;
            }
                break;
          }
          currentNode = currentNode.next;
          counter++;
      }
      return result;
    }

    ////// END OF THE CODE YOU NEED TO IMPLEMENT //////

    public static void main(String[] args) {
        // Test add(String), print, recursivePrint, and toString.
        System.out.println("=== Build a list of animals using add(String) ===");
        LinkedList theList = new LinkedList();
        theList.add("cat");
        theList.add("dog");
        theList.add("emu");
        theList.add("coatimundi");
        theList.add("newt");

        System.out.println("=== (iterative) print ===");
        theList.print();
        System.out.println("");

        System.out.println("=== recursivePrint ===");
        theList.recursivePrint();
        System.out.println("");

        System.out.println("=== toString ===");
        System.out.println(theList.toString());
        System.out.println("");

        // Test size() and recursiveSize()
        System.out.println("=== testing size() on the previous list ===");
        System.out.println("List length: " + theList.size());
        System.out.println("");
        System.out.println("=== testing recursiveSize() on the previous list ===");
        System.out.println("List length: " + theList.recursiveSize());
        System.out.println("");

        // Test get(int)
        System.out.println("=== get(0), get(3), get(size() - 1) ===");
        System.out.println(theList.get(0));
        System.out.println(theList.get(3));
        System.out.println(theList.get(theList.size() - 1));
        System.out.println("");

        // Test subList(int, int)
        System.out.println("=== subList(1, 4) ===");
        LinkedList subList = theList.subList(1, 4);
        System.out.println(theList.toString());
        if (subList != null) {
            System.out.println(subList.toString());
        }
        System.out.println("");

        // Test remove(int)
        System.out.println("=== remove(1) (you should see the original list, then again with second item gone) ===");
        System.out.println(theList.toString());
        theList.remove(1);
        System.out.println(theList.toString());
        System.out.println("");

        System.out.println("=== remove(0) (you should see the previous list, then again with its first item gone) ===");
        System.out.println(theList.toString());
        theList.remove(0);
        System.out.println(theList.toString());
        System.out.println("");

        // Test add(LinkedList)
        LinkedList newList = new LinkedList();
        newList.add("wallaby");
        newList.add("xantis");
        newList.add("yak");
        System.out.println("=== add(LinkedList) (previous list, new list, then the concatenation of the two) ===");
        System.out.println(theList.toString());
        System.out.println(newList.toString());
        theList.add(newList);
        System.out.println(theList.toString());
        System.out.println("");

        // Test add(int, String)
        System.out.println("=== previous list, then add(0, aardvark), add(size(), zebu), add(4, pig) ===");
        System.out.println(theList.toString());
        theList.add(0, "aardvark");
        System.out.println(theList.toString());
        theList.add(theList.size(), "zebu");
        System.out.println(theList.toString());
        theList.add(4, "pig");
        System.out.println(theList.toString());
        System.out.println("");

        // Test clear(), and then size() again.
        System.out.println("=== clear (should see blank line) ===");
        theList.clear();
        System.out.println(theList.toString());
        System.out.println("=== clear an empty list (should see blank line, no crash) ===");
        theList.clear();
        System.out.println(theList.toString());
        System.out.println("=== testing size() on an empty list ===");
        System.out.println("List length: " + theList.size());
    }
}
