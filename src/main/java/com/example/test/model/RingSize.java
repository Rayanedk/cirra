package com.example.test.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ring_sizes")
public class RingSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String canadian;
    private double diameter;
    private double circumference;

    // Getters & Setters
    public Long getId() { return id; }
    public String getCanadian() { return canadian; }
    public void setCanadian(String canadian) { this.canadian = canadian; }

    public double getDiameter() { return diameter; }
    public void setDiameter(double diameter) { this.diameter = diameter; }

    public double getCircumference() { return circumference; }
    public void setCircumference(double circumference) { this.circumference = circumference; }
}
