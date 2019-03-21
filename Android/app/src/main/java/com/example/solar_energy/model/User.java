package com.example.solar_energy.model;

public class User {

    String name;
    String id;
    String password;
    String generatorNum;





    public User(String name, String id, String password, String generatorNum) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.generatorNum = generatorNum;

    }



    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
    public String getGeneratorNum() {
        return generatorNum;
    }


}
