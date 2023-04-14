import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BijectionGroup {

    public static <T> Set<Function<T, T>> bijectionsOf(Set<T> domain) {
        Set<Function<T, T>> bijections = new HashSet<>();


        return bijections;
    }




    public static void main(String... args) {
//        Set<Integer> a_few = Stream.of(1, 2, 3).collect(Collectors.toSet());
//        BijectionGroup<Integer> g = bijectionGroup(a_few);
//        Bijection<Integer> f1 = bijectionsOf(a_few).stream().findFirst().get();
//        Bijection<Integer> f2 = g.inverseOf(f1);
//        Bijection<Integer> id = g.identity();
//
//        String groupString = "Bijection group G: " + g.toString();
//        String f1String = "First bijection f1: " + f1.toString();
//        String f2String = "Inverse of f1, f2: " + f2.toString();
//        String idString = "Identity element of G: " + id.toString();
//
//        System.out.println(groupString);
//        System.out.println(f1String);
//        System.out.println(f2String);
//        System.out.println(idString);
    }

}

