package com.challenges.exercism.hard.customset;

import java.util.Collection;
import java.util.HashMap;

public class CustomSet<T> {

  private final HashMap<T, T> elements = new HashMap<>();

  CustomSet() {}

  CustomSet(Collection<T> data) {
    for (T element : data) {
      this.elements.put(element, element);
    }
  }

  boolean isEmpty() {
    return this.elements.isEmpty();
  }

  boolean contains(T element) {
    return this.elements.containsKey(element);
  }

  boolean isDisjoint(CustomSet<T> other) {
    return this.getIntersection(other).isEmpty();
  }

  boolean add(T element) {
    if (this.contains(element)) {
      return false;
    }
    this.elements.put(element, element);
    return true;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    CustomSet<?> other = (CustomSet<?>) obj;
    return this.elements.keySet().equals(other.elements.keySet());
  }

  CustomSet<T> getIntersection(CustomSet<T> other) {
    final var intersection = new CustomSet<T>();
    for (T element : this.elements.keySet()) {
      if (other.contains(element)) {
        intersection.add(element);
      }
    }
    return intersection;
  }

  CustomSet<T> getUnion(CustomSet<T> other) {
    final var union = new CustomSet<T>();
    for (T element : this.elements.keySet()) {
      union.add(element);
    }
    for (T element : other.elements.keySet()) {
      union.add(element);
    }
    return union;
  }

  CustomSet<T> getDifference(CustomSet<T> other) {
    final var difference = new CustomSet<T>();
    for (T element : this.elements.keySet()) {
      if (!other.contains(element)) {
        difference.add(element);
      }
    }
    return difference;
  }

  boolean isSubset(CustomSet<T> other) {
    for (T element : other.elements.keySet()) {
      if (!this.contains(element)) {
        return false;
      }
    }
    return true;
  }
}
