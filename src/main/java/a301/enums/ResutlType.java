package a301.enums;

public enum ResutlType {

    PASS(1), FAIL(0);

    private final int value;

    ResutlType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
