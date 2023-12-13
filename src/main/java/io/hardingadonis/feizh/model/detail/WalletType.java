package io.hardingadonis.feizh.model.detail;

public enum WalletType {
    CASH("cash"),
    BANK("bank");

    private final String label;

    private WalletType(String label) {
        this.label = label;
    }

    public static WalletType create(String type) {
        switch (type) {
            case "cash":
                return CASH;
            case "bank":
            default:
                return BANK;
        }
    }

    @Override
    public String toString() {
        return label;
    }
}
