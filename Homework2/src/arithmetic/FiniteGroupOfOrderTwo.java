package arithmetic;
import core.Group;
import java.util.Arrays;
import java.util.List;


public class FiniteGroupOfOrderTwo implements Group<PlusOrMinusOne> {

    private final List<PlusOrMinusOne> elements;

    public FiniteGroupOfOrderTwo() {
        elements = Arrays.asList(PlusOrMinusOne.PLUS_ONE, PlusOrMinusOne.MINUS_ONE);
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
    public PlusOrMinusOne inverseOf(PlusOrMinusOne plusOrMinusOne) {
        return binaryOperation(plusOrMinusOne, plusOrMinusOne);
    }

}
