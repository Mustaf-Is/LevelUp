package com.example.levelup.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jdk.jfr.Enabled;
import lombok.Data;

@Entity
@Data
@Table(name = "users")

public class User {

    @Id
    @Column(name = "id")
    private int id;


    private String firstName;



}
