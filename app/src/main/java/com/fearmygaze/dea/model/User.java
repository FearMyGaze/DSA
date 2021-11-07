package com.fearmygaze.dea.model;

public class User {
    private final String name;
    private final String lastname;
    private final String email;
    private String location;
    private String SSN;
    private String device_id;
    private String uuid;

    public User(String name, String lastname, String email) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getLocation() {
        return location;
    }

    public String getSSN() {
        return SSN;
    }

    public String getDevice_id() {
        return device_id;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", location='" + location + '\'' +
                ", SSN='" + SSN + '\'' +
                ", device_id='" + device_id + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
