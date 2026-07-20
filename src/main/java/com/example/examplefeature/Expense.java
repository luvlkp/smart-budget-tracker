package com.example.examplefeature;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String description, category;
    private double price;
    private LocalDate date;

    public Expense(){

    }

    public Expense(String description, double price, String category, LocalDate date){
        this.description=description;
        this.category=category;
        this.price=price;
        this.date=date;
    }
    //Getters for variables
    public Long getId(){
        return Id;
    }
    public String getDescription(){
        return description;
    }
    public LocalDate getDate(){
        return date;
    }
    public String getCategory(){
        return category;
    }
    public double getPrice(){
        return price;
    }
    //Setters for variables
    public void setId(Long Id){
        this.Id=Id;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public void setCategory(String category){
        this.category=category;
    }
    public void setPrice(double price){
        this.price=price;
    }

    public String toString(){
        return category+": "+description+": "+price+":"+getDate();
    }
}