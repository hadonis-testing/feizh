package io.hardingadonis.feizh.model;

import io.hardingadonis.feizh.model.detail.*;
import java.time.*;

public class TransferTransaction extends Transaction {

    private int targetWalletID;

    public TransferTransaction() {
        super();
    }

    public TransferTransaction(int sourceWalletID, long amount, String description, TransactionType type, int targetWalletID) {
        super(sourceWalletID, amount, description, type);
        this.targetWalletID = targetWalletID;
    }

    public TransferTransaction(int ID, int sourceWalletID, long amount, String description, TransactionType type, int targetWalletID) {
        super(ID, sourceWalletID, amount, description, type);
        this.targetWalletID = targetWalletID;
    }

    public TransferTransaction(int ID, int sourceWalletID, long amount, String description, TransactionType type, LocalDateTime createAt, LocalDateTime updateAt, int targetWalletID) {
        super(ID, sourceWalletID, amount, description, type, createAt, updateAt);
        this.targetWalletID = targetWalletID;
    }

    public int getTargetWalletID() {
        return targetWalletID;
    }

    public void setTargetWalletID(int targetWalletID) {
        this.targetWalletID = targetWalletID;
    }

    @Override
    public String toString() {
        return "TransferTransaction{" + "super=" + super.toString() + ", targetWalletID=" + targetWalletID + '}';
    }
}
