package io.hardingadonis.feizh.model;

import io.hardingadonis.feizh.model.detail.*;
import java.time.*;

public class ExpenseTransaction extends Transaction {

  private int categoryID;

  public ExpenseTransaction() {
    super();
  }

  public ExpenseTransaction(
      int sourceWalletID, long amount, String description, TransactionType type, int categoryID) {
    super(sourceWalletID, amount, description, type);
    this.categoryID = categoryID;
  }

  public ExpenseTransaction(
      int ID,
      int sourceWalletID,
      long amount,
      String description,
      TransactionType type,
      int categoryID) {
    super(ID, sourceWalletID, amount, description, type);
    this.categoryID = categoryID;
  }

  public ExpenseTransaction(
      int ID,
      int sourceWalletID,
      long amount,
      String description,
      TransactionType type,
      int categoryID,
      LocalDateTime createAt) {
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
    return "ExpenseTransaction{" + "super=" + super.toString() + ", categoryID=" + categoryID + '}';
  }
}
