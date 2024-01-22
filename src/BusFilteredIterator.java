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
// Partner Lecturer's Name: (None
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: None
// Online Sources: I used the javadocs to help create this class
/////// (https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2023/fall/p09/BusFilteredIterator.html)
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An iterator that only returns buses from another iterator that go to a particular destination.
 */
public class BusFilteredIterator implements Iterator<Bus> {
  // data fields
  // The iterator we are filtering.
  private Iterator<Bus> baseIterator;
  // The destination BusStop we are filtering by.
  private BusStop destination;
  // The next Bus to be returned, or null if there aren't any more.
  private Bus next;

  /**
   * Construct a new BusFilteredIterator that filters the given iterator to return only Bus-es that
   * stop at the given destination.
   * 
   * @param iterator    - the iterator we are filtering.
   * @param destination - the desired destination.
   */
  public BusFilteredIterator(Iterator<Bus> iterator, BusStop destination) {
    this.baseIterator = baseIterator;
    this.destination = destination;
    // next bus to be returned
    advanceToNext();
  }

  /**
   * Private helper method that advances this iterator. It will iterate over `this.iterator` until
   * it reaches a Bus that stops at destination. Then, it will store that Bus in `next`.
   */
  private void advanceToNext() {
    next = null;
    while (baseIterator.hasNext()) {
      Bus bus = baseIterator.next();
      if (bus.goesTo(destination)) {
        next = bus;
        break;
      }
    }
  }

  /**
   * Returns true if there is another Bus (that goes to the destination) in this iterator, or false
   * otherwise. This method should not change any of the fields of the iterator.
   * 
   * @return true if a call to next() will return another Bus; false otherwise.
   */
  public boolean hasNext() {
    if (next != null) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Returns the `next` bus and advances the iterator until the next bus it will return.
   * 
   * @return Buses from the iterator baseIterator that go to the destination stop.
   * @throws NoSuchElementException - if called when there is no next Bus. Note that you get this
   *                                for free from the baseIterator. You do not need to import
   *                                anything or throw anything yourself.
   */
  public Bus next() {
    Bus current = next;
    // advance iterator
    advanceToNext();
    return current;
  }
}
