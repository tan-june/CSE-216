import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BijectionGroup<T> implements Group<Function<T, T>> {

    private Set<T> set;

    public BijectionGroup(Set<T> set) {
        this.set = set;
    }

    @Override
    public Function<T, T> binaryOperation(Function<T, T> one, Function<T, T> other) {
        return one.compose(other);
    }

    @Override
    public Function<T, T> identity() {
        return Function.identity();
    }

    @Override
    public Function<T, T> inverseOf(Function<T, T> t) {
        return t.andThen(t).andThen(t);
    }

    public static <T> Set<Function<T, T>> bijectionsOf(Set<T> domain) {
        Set<Function<T, T>> bijections = new HashSet<>();
        List<T> elements = new ArrayList<>(domain);

        List<List<T>> permutations = permutations(elements);

        for (List<T> permutation : permutations) {
            Function<T, T> bijection = new Function<T, T>() {
                public T apply(T input) {
                    int index = permutation.indexOf(input);
                    return elements.get(index);
                }
            };
            bijections.add(bijection);
        }

        return bijections;
    }

    public static <T> List<List<T>> permutations(List<T> list) {
        if (list.size() == 0) {
            List<List<T>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }

        T first = list.get(0);
        List<T> rest = list.subList(1, list.size());
        List<List<T>> permutations = permutations(rest);
        List<List<T>> result = new ArrayList<>();

        for (List<T> permutation : permutations) {
            for (int i = 0; i <= permutation.size(); i++) {
                List<T> newPermutation = new ArrayList<>(permutation);
                newPermutation.add(i, first);
                result.add(newPermutation);
            }
        }

        return result;
    }

    public static <T> Set<Function<T, T>> bijectionGroup(Set<T> domain) {
        List<T> list = new ArrayList<>(domain);
        List<List<T>> permutations = permutations(list);

        Set<Function<T, T>> bijections = new HashSet<>();
        for (List<T> permutation : permutations) {
            bijections.add(new Function<T, T>() {
                @Override
                public T apply(T t) {
                    int index = list.indexOf(t);
                    if (index >= 0) {
                        return permutation.get(index);
                    }
                    return t;
                }
            });
        }

        return bijections;
    }




    public static void main(String... args) {

        Set<Integer> a_few = Stream.of(1, 2, 3).collect(Collectors.toSet());
        System.out.println();
        System.out.println("Bijections Of Test:");
        Set<Function<Integer, Integer>> bijections = bijectionsOf(a_few);
        bijections.forEach(aBijection -> {
            a_few.forEach(n -> System.out.printf("%d --> %d; ", n, aBijection.apply(n)));
            System.out.println();
        });


        System.out.println();
        System.out.println("Bijection Group Tests:");
        BijectionGroup<Integer> g = new BijectionGroup<>(a_few);
        Function<Integer, Integer> f1 = g.bijectionGroup(a_few).stream().findFirst().get();
        Function<Integer, Integer> f2 = g.inverseOf(f1);
        Function<Integer, Integer> id = g.identity();
        Set<Function<Integer, Integer>> functionSet = new LinkedHashSet<>();
        functionSet.add(f1);
        functionSet.add(f2);
        functionSet.add(id);
        functionSet.forEach(aBijection -> {
            a_few.forEach(n -> System.out.printf("%d --> %d; ", n, aBijection.apply(n)));
            System.out.println();
        });


    }

}
