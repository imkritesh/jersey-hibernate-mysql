package com.leavemanager.domain;


import org.hibernate.annotations.CascadeType;


import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String fistName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String address;
    @NotNull
    @Size(message = "Mobilenumber should be of 10 digits",min = 10 , max = 10)
    private String mobilenumber;
    @NotNull
    private String dob;//date of birth
    @NotNull
    private String gender;
    @NotNull
    private String position;
    @NotNull
    @OneToMany(mappedBy = "employee",orphanRemoval = true, cascade = {javax.persistence.CascadeType.REMOVE})
    List<Vacations> vacations = new ArrayList<Vacations>();
    public Employee(){
        super();
    }
    public Employee(String firstname, String lastname, String email, String address,
                    String mobilenumber, String dob, String gender, String position){
        this.fistName = firstname;
        this.lastName = lastname;
        this.email = email;
        this.address = address;
        this.mobilenumber = mobilenumber;
        this.dob = dob;
        this.gender = gender;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<Vacations> getVacations() {
        return vacations;
    }

    public void setVacations(List<Vacations> vacations) {
        this.vacations = vacations;
    }
}
