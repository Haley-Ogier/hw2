package model;

import controller.InputValidation;
import java.util.ArrayList;
import java.util.List;

public class AmountFilter implements TransactionFilter {

    private double amount;

    public AmountFilter(double amount) {
        this.amount = amount;
    }

    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        if (!InputValidation.isValidAmount(amount)) {
            return new ArrayList<>();
        }

        List<Transaction> filtered = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getAmount() == amount) {
                filtered.add(t);
            }
        }
        return filtered;
    }
}
