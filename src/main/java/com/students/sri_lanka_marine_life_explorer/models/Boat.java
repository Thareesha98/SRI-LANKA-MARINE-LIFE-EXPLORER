/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.students.sri_lanka_marine_life_explorer.models;

/**
 *
 * @author thareesha
 */
public class Boat {
    private Long id;
    private String name;
    private Fisherman owner;


    private String type;
    private String engine_type;
    private int size;

    public String getEngine_type() {
        return engine_type;
    }

    public void setEngine_type(String engine_type) {
        this.engine_type = engine_type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    private String registrationNumber;
    
 // Link to owner

    // Getters and setters

    public Fisherman getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Boat{" + "name=" + name + '}';
    }

    

    public void setOwner(Fisherman owner) {
        this.owner = owner;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

   
}

