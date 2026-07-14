package com.example.examplefeature;

import java.time.LocalDate;
public class Expense {
    private String description, category;
    private double price;
    private LocalDate date;
    public Expense(String description, double price, String category, LocalDate date){
        this.description=description;
        this.category=category;
        this.price=price;
        this.date=date;
    }
    //Getters for variables
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