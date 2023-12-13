package io.hardingadonis.feizh.model;

import io.hardingadonis.feizh.model.detail.TransactionType;
import java.time.LocalDateTime;

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

    public IncomeTransaction(int ID, int sourceWalletID, long amount, String description, TransactionType type, int categoryID, LocalDateTime createAt, LocalDateTime updateAt) {
        super(ID, sourceWalletID, amount, description, type, createAt, updateAt);
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
