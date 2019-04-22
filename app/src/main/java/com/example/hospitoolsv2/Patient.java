package com.example.hospitoolsv2;

public class Patient {

    private int id;
    private String firstName;
    private String lastName;
    private String number;
    private String gender;
    private String dateOfBirth;
    private String email;

    public Patient(int newID, String newFname, String newLname, String newNum, String newGender, String newDoB, String newEmail){
        setID(newID);
        setFname(newFname);
        setLname(newLname);
        setNum(newNum);
        setGender(newGender);
        setDoB(newDoB);
        setEmail(newEmail);
    }

    public void setID(int newID){
        id=newID;
    }

    public void setFname(String newFname){
        firstName = newFname;
    }

    public void setLname(String newLname ){
        lastName = newLname;
    }

    public void setNum(String newNum){
        number = newNum;
    }

    public void setGender(String newGender){
        gender = newGender;
    }

    public void setDoB(String newDoB){
        dateOfBirth = newDoB;
    }

    public void setEmail(String newEmail){
        email = newEmail;

    }

    public int getId(){
        return id;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getNumber(){
        return number;
    }

    public String getGender(){
        return gender;
    }

    public String getDateOfBirth(){
        return dateOfBirth;
    }

    public String getEmail(){
        return email;
    }

    public String toString(){
        return "\nName:" + firstName + " "+ lastName + "\nNumber: " + number + "\nGender: " + gender + "\nDoB: " + dateOfBirth + "\nEmail: " + email;
    }
}


