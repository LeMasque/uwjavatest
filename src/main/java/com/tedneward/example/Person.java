package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {
  private int age;
  private String name;
  private double salary;
  private String ssn = "";
  private boolean propertyChangeFired = false;
  private static int numberOfPeople = 0;

  public Person() {
    this("", 0, 0.0d);
  }

  public Person(String n, int a, double s) {
    this.setName(n);
    this.setAge(a);
    this.setSalary(s);
    numberOfPeople++;
  }

  public void setSSN(String value) {
    String old = this.ssn;
    this.ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    this.propertyChangeFired = true;
  }

  public boolean getPropertyChangeFired() {
    return this.propertyChangeFired;
  }

  public double calculateBonus() {
    return this.salary * 1.10;
  }

  public String becomeJudge() {
    return "The Honorable " + this.name;
  }

  public int timeWarp() {
    return this.age + 10;
  }

  @Override
  public String toString() {
    return "[Person name:" + this.name + " age:" + this.age + " salary:" + this.salary + "]";
  }

  public double getSalary() {
    return this.salary;
  }

  public void setSalary(double s) {
    this.salary = s;
  }

  public void setAge(int a) {
    if (a < 0) throw new IllegalArgumentException("[!] Age must be a non-negative integer");
    this.age = a;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    if (this.getClass() != o.getClass()) return false;
    Person other = (Person) o; // cast to Person class
    return ((this.getAge() == other.getAge()) && (this.getName().equals(other.getName())));
  }

  public int getAge() {
    return this.age;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String n) {
    if (n == null) throw new IllegalArgumentException("[!] Name must be a non-null string");
    this.name = n;
  }

  public int count() {
    return numberOfPeople;
  }

  // should use generic here, but he specifically said "... that returns an ArrayList ..."
  public static ArrayList<Person> getNewardFamily() {
    // use ArrayList instead of generic to force it to return an ArrayList
    ArrayList<Person> fam = new ArrayList<Person>();
    fam.add(new Person("Ted", 41, 250000.0));
    fam.add(new Person("Charlotte", 43, 150000.0));
    fam.add(new Person("Michael", 22, 10000.0));
    fam.add(new Person("Matthew", 15, 0.0));
    return fam;
  }

  // TODO: is this what is supposed to happen???
  public static class AgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
      return p1.getAge() - p2.getAge();
    }
  }

  @Override
  public int compareTo(Person other) {
    return -(int)(this.getSalary() - other.getSalary());
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}
