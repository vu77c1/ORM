package la101.enums;

public enum BillStatus {
    Paid(0),
    Unpaid(1);

    private final int value;

    BillStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
