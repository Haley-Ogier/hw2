package model;

import controller.InputValidation;
import java.util.ArrayList;
import java.util.List;

public class CategoryFilter implements TransactionFilter {

    private String category;

    public CategoryFilter(String category) {
        this.category = category;
    }

    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        if (!InputValidation.isValidCategory(category)) {
            return new ArrayList<>();
        }

        List<Transaction> filtered = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getCategory().equalsIgnoreCase(category)) {
                filtered.add(t);
            }
        }
        return filtered;
    }
}
