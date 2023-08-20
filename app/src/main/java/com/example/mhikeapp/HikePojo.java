package com.example.mhikeapp;

public class HikePojo{

    String id;

    String name;
    String location;

    String date,parking,length,level,description;


    public HikePojo() {

    }

    public HikePojo(String id, String name, String location, String date, String parking, String length, String level, String description) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.date = date;
        this.parking = parking;
        this.length = length;
        this.level = level;
        this.description = description;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getParking() {
        System.out.println(parking);
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //    public HikePojo( String id, String name,String location, String date,) {
//        this.location = location;
//        this.name = name;
//        this.date = date;
//    }
//
//    public String getlocation() {
//        return location;
//    }
//
//    public void setId(String location) {
//        this.location = location;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
}