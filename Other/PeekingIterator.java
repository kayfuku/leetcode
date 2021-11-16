package leetcode;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

// Author: leetcode + kei
// Date : November 10, 2021

class PeekingIterator implements Iterator<Integer> {

  public PeekingIterator() {
    // initialize any member here.

  }

  // 2.
  private Iterator<Integer> iter;
  private Integer next = null;

  // 3.
  public PeekingIterator(Iterator<Integer> iterator) {
    // Avoid an exception being thrown in the constructor.
    if (iterator.hasNext()) {
      next = iterator.next();
    }
    iter = iterator;
  }

  // 1.
  // Peek next element.
  public Integer peek() {
    if (next == null) {
      throw new NoSuchElementException();
    }
    return next;
  }

  // 4.
  @Override
  public Integer next() {
    /*
     * As per the Java Iterator specs, we should throw a NoSuchElementException if
     * the next element doesn't exist.
     */
    if (next == null) {
      throw new NoSuchElementException();
    }
    Integer toReturn = next;
    next = null;
    if (iter.hasNext()) {
      next = iter.next();
    }
    return toReturn;
  }

  // 5.
  @Override
  public boolean hasNext() {
    // We cannot use iter.hasNext() here!
    // because iter.next() is one element ahead and when we call this (hasNext()) at
    // the second to last element, it returns false.
    return next != null;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    PeekingIterator solution = new PeekingIterator();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
