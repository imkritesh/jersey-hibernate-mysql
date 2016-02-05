package com.leavemanager.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Vacations {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String startDate;
    @NotNull
    private String endDate;
    @NotNull
    private String reason;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Employee employee;
    public Vacations(){
        super();
    }
    public Vacations(String from, String to, String reason, Employee employee){
        this.startDate = from;
        this.endDate = to;
        this.reason = reason;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
   /* @Override
    public String toString(){
        return "from:"+startDate+",to:"+endDate+",reason:"+reason;
    }
    */
}
