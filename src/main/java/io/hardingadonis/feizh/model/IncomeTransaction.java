package io.hardingadonis.feizh.model;

import io.hardingadonis.feizh.model.detail.*;
import java.time.*;

public class IncomeTransaction extends Transaction {

    private int categoryID;

    public IncomeTransaction() {
        super();
    }

    public IncomeTransaction(int sourceWalletID, long amount, String description, TransactionType type, int categoryID) {
        super(sourceWalletID, amount, description, type);
        this.categoryID = categoryID;
    }

    public IncomeTransaction(int ID, int sourceWalletID, long amount, String description, TransactionType type, int categoryID) {
        super(ID, sourceWalletID, amount, description, type);
        this.categoryID = categoryID;
    }

    public IncomeTransaction(int ID, int sourceWalletID, long amount, String description, TransactionType type, int categoryID, LocalDateTime createAt) {
        super(ID, sourceWalletID, amount, description, type, createAt);
        this.categoryID = categoryID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public String toString() {
        return "IncomeTransaction{"+ "super=" + super.toString() + ", categoryID=" + categoryID + '}';
    }
}
