package com.compostage.Data;

import java.io.Serializable;

public class Bed implements Serializable {
    public Bed(){
        this.setName("");
    }

    public Bed(int id, String name){
        this.setId(id);
        this.setName(name);
    }

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
