package com.example.Em_project;

import lombok.Data;

@Data
public class Employee {
    private Long id;  // use Long instead of int for id to handle larger numbers
    private String name;
    private String email;
    private String phone;

    // public String getName(){
    //     return name;
    // }

    // public void setName(String name){
    //     this.name = name;
    // }
}


