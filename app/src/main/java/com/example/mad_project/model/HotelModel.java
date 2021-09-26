package com.example.mad_project.model;


import java.io.Serializable;

public class HotelModel implements Serializable {

    String id;
    String name;
    String address;
    String owner;
    String contact;
    String facilities;
    String hrn;
    String photo;

    public HotelModel() {
    }

    public HotelModel(String id, String name, String address, String owner, String contact, String facilities, String hrn) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.owner = owner;
        this.contact = contact;
        this.facilities = facilities;
        this.hrn = hrn;
    }

    public void update(String name, String address, String owner, String contact, String facilities, String hrn) {

        this.name = name;
        this.address = address;
        this.owner = owner;
        this.contact = contact;
        this.facilities = facilities;
        this.hrn = hrn;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getHrn() {
        return hrn;
    }

    public void setHrn(String hrn) {
        this.hrn = hrn;
    }
}
