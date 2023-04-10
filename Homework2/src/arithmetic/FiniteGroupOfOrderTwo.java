package arithmetic;

import core.Group;

import java.util.Arrays;
import java.util.List;


public class FiniteGroupOfOrderTwo implements Group<PlusOrMinusOne> {

    public FiniteGroupOfOrderTwo() {
        List<PlusOrMinusOne> elements = Arrays.asList(PlusOrMinusOne.PLUS_ONE, PlusOrMinusOne.MINUS_ONE);
    }

    @Override
    public PlusOrMinusOne binaryOperation(PlusOrMinusOne one, PlusOrMinusOne other) {
        int result = one.getValue() * other.getValue();
        if (result == 1) {
            return PlusOrMinusOne.PLUS_ONE;
        } else {
            return PlusOrMinusOne.MINUS_ONE;
        }
    }

    @Override
    public PlusOrMinusOne identity() {
        return PlusOrMinusOne.PLUS_ONE;
    }

    @Override
    public PlusOrMinusOne inverseOf(PlusOrMinusOne element) {
        if (element == PlusOrMinusOne.PLUS_ONE) {
            return PlusOrMinusOne.PLUS_ONE;
        } else {
            return PlusOrMinusOne.MINUS_ONE;
        }
    }
}
