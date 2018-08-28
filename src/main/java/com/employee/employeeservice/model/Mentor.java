package com.employee.employeeservice.model;

import java.util.Map;

public class Mentor extends Employee {
    private Map<String, Employee> menteesList;
    public Mentor(String firstName,String lastName,String employeeID,String office,String title,String email,String imageURL,Map<String,Employee> mentees){
        super(firstName,lastName,employeeID,office,title,email,imageURL);
        menteesList = mentees;
    }
    public Map<String, Employee> getMenteesList() {
        return menteesList;
    }



    public void setMenteesList(Map<String, Employee> menteesList) {
        this.menteesList = menteesList;
    }

    public void addMentee(Employee mentee){
        menteesList.put(mentee.getEmployeeID(),mentee);
    }

    public void removeMentee(Employee mentee){
        if(menteesList.get(mentee.getEmployeeID()) != null){
            menteesList.remove(mentee.getEmployeeID());
        }
    }


}
