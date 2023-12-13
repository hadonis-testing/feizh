package io.hardingadonis.feizh.model;

import io.hardingadonis.feizh.model.detail.*;
import java.time.*;

public class Wallet {

    private int ID;

    private String name;
    private WalletType type;
    private long balance;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;

    public Wallet() {
    }

    public Wallet(String name, WalletType type, long balance) {
        this.name = name;
        this.type = type;
        this.balance = balance;
    }

    public Wallet(int ID, String name, WalletType type, long balance) {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.balance = balance;
    }

    public Wallet(int ID, String name, WalletType type, long balance, LocalDateTime createAt, LocalDateTime updateAt, LocalDateTime deleteAt) {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.balance = balance;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.deleteAt = deleteAt;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WalletType getType() {
        return type;
    }

    public void setType(WalletType type) {
        this.type = type;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public LocalDateTime getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(LocalDateTime deleteAt) {
        this.deleteAt = deleteAt;
    }

    @Override
    public String toString() {
        return "Wallet{" + "ID=" + ID + ", name=" + name + ", type=" + type + ", balance=" + balance + ", createAt=" + createAt + ", updateAt=" + updateAt + ", deleteAt=" + deleteAt + '}';
    }
}
