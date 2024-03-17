package com.jeanlima.springmvcapp.model;
import java.util.UUID;

public class Curso{
    private String name;
    public Curso(){};
    public Curso(String name){
        this.name = name;
    };
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
