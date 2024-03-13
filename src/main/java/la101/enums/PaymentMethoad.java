package la101.enums;

public enum PaymentMethoad {
    CARD(0),
    CASH(1),
    MOBILE_WALLET(5);

    private final int value;

    PaymentMethoad(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
