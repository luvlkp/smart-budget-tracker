package com.example.examplefeature;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BudgetService {
    private final ExpenseRepository expenseRepository;
    private double budget=0;

    public BudgetService(ExpenseRepository expenseRepository){
        this.expenseRepository=expenseRepository;
    }

    public double getTotalCost(){
        return expenseRepository.findAll().stream()
            .mapToDouble(Expense::getPrice)
            .sum();
    }
    public List<Expense> getList(){
        return expenseRepository.findAll();
    }
    public void addExpense(Expense item){
        expenseRepository.save(item);
    }
    public double getBudget(){
        return budget;
    }
    public void setBudget(double budget){
        this.budget=budget;
    }
    public int closeToBudget(){
        if (getBudget()==0)
            return 0;
        return (int)(100*getTotalCost()/getBudget());
    }

    
}
