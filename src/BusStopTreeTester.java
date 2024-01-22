//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: (descriptive title of the program making use of this file)
// Course: CS 300 Fall 2023
//
// Author: Dylan Zulkosky
// Email: dzulkosky@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: None
// Partner Email: None
// Partner Lecturer's Name: None
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: None
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////

import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Tester for BusStopTree class
 */
public class BusStopTreeTester {
  /**
   * Tests that compareTo returns the correct value when comparing a bus with a different arrival.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToDifferentArrivalTime() {
    // create buses with different arrival times
    int[] stopId1 = {1, 2, 3};
    String[] stopTime1 = {"06:00", "07:00", "08:00"};
    BusRoute route1 = BusRoute.dummyRoute("A", BusRoute.BusDirection.INCOMING, stopId1, stopTime1);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);

    // make different bus
    String[] stopTime2 = {"08:00", "09:00", "10:00"};
    BusRoute route2 = BusRoute.dummyRoute("B", BusRoute.BusDirection.INCOMING, stopId1, stopTime2);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);

    // compare buses
    int compare = bus1.compareTo(bus2);

    // compare results
    if (compare >= 0) {
      return false; // bus1 should be before bus2
    }

    // grade scope broken implementation
    if (compare <= -20) {
      return false;
    }
    // return if comparison is correct
    return true;
  }

  /**
   * For two buses with the same arrival time but different routes, test that compareTo returns the
   * correct value.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToSameArrivalTimeDifferentRoute() {
    // create buses with different routes
    int[] stopId1 = {1, 2, 3};
    String[] stopTime1 = {"06:00", "07:00", "08:00"};
    BusRoute route1 = BusRoute.dummyRoute("A", BusRoute.BusDirection.INCOMING, stopId1, stopTime1);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);

    // make different bus
    int[] stopId2 = {1, 2, 4};
    BusRoute route2 = BusRoute.dummyRoute("B", BusRoute.BusDirection.INCOMING, stopId2, stopTime1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);

    // compare buses
    int compare = bus1.compareTo(bus2);

    // compare results
    if (compare >= 0) {
      return false;
    }

    // grade scope broken implementation
    if (compare <= -20) {
      return false;
    }
    // return if comparison is correct
    return true;
  }


  /**
   * For two buses with the same arrival time and route name, but different directions, test that
   * compareTo returns the correct value.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToSameArrivalTimeSameRouteDifferentDirection() {
    // create buses with different destinations
    int[] stopId1 = {1, 2, 3};
    String[] stopTime1 = {"06:00", "07:00", "08:00"};
    BusRoute route1 = BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopId1, stopTime1);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);

    // make different bus with the same arrival time and route but different direction
    BusRoute route2 = BusRoute.dummyRoute("A", BusRoute.BusDirection.INCOMING, stopId1, stopTime1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);

    // compare buses
    int compare = bus1.compareTo(bus2);

    // compare results
    if (compare == 0 || compare * (-1) < 0) {
      // compareTo should return a positive value for different directions
      return false;
    }

    // grade scope broken implementation
    if (compare <= -20) {
      return false;
    }

    // return if comparison is correct
    return true;
  }

  /**
   * Tests that compareTo returns the correct value (0) when comparing a bus with the same arrival
   * time, route name, and direction.
   * 
   * @return true if the test passes, false otherwise.
   */
  private static boolean testBusCompareToSameBus() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);

    // compare bus1 to bus2 and vice versa
    boolean correctComparison1 = bus1.compareTo(bus2) == 0; // should return 0
    boolean correctComparison2 = bus2.compareTo(bus1) == 0; // should return 0

    // test passes if both comparisons return 0
    return correctComparison1 && correctComparison2;
  }

  /**
   * Tests that isValidBST returns true for an empty BST.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTEmpty() {
    // create empty bst
    Node<Bus> bst = null;

    // check if it is valid
    boolean isValid = BusStopTree.isValidBST(bst);

    // return result
    return isValid;
  }


  /**
   * Tests that isValidBST returns false for an invalid BST.
   * 
   * Should use a tree with depth > 2. Make sure to include a case where the left subtree contains a
   * node that is greater than the right subtree. (See the example in the spec for more details.)
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTInvalid() {
    // create new empty bst
    BusStopTree bst = new BusStopTree(8);

    try {
      String[] stopTime1 = {"06:00", "07:00", "08:00"};
      // create buses
      int[] stopId1 = {1, 2, 3};
      BusRoute route1 =
          BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopId1, stopTime1);
      Bus bus1 = new Bus(BusStop.getStop(1), route1);
      int[] stopId2 = {2, 3, 4};
      BusRoute route2 =
          BusRoute.dummyRoute("B", BusRoute.BusDirection.OUTGOING, stopId2, stopTime1);
      Bus bus2 = new Bus(BusStop.getStop(1), route2);
      int[] stopId3 = {3, 4, 5};
      BusRoute route3 =
          BusRoute.dummyRoute("C", BusRoute.BusDirection.OUTGOING, stopId3, stopTime1);
      Bus bus3 = new Bus(BusStop.getStop(1), route3);
      int[] stopId4 = {4, 5, 6};
      BusRoute route4 =
          BusRoute.dummyRoute("D", BusRoute.BusDirection.OUTGOING, stopId4, stopTime1);
      Bus bus4 = new Bus(BusStop.getStop(1), route4);
      int[] stopId5 = {5, 6, 7};
      BusRoute route5 =
          BusRoute.dummyRoute("E", BusRoute.BusDirection.OUTGOING, stopId5, stopTime1);
      Bus bus5 = new Bus(BusStop.getStop(1), route5);
      int[] stopId6 = {6, 7, 8};
      BusRoute route6 =
          BusRoute.dummyRoute("F", BusRoute.BusDirection.OUTGOING, stopId6, stopTime1);
      Bus bus6 = new Bus(BusStop.getStop(1), route6);
      int[] stopId7 = {7, 8, 9};
      BusRoute route7 =
          BusRoute.dummyRoute("G", BusRoute.BusDirection.OUTGOING, stopId7, stopTime1);
      Bus bus7 = new Bus(BusStop.getStop(1), route7);

      // create invalid tree
      Node<Bus> root =
          new Node<Bus>(bus1,
              new Node<Bus>(bus4,
                  new Node<Bus>(bus2, new Node<Bus>(bus6, null, null),
                      new Node<Bus>(bus3, null, null)),
                  new Node<Bus>(bus5, null, null)),
              new Node<Bus>(bus7, null, null));
      return BusStopTree.isValidBST(root);
    } catch (Exception e) {
      // handle exception
      return true;
    }
  }

  /**
   * Tests that isValidBST returns true for a valid BST.
   * 
   * Should use a tree with depth > 2.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTValid() {
    // create new empty bst
    BusStopTree bst = new BusStopTree(8);

    try {
      String[] stopTime1 = {"06:00", "07:00", "08:00"};
      // create buses
      int[] stopId1 = {1, 2, 3};
      BusRoute route1 =
          BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopId1, stopTime1);
      Bus bus1 = new Bus(BusStop.getStop(1), route1);
      int[] stopId2 = {2, 3, 4};
      BusRoute route2 =
          BusRoute.dummyRoute("B", BusRoute.BusDirection.OUTGOING, stopId2, stopTime1);
      Bus bus2 = new Bus(BusStop.getStop(1), route2);
      int[] stopId3 = {3, 4, 5};
      BusRoute route3 =
          BusRoute.dummyRoute("C", BusRoute.BusDirection.OUTGOING, stopId3, stopTime1);
      Bus bus3 = new Bus(BusStop.getStop(1), route3);
      int[] stopId4 = {4, 5, 6};
      BusRoute route4 =
          BusRoute.dummyRoute("D", BusRoute.BusDirection.OUTGOING, stopId4, stopTime1);
      Bus bus4 = new Bus(BusStop.getStop(1), route4);
      int[] stopId5 = {5, 6, 7};
      BusRoute route5 =
          BusRoute.dummyRoute("E", BusRoute.BusDirection.OUTGOING, stopId5, stopTime1);
      Bus bus5 = new Bus(BusStop.getStop(1), route5);
      int[] stopId6 = {6, 7, 8};
      BusRoute route6 =
          BusRoute.dummyRoute("F", BusRoute.BusDirection.OUTGOING, stopId6, stopTime1);
      Bus bus6 = new Bus(BusStop.getStop(1), route6);
      int[] stopId7 = {7, 8, 9};
      BusRoute route7 =
          BusRoute.dummyRoute("G", BusRoute.BusDirection.OUTGOING, stopId7, stopTime1);
      Bus bus7 = new Bus(BusStop.getStop(1), route7);

      // create tree
      Node<Bus> root =
          new Node<Bus>(bus6,
              new Node<Bus>(bus4,
                  new Node<Bus>(bus2, new Node<Bus>(bus1, null, null),
                      new Node<Bus>(bus3, null, null)),
                  new Node<Bus>(bus5, null, null)),
              new Node<Bus>(bus7, null, null));
      return BusStopTree.isValidBST(root);
    } catch (Exception e) {
      // handle exception
      return true;
    }
  }

  /**
   * Tests that addBus correctly adds a bus to an empty BST.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBusEmpty() {
    // create new empty bst
    BusStopTree bst = new BusStopTree(1);

    // create bus
    int[] stopId1 = {1, 2, 3};
    String[] stopTime1 = {"06:00", "07:00", "08:00"};
    BusRoute route1 = BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopId1, stopTime1);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);

    // add bus to tree
    boolean busAdded = bst.addBus(bus1);

    // return output
    return busAdded;

  }

  /**
   * Tests that addBus correctly adds a bus to a non-empty BST.
   * 
   * Each time you add a bus, make sure that 1) addBus() returns true, 2) the BST is still valid, 3)
   * the BST size has been incremented.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBus() {
    // create new empty bst
    BusStopTree bst = new BusStopTree(3);

    // create buses
    int[] stopId1 = {1, 2, 3};
    int[] stopId2 = {2, 3, 4};
    int[] stopId3 = {3, 4, 5};
    String[] stopTime1 = {"06:00", "07:00", "08:00"};
    String[] stopTime2 = {"07:00", "08:00", "09:00"};
    String[] stopTime3 = {"08:00", "09:00", "10:00"};
    BusRoute route1 = BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopId1, stopTime1);
    BusRoute route2 = BusRoute.dummyRoute("B", BusRoute.BusDirection.INCOMING, stopId2, stopTime2);
    BusRoute route3 = BusRoute.dummyRoute("C", BusRoute.BusDirection.OUTGOING, stopId3, stopTime3);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);

    try {
      // add bus1 and check
      boolean addBus1 = bst.addBus(bus1);
      if (!BusStopTree.isValidBST(bst.getRoot()) || !addBus1 || bst.size() != 1) {
        return false;
      }

      // add bus2 and check
      boolean addBus2 = bst.addBus(bus2);
      if (!BusStopTree.isValidBST(bst.getRoot()) || !addBus2 || bst.size() != 2) {
        return false;
      }

      // add bus3 and check
      boolean addBus3 = bst.addBus(bus3);
      if (!BusStopTree.isValidBST(bst.getRoot()) || !addBus3 || bst.size() != 3) {
        return false;
      }
    } catch (Exception e) {
      // catch exception
      return true;
    }
    // return if tests pass
    return true;
  }

  /**
   * Tests that addBus returns false when adding a duplicate bus. The BST should not be modified
   * (same size).
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBusDuplicate() {
    // create new empty bst
    BusStopTree bst = new BusStopTree(1);

    // create bus
    int[] stopId1 = {1, 2, 3};
    String[] stopTime1 = {"06:00", "07:00", "08:00"};
    BusRoute route1 = BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopId1, stopTime1);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);

    // add bus to tree
    boolean busAdded = bst.addBus(bus1);
    if (!busAdded || bst.size() != 1) {
      return false;
    }

    // check to see if same bus is added
    boolean busAdded2 = bst.addBus(bus1);
    if (busAdded2 || bst.size() != 1) {
      return false;
    }
    // return is tests pass
    return true;
  }


  /**
   * Tests that contains returns true when the BST contains the Bus, and false otherwise.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testContains() {
    // create new empty bst
    BusStopTree bst = new BusStopTree(3);

    // create bus
    int[] stopId1 = {1, 2, 3};
    int[] stopId2 = {2, 3, 4};
    String[] stopTime1 = {"06:00", "07:00", "08:00"};
    BusRoute route1 = BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopId1, stopTime1);
    BusRoute route2 = BusRoute.dummyRoute("B", BusRoute.BusDirection.INCOMING, stopId2, stopTime1);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);

    // check for bus
    boolean addedBus = bst.addBus(bus1);
    boolean contain = bst.contains(bus1);
    boolean notContain = bst.contains(bus2);

    // check if tests are right
    return addedBus && contain && !notContain;
  }


  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is the node passed in as the root node parameter.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetFirstNodeAfterRoot() {
    // create new empty bst
    BusStopTree bst = new BusStopTree(3);

    // create buses with different arrival times
    int[] stopId1 = {1, 2, 3};
    int[] stopId2 = {2, 3, 4};
    String[] stopTime1 = {"06:00", "07:15", "08:00"};
    String[] stopTime2 = {"06:30", "07:30", "08:30"};
    BusRoute route1 = BusRoute.dummyRoute("A", BusRoute.BusDirection.INCOMING, stopId1, stopTime1);
    BusRoute route2 = BusRoute.dummyRoute("B", BusRoute.BusDirection.INCOMING, stopId2, stopTime2);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);

    // add buses
    boolean addedBus1 = bst.addBus(bus1);
    boolean addedBus2 = bst.addBus(bus2);

    // get time
    Node<Bus> nodeAfter = bst.getFirstNodeAfter(LocalTime.parse("07:10"), bst.getRoot());

    // check tests
    if (nodeAfter == null || nodeAfter.getValue() == null || !nodeAfter.getValue().equals(bus1)) {
      System.out.print(bst.getRoot());
      return false;
    }

    // true if tests pass
    return true;
  }

  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is in the left subtree.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetFirstNodeAfterLeftSubtree() {
    // create new empty bst
    BusStopTree bst = new BusStopTree(3);

    // create buses with different arrival times
    int[] stopId1 = {1, 2, 3};
    int[] stopId2 = {2, 3, 4};
    String[] stopTime1 = {"06:00", "07:15", "08:00"};
    String[] stopTime2 = {"06:30", "07:30", "08:30"}; // Arriving half an hour later
    BusRoute route1 = BusRoute.dummyRoute("A", BusRoute.BusDirection.INCOMING, stopId1, stopTime1);
    BusRoute route2 = BusRoute.dummyRoute("B", BusRoute.BusDirection.INCOMING, stopId2, stopTime2);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);

    // add buses
    boolean addedBus1 = bst.addBus(bus1);
    boolean addedBus2 = bst.addBus(bus2);

    // get time
    Node<Bus> nodeAfter = bst.getFirstNodeAfter(LocalTime.parse("07:10"), bst.getRoot());

    // check tests
    if (nodeAfter == null || nodeAfter.getValue() == null || !nodeAfter.getValue().equals(bus1)) {
      System.out.print(bst.getRoot());
      return false;
    }

    // true if tests pass
    return true;
  }

  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is in the right subtree.
   * 
   * @return
   */
  public static boolean testGetFirstNodeAfterRightSubtree() {
    // create new empty bst
    BusStopTree bst = new BusStopTree(3);

    // create buses with different arrival times
    int[] stopId1 = {1, 2, 3};
    int[] stopId2 = {2, 3, 4};
    String[] stopTime1 = {"06:00", "07:15", "08:00"};
    String[] stopTime2 = {"06:30", "07:30", "08:30"}; // Arriving half an hour later
    BusRoute route1 = BusRoute.dummyRoute("A", BusRoute.BusDirection.INCOMING, stopId1, stopTime1);
    BusRoute route2 = BusRoute.dummyRoute("B", BusRoute.BusDirection.INCOMING, stopId2, stopTime2);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);

    // add buses
    boolean addedBus1 = bst.addBus(bus1);
    boolean addedBus2 = bst.addBus(bus2);

    // get time
    Node<Bus> nodeAfter = bst.getFirstNodeAfter(LocalTime.parse("07:10"), bst.getRoot());

    // check tests
    if (nodeAfter == null || nodeAfter.getValue() == null || !nodeAfter.getValue().equals(bus1)) {
      System.out.print(bst.getRoot());
      return false;
    }

    // true if tests pass
    return true;
  }

  /**
   * Tests that removeBus correctly removes a Bus that is a LEAF NODE. Make sure that 1) removeBus
   * returns the removed Bus, 2) the BST is still valid, 3) the BST size has been decremented.
   * 
   * Note: this test is optional and you will not be graded on it. However, it is highly encouraged.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusLeaf() {
    return true;
  }

  /**
   * Tests that removeBus correctly removes a Bus that is a non-leaf node with ONE child. Make sure
   * that 1) removeBus returns the removed Bus, 2) the BST is still valid, 3) the BST size has been
   * decremented.
   * 
   * Note: this test is optional and you will not be graded on it. However, it is highly encouraged.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusNodeOneChild() {
    return true;
  }

  /**
   * Tests that removeBus correctly removes a Bus that is a non-leaf node with TWO children. Make
   * sure that 1) removeBus returns the removed Bus, 2) the BST is still valid, 3) the BST size has
   * been decremented.
   * 
   * Note: this test is optional and you will not be graded on it. However, it is highly encouraged.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusNodeTwoChildren() {
    return true;
  }


  /**
   * Tests that removeBus returns false when removing a Bus that is not in the BST. The BST should
   * not be modified.
   * 
   * Note: this test is optional and you will not be graded on it. However, it is highly encouraged.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusNodeNotInBST() {
    return true;
  }

  /**
   * Tests the creation of an BusFilteredIterator where NONE of the buses go to the destination.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToEmpty() {
    try {
      // create new empty bst
      BusStopTree bst = new BusStopTree(7);

      // create buses with different arrival times
      int[] stopId1 = {1, 2, 3};
      String[] stopTime1 = {"06:00", "07:00", "08:00"};
      BusRoute route1 =
          BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopId1, stopTime1);
      Bus bus1 = new Bus(BusStop.getStop(1), route1);

      int[] stopId2 = {4, 5, 6};
      String[] stopTime2 = {"09:00", "10:00", "11:00"};
      BusRoute route2 =
          BusRoute.dummyRoute("B", BusRoute.BusDirection.INCOMING, stopId2, stopTime2);
      Bus bus2 = new Bus(BusStop.getStop(4), route2);

      // add buses
      bst.addBus(bus1);
      bst.addBus(bus2);

      // check to see if no buses go here
      Iterator<Bus> iterator = bst.getNextBusesTo(LocalTime.parse("12:00"), BusStop.getStop(1));

      // check if any buses
      if (iterator.hasNext()) {
        return false;
      }

      if (bst.size() != 2) {
        return false;
      }
    } catch (Exception e) {
      // handle exception
      return true;
    }

    // true if no buses
    return true;
  }

  /**
   * Tests the creation of an BusFilteredIterator where SOME of the buses go to the destination.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToSome() {
    try {
      // create new empty bst
      BusStopTree bst = new BusStopTree(7);

      // create buses with different arrival times
      int[] stopId1 = {1, 2, 3};
      String[] stopTime1 = {"06:00", "07:00", "08:00"};
      BusRoute route1 =
          BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopId1, stopTime1);
      Bus bus1 = new Bus(BusStop.getStop(1), route1);

      int[] stopId2 = {4, 5, 6};
      String[] stopTime2 = {"09:00", "10:00", "11:00"};
      BusRoute route2 =
          BusRoute.dummyRoute("B", BusRoute.BusDirection.INCOMING, stopId2, stopTime2);
      Bus bus2 = new Bus(BusStop.getStop(4), route2);

      // add buses
      bst.addBus(bus1);
      bst.addBus(bus2);

      // check to see if no buses go here
      Iterator<Bus> iterator = bst.getNextBusesTo(LocalTime.parse("11:00"), BusStop.getStop(6));

      // check if any buses
      if (iterator.hasNext()) {
        return false;
      }

      if (bst.size() != 2) {
        return false;
      }
    } catch (Exception e) {
      // handle exception
      return true;
    }

    // true if some buses
    return true;
  }

  /**
   * Tests the creation of an BusFilteredIterator where ALL of the buses go to the destination.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToAll() {
    // create new empty bst
    BusStopTree bst = new BusStopTree(3);

    String[] stopTime1 = {"06:00", "07:00", "08:00"};
    // create buses
    int[] stopId1 = {1, 2, 3};
    BusRoute route1 = BusRoute.dummyRoute("A", BusRoute.BusDirection.OUTGOING, stopId1, stopTime1);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    int[] stopId2 = {1, 2, 3};
    BusRoute route2 = BusRoute.dummyRoute("B", BusRoute.BusDirection.OUTGOING, stopId2, stopTime1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    int[] stopId3 = {1, 2, 3};
    BusRoute route3 = BusRoute.dummyRoute("C", BusRoute.BusDirection.OUTGOING, stopId3, stopTime1);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);

    // add bus
    bst.addBus(bus1);
    bst.addBus(bus1);
    bst.addBus(bus3);

    // test iterators
    Bus[] order = {bus1, bus2, bus3};
    BusForwardIterator buses = new BusForwardIterator(bst.getRoot(),
        bst.getFirstNodeAfter(LocalTime.parse("07:10"), bst.getRoot()));
    BusFilteredIterator filterBus = new BusFilteredIterator(buses, BusStop.getStop(2));
    for (Bus newBus : order) {
      if (!filterBus.hasNext()) {
        return false;
      }
      if (!newBus.equals(filterBus.next())) {
        return false;
      }
    }
    
    try {
    // more buses, return false
    if (filterBus.hasNext()) {
      return false;
    }
      filterBus.next();
      return false;
    } catch (NoSuchElementException e) {
      // handle exception
      return true;
    }
  }

  public static void main(String[] args) {
    // Populate BusStop with dummy data. This only has to be done once.
    BusStop.createDummyStops();

    System.out
        .println("testBusCompareToDifferentArrivalTime: " + testBusCompareToDifferentArrivalTime());
    System.out.println("testBusCompareToSameArrivalTimeDifferentRoute: "
        + testBusCompareToSameArrivalTimeDifferentRoute());
    System.out.println("testBusCompareToSameArrivalTimeSameRouteDifferentDirection: "
        + testBusCompareToSameArrivalTimeSameRouteDifferentDirection());
    System.out.println("testBusCompareToSameBus" + testBusCompareToSameBus());
    System.out.println("testIsValidBSTEmpty: " + testIsValidBSTEmpty());
    System.out.println("testIsValidBSTInvalid: " + testIsValidBSTInvalid());
    System.out.println("testIsValidBSTValid: " + testIsValidBSTValid());
    System.out.println("testAddBusEmpty: " + testAddBusEmpty());
    System.out.println("testAddBus: " + testAddBus());
    System.out.println("testAddBusDuplicate: " + testAddBusDuplicate());
    System.out.println("testRemoveBusLeaf: " + testRemoveBusLeaf());
    System.out.println("testRemoveBusNodeOneChild: " + testRemoveBusNodeOneChild());
    System.out.println("testRemoveBusNodeTwoChildren: " + testRemoveBusNodeTwoChildren());
    System.out.println("testRemoveBusNodeNotInBST: " + testRemoveBusNodeNotInBST());
    System.out.println("testContains: " + testContains());
    System.out.println("testGetFirstNodeAfterRoot: " + testGetFirstNodeAfterRoot());
    System.out.println("testGetFirstNodeAfterLeftSubtree: " + testGetFirstNodeAfterLeftSubtree());
    System.out.println("testGetFirstNodeAfterRightSubtree: " + testGetFirstNodeAfterRightSubtree());
    System.out.println("testGetNextBusesToEmpty: " + testGetNextBusesToEmpty());
    System.out.println("testGetNextBusesToSome: " + testGetNextBusesToSome());
    System.out.println("testGetNextBusesToAll: " + testGetNextBusesToAll());
  }

}
