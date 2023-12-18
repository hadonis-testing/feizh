package io.hardingadonis.feizh.dao;

import io.hardingadonis.feizh.model.*;
import java.util.*;

public interface IExpenseTransactionDAO {

    public List<ExpenseTransaction> getAll();

    public ExpenseTransaction get(int ID);

    public void insert(ExpenseTransaction obj);
}
