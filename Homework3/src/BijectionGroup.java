import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BijectionGroup<T> implements Group<Function<T,T>> {

    private final Set<T> domainSet;

    public BijectionGroup(Set<T> set) {
        this.domainSet = set;
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
        return x -> domainSet.stream()
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


    public static void main(String... args) {
//        Set<Integer> a_few = Stream.of(1, 2, 3).collect(Collectors.toSet());
//        Set<Integer> a_few1 = Stream.of(10,40,60, 100).collect(Collectors.toSet());
//        Set<Integer> a_few2 = Stream.of(10, 23, 72).collect(Collectors.toSet());
//
//        System.out.println();
//        System.out.println("Bijections Of Test:");
//        System.out.println();
//        Set<Function<Integer, Integer>> bijections = bijectionsOf(a_few);
//        System.out.println("Bijections 0 Size:"+ bijections.size());
//        bijections.forEach(aBijection -> {
//            a_few.forEach(n -> System.out.printf("%d --> %d; ", n, aBijection.apply(n)));
//            System.out.println();
//        });
//
//        System.out.println();
//        Set<Function<Integer, Integer>> bijections3 = bijectionsOf(a_few1);
//        System.out.println("Bijections 1 Size:"+ bijections3.size());
//        bijections3.forEach(aBijection -> {
//            a_few1.forEach(n -> System.out.printf("%d --> %d; ", n, aBijection.apply(n)));
//            System.out.println();
//        });
//
//        System.out.println();
//        Set<Function<Integer, Integer>> bijections1 = bijectionsOf(a_few2);
//        System.out.println("Bijections 1 Size:"+ bijections1.size());
//        bijections1.forEach(aBijection -> {
//            a_few2.forEach(n -> System.out.printf("%d --> %d; ", n, aBijection.apply(n)));
//            System.out.println();
//        });
//
//
//        System.out.println();
//        System.out.println("Bijection Group Tests:");
//
//        Group<Function<Integer,Integer>> g = bijectionGroup(a_few);
//        Function<Integer, Integer> f1 = bijectionsOf(a_few).stream().findFirst().get();
//        Function<Integer, Integer> f2 = g.inverseOf(f1);
//        Function<Integer, Integer> id = g.identity();
//        Set<Function<Integer, Integer>> functionSet = new LinkedHashSet<>();
//        functionSet.add(f1);
//        functionSet.add(f2);
//        functionSet.add(id);
//        functionSet.forEach(aBijection -> {
//            a_few.forEach(n -> System.out.printf("%d --> %d; ", n, aBijection.apply(n)));
//            System.out.println();
//        });
//
//        System.out.println();
//        Group<Function<Integer,Integer>> g11 = bijectionGroup(a_few1);
//        Function<Integer, Integer> f11 = bijectionsOf(a_few1).stream().findFirst().get();
//        Function<Integer, Integer> f21 = g11.inverseOf(f11);
//        Function<Integer, Integer> id1 = g11.identity();
//        Set<Function<Integer, Integer>> functionSet1 = new LinkedHashSet<>();
//        functionSet1.add(f11);
//        functionSet1.add(f21);
//        functionSet1.add(id1);
//        functionSet1.forEach(aBijection -> {
//            a_few1.forEach(n -> System.out.printf("%d --> %d; ", n, aBijection.apply(n)));
//            System.out.println();
//        });
//
//        Group<Function<Integer,Integer>> g12 = bijectionGroup(a_few2);
//        Function<Integer, Integer> f12 = bijectionsOf(a_few2).stream().findFirst().get();
//        Function<Integer, Integer> f22 = g12.inverseOf(f12);
//        Function<Integer, Integer> id2 = g12.identity();
//
//        Set<Function<Integer, Integer>> functionSet2 = new LinkedHashSet<>();
//        functionSet2.add(f12);
//        functionSet2.add(f22);
//        functionSet2.add(id2);
//
//        System.out.println();
//        functionSet2.forEach(aBijection -> {
//            a_few2.forEach(n -> System.out.printf("%d --> %d; ", n, aBijection.apply(n)
//            ));
//            System.out.println();
//        });
    }
}


