package org.acme.rest.json;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class ExpenseService {
    private Set<Expense> expenses = Collections.newSetFromMap(Collections.synchronizedMap(new HashMap<>()));

    public Set<Expense> list() {
        return expenses;
    }

    public Expense create(Expense expense) {
        expenses.add(expense);
        return expense;
    }

    public boolean delete(UUID uuid) {
        return expenses.removeIf(expense -> expense.getUuid().equals(uuid));
    }

    public void update(Expense expense) {
        delete(expense.getUuid());
        create(expense);
    }

    public boolean exists(UUID uuid) {
        return expenses.stream().anyMatch(exp -> exp.getUuid().equals(uuid));
    }
}