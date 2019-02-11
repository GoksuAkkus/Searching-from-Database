package com.example.cuyanik.sqlitedatabaseapp;

/**
 * Created by CUyanik on 9.12.2016.
 */

public class Employee {

    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private Double salary;
    private String gender;

    public Employee() {
    }

    public Employee(String name, String surname, Integer age, Double salary, String gender) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.salary = salary;
        this.gender = gender;
    }

    public Employee(Long id, String name, String surname, Integer age, Double salary, String gender) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.salary = salary;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
