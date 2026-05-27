package com.example.examplefeature;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
@Service
public class BudgetService {
    private ArrayList<Expense> list= new ArrayList<>();
    private double totalCost=0, budget=0;
    public BudgetService(){
    }
    public double getTotalCost(){
        return totalCost;
    }
    public ArrayList<Expense> getList(){
        return list;
    }
    public void addExpense(Expense item){
        list.add(item);
        totalCost+=item.getPrice();
    }
    public double getBudget(){
        return budget;
    }
    public void setBudget(double budget){
        this.budget=budget;
    }
    public boolean closeToBudget(){
        return getTotalCost()>=(.8*getBudget());
    }

    
}
