package com.example.examplefeature;
import com.vaadin.flow.component.grid.Grid;

public class ExpenseGrid extends Grid<Expense>{
    public ExpenseGrid(){
        addColumn(Expense::getDescription).setHeader("Description");    
        addColumn(Expense::getCategory).setHeader("Category");    
        addColumn(Expense::getPrice).setHeader("Price");    
        addColumn(Expense::getDate).setHeader("Date"); 

    }
}