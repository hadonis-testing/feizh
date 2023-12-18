package io.hardingadonis.feizh.model.detail;

public enum CategoryType {
    INCOME("income"),
    EXPENSE("expense");

    private final String label;

    private CategoryType(String label) {
        this.label = label;
    }

    public static CategoryType create(String type) {
        switch (type) {
            case "income":
                return INCOME;
            case "expense":
            default:
                return EXPENSE;
        }
    }

    @Override
    public String toString() {
        return label;
    }
}
