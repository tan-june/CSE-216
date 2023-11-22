import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BijectionGroup<T> implements Group<Function<T,T>> {

    private final Set<T> set;

    public BijectionGroup(Set<T> set) {
        this.set = set;
    }

    @Override
    public Function<T, T> binaryOperation(Function<T, T> f1, Function<T, T> f2) {
        return f1.compose(f2);
    }

    @Override
    public Function<T, T> identity() {
        return Function.identity();
    }

    @Override
    public Function<T, T> inverseOf(Function<T,T> f) {
        return x -> set.stream()
                .filter(y -> f.apply(y).equals(x))
                .findFirst()
                .orElseThrow(null);
    }

    public static <T> Group<Function<T,T>> bijectionGroup(Set<T> domainSet) {
        return new BijectionGroup<>(domainSet);
    }

    public static <T> Set<Function<T, T>> bijectionsOf(Set<T> domain) {
        Set<Function<T, T>> bijectionsOfSet = new HashSet<>();

        List<T> elements = new ArrayList<>(domain);
        List<List<T>> listsOfPermutation = permutations(elements);

        for (int i = 0; i < listsOfPermutation.size(); i++) {
            List<T> permutation = listsOfPermutation.get(i);
            Function<T, T> bijection = x -> elements.get(permutation.indexOf(x));
            bijectionsOfSet.add(bijection);
        }
        return bijectionsOfSet;
    }

    public static <T> List<List<T>> permutations(List<T> list) {
        if (list.size() == 0) {
            List<List<T>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }

        List<List<T>> allPermutations = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            T element = list.get(i);
            List<T> sub = new ArrayList<>(list.subList(0, i));
            sub.addAll(list.subList(i + 1, list.size()));
            List<List<T>> permutations = permutations(sub);

            for (int j = 0; j < permutations.size(); j++) {
                List<T> t = permutations.get(j);
                t.add(0, element);
                allPermutations.add(t);
            }
        }
        return allPermutations;
    }
}


