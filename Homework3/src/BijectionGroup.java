import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BijectionGroup<T> implements Group<Function<T,T>>{

    //Part 5
    public static <T> Set<Function<T, T>> bijectionsOf(Set<T> domain) {
        Set<Function<T, T>> bijections = new HashSet<>();
        List<T> domainList = new ArrayList<>(domain);
        if (domainList.size() == 1) {
            bijections.add(Function.identity());
        } else {
            for (int i = 0; i < domainList.size(); i++) {
                T x = domainList.get(i);
                List<T> rest = new ArrayList<>(domainList.subList(0, i));
                rest.addAll(domainList.subList(i + 1, domainList.size()));
                Set<Function<T, T>> restBijections = bijectionsOf(new HashSet<>(rest));
                for (Function<T, T> f : restBijections) {
                    Function<T, T> bijection = t -> {
                        if (t.equals(x)) {
                            return f.apply(domainList.get(0));
                        } else {
                            return f.apply(t);
                        }
                    };
                    bijections.add(bijection);
                }
            }
        }
        return bijections;
    }


    @Override
    public String toString() {
        List<String> bijections = set.stream()
                .map(Object::toString)
                .collect(Collectors.toList());
        return String.join("\n", bijections);
    }

    //Part 6
    private Set<Function<T,T>> set;
    public BijectionGroup(Set<Function<T,T>> set) {
        this.set = set;
    }
    public Function<T, T> identity() {
        return new Function<T, T>() {
            @Override
            public T apply(T t) {
                return t;
            }
        };
    }
    public Function<T, T> binaryOperation(Function<T, T> f, Function<T, T> g) {
        return x -> {
            T gx = g.apply(x);
            T fx = f.apply(gx);
            return fx;
        };
    }
    public Function<T, T> inverseOf(Function<T, T> f) {
        return x -> {
            T result = f.apply(x);
            return inverseOf(f).apply(result);
        };
    }

    //getter method
    public Set<Function<T, T>> getSet(){
        return set;
    }

    public static <T> BijectionGroup<T> bijectionGroup(Set<T> set) {
        Set<Function<T, T>> bijections = new HashSet<>();
        for (T element1 : set) {
            for (T element2 : set) {
                if (element1 != element2) {
                    bijections.add(x -> x.equals(element1) ? element2 : x);
                }
            }
        }
        return new BijectionGroup<>(bijections);
    }


    public static void main(String... args) {
        Set<Integer> a_few = Stream.of(1, 2, 3).collect(Collectors.toSet());
        Set<Function<Integer, Integer>> bijections = bijectionsOf(a_few);
        bijections.forEach(aBijection -> {
            a_few.forEach(n -> System.out.printf("%d --> %d; ", n, aBijection.apply(n)));
            System.out.println();
        });

        System.out.println();
        System.out.println();

        BijectionGroup<Integer> g = bijectionGroup(a_few);
        Function<Integer, Integer> f1 = bijectionsOf(a_few).stream().findFirst().get();
        Function<Integer, Integer> f2 = g.inverseOf(f1);
        Function<Integer, Integer> id = g.identity();

        System.out.println("Set of elements: " + a_few);
        System.out.println("Bijection group: " + g);
        System.out.println("Random bijection f1: " + f1);
        System.out.println("Inverse of f1: " + f2);
        System.out.println("Identity function: " + id);

    }


    }

