import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.ExpenseTrackerController;
import model.AmountFilter;
import model.CategoryFilter;
import model.ExpenseTrackerModel;
import view.ExpenseTrackerView;
import model.Transaction;
import model.TransactionFilter;
import controller.InputValidation;

public class ExpenseTrackerApp {

  public static void main(String[] args) {
    
    // Create MVC components
    ExpenseTrackerModel model = new ExpenseTrackerModel();
    ExpenseTrackerView view = new ExpenseTrackerView();
    ExpenseTrackerController controller = new ExpenseTrackerController(model, view);

    // Initialize view
    view.setVisible(true);

    // Handle add transaction button clicks
    view.getAddTransactionBtn().addActionListener(e -> {
      // Get transaction data from view
      double amount = view.getAmountField();
      String category = view.getCategoryField();
      
      // Call controller to add transaction
      boolean added = controller.addTransaction(amount, category);
      
      if (!added) {
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
        view.toFront();
      }
    });

    view.getApplyFilterBtn().addActionListener(e -> {
      String filterType = view.getFilterType();     
      String filterValue = view.getFilterValue(); 

      if ("Category".equalsIgnoreCase(filterType)) {
        if (!InputValidation.isValidCategory(filterValue)) {
          JOptionPane.showMessageDialog(view, "Invalid category. Must be one of: food, travel, bills, entertainment, other.");
          return; 
        }
        controller.applyFilter(new CategoryFilter(filterValue));
      } else {
        double amount;
        try {
          amount = Double.parseDouble(filterValue);
        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(view,"Invalid number format for amount. Please enter a numeric value.");
          return;
        }
        if (!InputValidation.isValidAmount(amount)) {
          JOptionPane.showMessageDialog(view,"Amount must be > 0 and < 1000.");
          return; 
        }
        controller.applyFilter(new AmountFilter(amount));
      }
    });
  }

}