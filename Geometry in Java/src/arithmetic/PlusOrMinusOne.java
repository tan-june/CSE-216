package arithmetic;

enum PlusOrMinusOne {
    MINUS_ONE(-1),
    PLUS_ONE(1);
    private final int integer;

    PlusOrMinusOne(int integer) {
        this.integer = integer;
    }

    public int getValue() {
        return integer;
    }

    @Override
    public String toString() {
        return Integer.toString(integer);
    }
}