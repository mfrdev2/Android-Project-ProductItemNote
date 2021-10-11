package com.frabbi.mysqlitedatabasedemo;

public class User {
    private int _id;
    private String user_name;
    private String password;
    private String first_name;
    private String last_name;
    private String gender;
    private String email;
    private String phone_no;
    private String address;
    private String city;
    private String country;

    public User(int _id, String user_name, String password,
                String first_name, String last_name, String gender,
                String email, String phone_no, String address,
                String city, String country) {
        this._id = _id;
        this.user_name = user_name;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.email = email;
        this.phone_no = phone_no;
        this.address = address;
        this.city = city;
        this.country = country;
    }
    public User(String user_name, String password,
                String first_name, String last_name, String gender,
                String email, String phone_no, String address,
                String city, String country) {

        this.user_name = user_name;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.email = email;
        this.phone_no = phone_no;
        this.address = address;
        this.city = city;
        this.country = country;
    }

    public User(String username, String password) {
        this.user_name = username;
        this.password = password;
    }

    public int get_id() {
        return _id;
    }


    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
