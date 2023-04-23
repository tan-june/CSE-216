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
        return new Function<T, T>() {
            @Override
            public T apply(T input) {
                return set.stream()
                        .filter(output -> t.apply(output).equals(input))
                        .findFirst()
                        .orElseThrow(IllegalArgumentException::new);
            }
        };
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

        List<List<T>> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            T element = list.get(i);
            List<T> rest = new ArrayList<>(list.subList(0, i));
            rest.addAll(list.subList(i + 1, list.size()));
            List<List<T>> permutations = permutations(rest);
            for (List<T> permutation : permutations) {
                permutation.add(0, element);
                result.add(permutation);
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
        Set<Integer> a_few1 = Stream.of(10,40,60, 80, 90, 100, 50, 1000).collect(Collectors.toSet());
        Set<Integer> a_few2 = Stream.of(10, 100, 50, 1000).collect(Collectors.toSet());
        System.out.println();
        System.out.println("Bijections Of Test:");
        System.out.println();
        Set<Function<Integer, Integer>> bijections = bijectionsOf(a_few);
        System.out.println("Bijections 0 Size:"+ bijections.size());
        bijections.forEach(aBijection -> {
            a_few.forEach(n -> System.out.printf("%d --> %d; ", n, aBijection.apply(n)));
            System.out.println();
        });

        System.out.println();
        Set<Function<Integer, Integer>> bijections1 = bijectionsOf(a_few2);
        System.out.println("Bijections 1 Size:"+ bijections1.size());
        bijections1.forEach(aBijection -> {
            a_few2.forEach(n -> System.out.printf("%d --> %d; ", n, aBijection.apply(n)));
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

        System.out.println();

        BijectionGroup<Integer> g11 = new BijectionGroup<>(a_few1);
        Function<Integer, Integer> f11 = g11.bijectionGroup(a_few1).stream().findFirst().get();
        Function<Integer, Integer> f21 = g11.inverseOf(f11);
        Function<Integer, Integer> id1 = g11.identity();
        Set<Function<Integer, Integer>> functionSet1 = new LinkedHashSet<>();
        functionSet1.add(f11);
        functionSet1.add(f21);
        functionSet1.add(id1);
        functionSet1.forEach(aBijection -> {
            a_few1.forEach(n -> System.out.printf("%d --> %d; ", n, aBijection.apply(n)));
            System.out.println();
        });

        BijectionGroup<Integer> g12 = new BijectionGroup<>(a_few2);
        Function<Integer, Integer> f12 = g12.bijectionGroup(a_few2).stream().findFirst().get();
        Function<Integer, Integer> f22 = g12.inverseOf(f12);
        Function<Integer, Integer> id2 = g12.identity();

        Set<Function<Integer, Integer>> functionSet2 = new LinkedHashSet<>();
        functionSet2.add(f12);
        functionSet2.add(f22);
        functionSet2.add(id2);

        System.out.println();
        functionSet2.forEach(aBijection -> {
            a_few2.forEach(n -> System.out.printf("%d --> %d; ", n, aBijection.apply(n)));
            System.out.println();
        });

    }

}
