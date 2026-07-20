package com.example.examplefeature;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{
    //Spring Data JPA creates all the data and lets me use 
    //.save(Expense), .findAll(), delete(Expense) without needing SQL
}
