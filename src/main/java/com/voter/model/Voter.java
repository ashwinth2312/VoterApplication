package com.voter.model;

public class Voter {
    private int    voterId;
    private String name;
    private String email;
    private String password;
    private String dob;
    private String aadharNo;
    private String gender;
    private String address;
    private boolean voted;

    public Voter() {}

    public Voter(int voterId, String name, String email, String password,
                 String dob, String aadharNo, String gender, String address, boolean voted) {
        this.voterId  = voterId;
        this.name     = name;
        this.email    = email;
        this.password = password;
        this.dob      = dob;
        this.aadharNo = aadharNo;
        this.gender   = gender;
        this.address  = address;
        this.voted    = voted;
    }

    public int     getVoterId()  { return voterId; }
    public String  getName()     { return name; }
    public String  getEmail()    { return email; }
    public String  getPassword() { return password; }
    public String  getDob()      { return dob; }
    public String  getAadharNo() { return aadharNo; }
    public String  getGender()   { return gender; }
    public String  getAddress()  { return address; }
    public boolean isVoted()     { return voted; }

    public void setVoterId(int v)     { this.voterId  = v; }
    public void setName(String v)     { this.name     = v; }
    public void setEmail(String v)    { this.email    = v; }
    public void setPassword(String v) { this.password = v; }
    public void setDob(String v)      { this.dob      = v; }
    public void setAadharNo(String v) { this.aadharNo = v; }
    public void setGender(String v)   { this.gender   = v; }
    public void setAddress(String v)  { this.address  = v; }
    public void setVoted(boolean v)   { this.voted    = v; }
}
