import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BijectionGroup<T> implements Group<Function<T,T>>{

    private Set<T> set;

    public BijectionGroup(Set<T> set) {
        this.set = set;
    }

    public static <T> BijectionGroup<T> bijectionGroup(Set<T> set) {
        return new BijectionGroup<>(set);
    }

    public Function<T, T> identity() {
        return x -> x;
    }

    public Function<T, T> binaryOperation(Function<T, T> f, Function<T, T> g) {
        return x -> f.apply(g.apply(x));
    }

    public Function<T, T> inverseOf(Function<T, T> f) {
        return null;
    }

}

