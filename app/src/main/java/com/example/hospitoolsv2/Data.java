package com.example.hospitoolsv2;

public class Data {

    private int dataID;
    private String hospital;
    private String procedure;
    private String firstName;
    private String lastName;
    private String waitTime;

    public Data(int newDataID, String newFname, String newLname, String newHospital, String newProcedure, String newwaitTime) {
        setFname(newFname);
        setLname(newLname);
        setDataID(newDataID);
        setHospital(newHospital);
        setProcedure(newProcedure);
        setWaitTime(newwaitTime);
    }

    public void setWaitTime(String newwaitTime) {
        waitTime = newwaitTime;
    }

    public void setFname(String newFname) {
        firstName = newFname;
    }

    public void setLname(String newLname) {
        lastName = newLname;
    }


    public void setHospital(String newHospital) {
        hospital = newHospital;
    }

    public void setProcedure(String newProcedure) {
        procedure = newProcedure;
    }

    public void setDataID(int newDataID) {
        dataID = newDataID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHospital() {
        return hospital;
    }

    public String getWaitTime() {
        return waitTime;
    }

    public String getProcedure() {
        return procedure;
    }

    public String getDataID() {
        return getDataID();
    }

    public String toString() {
        return "ID: " + dataID + "\nName:" + firstName + " " + lastName + "\nHospital: " + hospital + "\nProcedure: " + procedure + "\nWait Time: " + waitTime;
    }
}
