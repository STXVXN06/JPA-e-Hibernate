package com.steven.curso.springboot.jpa.springboot_jpa.dto;

public class PersonDto {

    String name;
    String lastnmae;
    
    
    public PersonDto(String name, String lastnmae) {
        this.name = name;
        this.lastnmae = lastnmae;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getLastnmae() {
        return lastnmae;
    }
    
    public void setLastnmae(String lastnmae) {
        this.lastnmae = lastnmae;
    }

    @Override
    public String toString() {
        return "PersonDto [name = " + name + ", lastnmae = " + lastnmae + "]";
    }

    
    
}
