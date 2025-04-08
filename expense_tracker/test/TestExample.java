// package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import controller.ExpenseTrackerController;
import model.AmountFilter;
import model.CategoryFilter;
import model.ExpenseTrackerModel;
import model.Transaction;
import view.ExpenseTrackerView;


public class TestExample {
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;
  private ExpenseTrackerController controller;

  @Before
  public void setup() {
    model = new ExpenseTrackerModel();
    view = new ExpenseTrackerView();
    controller = new ExpenseTrackerController(model, view);
  }

    public double getTotalCost() {
        double totalCost = 0.0;
        List<Transaction> allTransactions = model.getTransactions(); // Using the model's getTransactions method
        for (Transaction transaction : allTransactions) {
            totalCost += transaction.getAmount();
        }
        return totalCost;
    }
    


    @Test
    public void testAddTransaction() {
        // Pre-condition: List of transactions is empty
        assertEquals(0, model.getTransactions().size());
    
        // Perform the action: Add a transaction
        assertTrue(controller.addTransaction(50.00, "food"));
    
        // Post-condition: List of transactions contains one transaction
        assertEquals(1, model.getTransactions().size());
    
        // Check the contents of the list
        assertEquals(50.00, getTotalCost(), 0.01);
    }


    @Test
    public void testRemoveTransaction() {
        // Pre-condition: List of transactions is empty
        assertEquals(0, model.getTransactions().size());
    
        // Perform the action: Add and remove a transaction
        Transaction addedTransaction = new Transaction(50.00, "Groceries");
        model.addTransaction(addedTransaction);
    
        // Pre-condition: List of transactions contains one transaction
        assertEquals(1, model.getTransactions().size());
    
        // Perform the action: Remove the transaction
        model.removeTransaction(addedTransaction);
    
        // Post-condition: List of transactions is empty
        List<Transaction> transactions = model.getTransactions();
        assertEquals(0, transactions.size());
    
        // Check the total cost after removing the transaction
        double totalCost = getTotalCost();
        assertEquals(0.00, totalCost, 0.01);
    }
    

    @Test
    public void testAddTransaction_New() {
        // Pre-condition: List of transactions is empty
        assertEquals(0, model.getTransactions().size());
    
        // Perform the action: Add a transaction
        assertTrue(controller.addTransaction(34.21, "food"));
    
        // Post-condition: List of transactions contains one transaction
        assertEquals(1, model.getTransactions().size());
    
        // Check the contents of the list
        assertEquals(34.21, getTotalCost(), 0.01);
    }


    @Test
    public void testInvalidInputHandling() {
        // Pre-condition: List of transactions is empty and total cost is 0
        assertEquals(0, model.getTransactions().size());
        double startingTotal = getTotalCost();

        // Perform the action: Try to add invalid transactions such as negative amount
        boolean negativeAmount = controller.addTransaction(-5.0, "food");

        // Post-condition: Should return false, show error message to user, and not add the transaction
        assertFalse(negativeAmount);

        // Perform the action: Try to add invalid transactions such as invalid category
        boolean invalidCategoryResult = controller.addTransaction(20, "sports");
        
        // Post-condition: Should return false, show error message to user, and not add the transaction
        assertFalse(invalidCategoryResult);

        // Validation: no transactions should have been added and total cost should remain unchanged
        assertEquals(0, model.getTransactions().size());
        assertEquals(startingTotal, getTotalCost(), 0.01);
    }


    @Test
    public void testFilterByAmount() {
        // Pre-condition: List of transactions is empty
        assertEquals(0, model.getTransactions().size());
        
        // Setup: Add multiple transactions with different amounts and categories
        controller.addTransaction(15.00, "bills");     
        controller.addTransaction(45.07, "travel");  
        controller.addTransaction(15.00, "food"); 
        controller.addTransaction(15.32, "travel"); 
        controller.addTransaction(15, "other"); 

        // Perform the action: Filter the transactions by amount
        AmountFilter amountFilter = new AmountFilter(15.00);
        List<Transaction> filteredList = amountFilter.filter(model.getTransactions());

        // Post-condition: Should find 3 transactions with amount=15.00
        assertEquals(3, filteredList.size());

        // Validation: Check the amount of each filtered transaction
        for (Transaction t : filteredList) {
            assertEquals("Amount should be 15.00", 15.00, t.getAmount(), 0.01);
        }
    }


    @Test
    public void testFilterByCategory() {
        // Pre-condition: List of transactions is empty
        assertEquals(0, model.getTransactions().size());

        // Setup: Add multiple transactions with different amounts and categories
        controller.addTransaction(15.00, "bills");     
        controller.addTransaction(45.07, "travel");  
        controller.addTransaction(15.00, "food"); 
        controller.addTransaction(15.32, "travel"); 
        controller.addTransaction(15, "other"); 

        // Perform the action: Filter the transactions by category
        CategoryFilter categoryFilter = new CategoryFilter("travel");
        List<Transaction> filteredList = categoryFilter.filter(model.getTransactions());

        // Post-condition: Should find 2 transactions with category=travel
        assertEquals(2, filteredList.size());

        // Validation: Check the category of each filtered transaction
        for (Transaction t : filteredList) {
            assertEquals("travel", t.getCategory());
        }
    }


}