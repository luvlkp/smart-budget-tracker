package com.example.examplefeature.ui;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.example.examplefeature.BudgetService;
import com.example.examplefeature.Expense;
import com.example.examplefeature.ExpenseGrid;
import java.time.LocalDate;


///import java.util.list;
///import java.util.map;
//import java.util.stream.Collectors;
//import java.util.ArrayList;





@Route("")
public class BudgetView extends VerticalLayout{
    private final BudgetService budgetService;
    public BudgetView(BudgetService budgetService){
        this.budgetService=budgetService;
        
        getStyle().set("background-color", "#c3d2e1");

        //Creation of grids, list, fields, and buttons
        ExpenseGrid grid = new ExpenseGrid();
        GridListDataView<Expense> dataView= grid.setItems(budgetService.getList());
        H2 budgetDisplay = new H2("Budget: $0");
        H2 totalCostDisplay = new H2("Total Spent: $0");
        H2 nearBudget = new H2("At 0% of the budget.");
        H2 boxTitle = new H2("Enter A Purchase");
        Button enterItemButton = new Button("Enter");
        Button enterBudgetButton = new Button("Enter");
        TextField descriptionField= new TextField("Description");
        NumberField costField= new NumberField("Cost");
        NumberField budgetField= new NumberField("Budget");
        Select<String> categoryField= new Select<>();
        Select<String> filterCategoryField= new Select<>();
        VerticalLayout itemCard = new VerticalLayout();
        VerticalLayout infoBox = new VerticalLayout();
        HorizontalLayout budgetBox= new HorizontalLayout();

        //Setting the Fields
        budgetField.setPlaceholder("Enter budget");
        descriptionField.setPlaceholder("Enter description");
        costField.setPlaceholder("Enter cost");
        categoryField.setItems("Food", "Transportation", "Utilities", "Entertainment", "House");
        filterCategoryField.setItems("","Food", "Transportation", "Utilities", "Entertainment", "House");
        categoryField.setLabel("Category");
        categoryField.setPlaceholder("Select a category");
        filterCategoryField.setPlaceholder("Filter the grid");
        itemCard.getStyle()
            .set("border", "1px solid #e0e0e0")  
            .set("border-radius", "8px")         
            .set("padding", "20px")           
            .set("background-color", "#b2bfcd")  
            .set("max-width", "320px")
            .set("max-height", "320px");
        itemCard.add(boxTitle,descriptionField,costField,categoryField,enterItemButton);
        budgetBox.add(budgetField,enterBudgetButton);
        budgetBox.setAlignItems(Alignment.BASELINE);
        infoBox.add(totalCostDisplay,budgetDisplay,nearBudget);
        infoBox.getStyle()
            .set("border", "1px solid #e0e0e0")  
            .set("border-radius", "8px")         
            .set("padding", "20px")           
            .set("background-color", "#c3d2e1")  
            .set("max-width", "320px");

        //Event when the enter button is pressed
        enterItemButton.addClickListener(clickEvent->{
            Expense item = new Expense(descriptionField.getValue(),costField.getValue(),categoryField.getValue(), LocalDate.now());
            budgetService.addExpense(item);
            grid.setItems(budgetService.getList());
            descriptionField.clear();
            categoryField.clear();
            costField.clear();
            totalCostDisplay.setText("Total Spent: $"+budgetService.getTotalCost());
            nearBudget.setText("At "+budgetService.closeToBudget()+"% of the budget");
            filterCategoryField.setValue("");

        });

        //Event when the budget enter button is pressed
        enterBudgetButton.addClickListener(clickEvent->{
            budgetService.setBudget(budgetField.getValue());
            budgetDisplay.setText("Budget: $"+budgetService.getBudget());
            budgetField.clear();
            nearBudget.setText("At "+budgetService.closeToBudget()+"% of the budget");
        });

        //Event when the category grid filter field is changed
        filterCategoryField.addValueChangeListener(ValueChangeEvent->{
            dataView.setFilter(expense->{
                if (filterCategoryField.getValue()=="")
                    return true;
                return expense.getCategory().equals(filterCategoryField.getValue());
            });

        });

        //Adds all the UI to the screen
        add(infoBox, budgetBox ,itemCard, filterCategoryField, grid);
        
        
        
    }
    
    
}
