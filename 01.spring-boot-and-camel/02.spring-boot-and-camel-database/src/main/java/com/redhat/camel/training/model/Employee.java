package com.redhat.camel.training.model;

import java.util.Date;

/**
 * <pre>
 *  com.redhat.camel.training.model.Employee
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 30 Sep 2024 9:22
 */
public class Employee {
    private String gender;
    private Date birthdate;
    private Long id;
    private String firstname;
    private String lastname;

    public Employee() {
    }

    public Employee(String gender, Date birthdate, Long id, String firstname, String lastname) {
        this.gender = gender;
        this.birthdate = birthdate;
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
