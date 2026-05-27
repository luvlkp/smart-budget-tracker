package com.example.examplefeature.ui;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.example.examplefeature.BudgetService;
import com.example.examplefeature.Expense;
import com.example.examplefeature.ExpenseGrid;


import java.time.LocalDate;


@Route("")
public class BudgetView extends VerticalLayout{
    private final BudgetService budgetService;
    public BudgetView(BudgetService budgetService){
        this.budgetService=budgetService;
        ExpenseGrid grid = new ExpenseGrid();
        GridListDataView<Expense> dataView= grid.setItems(budgetService.getList());
        H2 budgetDisplay = new H2("Budget: $0");
        H2 totalCostDisplay = new H2("Total Spent: $0");
        H2 nearBudget = new H2("At 80% of the budget: False");
        Button enterButton = new Button("Enter");
        Button enterBudgetButton = new Button("Enter");
        TextField descriptionField= new TextField("Description");
        NumberField costField= new NumberField("Cost");
        NumberField budgetField= new NumberField("Budget");
        Select<String> categoryField= new Select<>();
        Select<String> filterCategoryField= new Select<>();
        categoryField.setItems("Food", "Transportation", "Utilities", "Entertainment", "House");
        filterCategoryField.setItems("","Food", "Transportation", "Utilities", "Entertainment", "House");
        categoryField.setLabel("Category");
        categoryField.setPlaceholder("Select a category");
        filterCategoryField.setPlaceholder("Filter the grid");


        enterButton.addClickListener(clickEvent->{
            Expense item = new Expense(descriptionField.getValue(),costField.getValue(),categoryField.getValue(), LocalDate.now());
            budgetService.addExpense(item);
            grid.setItems(budgetService.getList());
            descriptionField.clear();
            categoryField.clear();
            costField.clear();
            totalCostDisplay.setText("Total Spent: $"+budgetService.getTotalCost());
            if (budgetService.closeToBudget()==true)
                nearBudget.setText("At 80% of the budget: True");
            else
                nearBudget.setText("At 80% of the budget: False");
            filterCategoryField.setValue("");

        });
        enterBudgetButton.addClickListener(clickEvent->{
            budgetService.setBudget(budgetField.getValue());
            budgetDisplay.setText("Budget: $"+budgetService.getBudget());
            budgetField.clear();
            if (budgetService.closeToBudget()==true)
                nearBudget.setText("At 80% of the budget: True");
            else
                nearBudget.setText("At 80% of the budget: False");
        });
        filterCategoryField.addValueChangeListener(ValueChangeEvent->{
            dataView.setFilter(expense->{
                if (filterCategoryField.getValue()=="")
                    return true;
                return expense.getCategory().equals(filterCategoryField.getValue());
            });

        });
        add(totalCostDisplay,budgetDisplay, nearBudget, budgetField, enterBudgetButton, descriptionField, costField, categoryField, enterButton, filterCategoryField, grid);
    }
}
