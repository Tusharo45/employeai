package com.example.employeeai.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String department;

    private String skills;

    private int experience;

    private String projectName;

    private double rating;

    @Column(length = 5000)
    private String aiSummary;
}