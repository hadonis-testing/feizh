package io.hardingadonis.feizh.model.detail;

public enum TransactionType {
    INCOME("income"),
    EXPENSE("expense"),
    TRANSFER("transfer");

    private final String label;

    private TransactionType(String label) {
        this.label = label;
    }

    public static TransactionType create(String type) {
        switch (type) {
            case "income":
                return INCOME;
            case "expense":
                return EXPENSE;
            case "transfer":
            default:
                return TRANSFER;
        }
    }

    @Override
    public String toString() {
        return label;
    }
}
