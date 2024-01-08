package io.hardingadonis.feizh.model;

import io.hardingadonis.feizh.model.detail.*;
import java.time.*;

public class Transaction {

  private int ID;

  private int sourceWalletID;
  private long amount;
  private String description;
  private TransactionType type;

  private LocalDateTime createAt;

  public Transaction() {}

  public Transaction(int sourceWalletID, long amount, String description, TransactionType type) {
    this.sourceWalletID = sourceWalletID;
    this.amount = amount;
    this.description = description;
    this.type = type;
  }

  public Transaction(
      int ID, int sourceWalletID, long amount, String description, TransactionType type) {
    this.ID = ID;
    this.sourceWalletID = sourceWalletID;
    this.amount = amount;
    this.description = description;
    this.type = type;
  }

  public Transaction(
      int ID,
      int sourceWalletID,
      long amount,
      String description,
      TransactionType type,
      LocalDateTime createAt) {
    this.ID = ID;
    this.sourceWalletID = sourceWalletID;
    this.amount = amount;
    this.description = description;
    this.type = type;
    this.createAt = createAt;
  }

  public int getID() {
    return ID;
  }

  public void setID(int ID) {
    this.ID = ID;
  }

  public int getSourceWalletID() {
    return sourceWalletID;
  }

  public void setSourceWalletID(int sourceWalletID) {
    this.sourceWalletID = sourceWalletID;
  }

  public long getAmount() {
    return amount;
  }

  public void setAmount(long amount) {
    this.amount = amount;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public TransactionType getType() {
    return type;
  }

  public void setType(TransactionType type) {
    this.type = type;
  }

  public LocalDateTime getCreateAt() {
    return createAt;
  }

  public void setCreateAt(LocalDateTime createAt) {
    this.createAt = createAt;
  }

  @Override
  public String toString() {
    return "Transaction{"
        + "ID="
        + ID
        + ", sourceWalletID="
        + sourceWalletID
        + ", amount="
        + amount
        + ", description="
        + description
        + ", type="
        + type
        + ", createAt="
        + createAt
        + '}';
  }
}
