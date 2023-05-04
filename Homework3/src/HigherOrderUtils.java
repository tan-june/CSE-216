import java.util.List;
import java.util.Arrays;
import java.util.function.BiFunction;

public class HigherOrderUtils {
    public interface NamedBiFunction<X,Y,Z> extends BiFunction<X,Y,Z> {
        String name();
    }
    /*
    * First, write a static nested interface in HigherOrderUtils called NamedBiFunction. This interface must (15)
    * extend the interface java.util.Function.BiFunction. The interface should just have one additional
    * method declaration: String name();, i.e., a class implementing this interface must provide a “name”
    * for every instance of that class. Then, create public static NamedBiFunction instances as follows:
    * (a) add, with the name “plus”, to perform addition of two Doubles.
    * (b) subtract, with the name “minus”, to subtract the second Double from the first.
    * (c) multiply, with the name “mult”, to perform multiplication of two Doubles.
    (d) divide, with the name “div”, to divide the first Double by the second. This operation should
    throw a java.lang.ArithmeticException if there is a division by zero being attempted.
    * */

    //add
    public static NamedBiFunction<Double, Double, Double> add = new NamedBiFunction<>() {
        @Override
        public Double apply(Double a, Double b) {
            return a + b;
        }

        @Override
        public String name() {
            return "plus";
        }
    };

    //subtract
    public static NamedBiFunction<Double, Double, Double> subtract = new NamedBiFunction<>() {
        @Override
        public Double apply(Double a, Double b) {
            return a - b;
        }

        @Override
        public String name() {
            return "minus";
        }
    };

    //multiply
    public static NamedBiFunction<Double, Double, Double> multiply = new NamedBiFunction<>() {
        @Override
        public Double apply(Double a, Double b) {
            return a * b;
        }

        @Override
        public String name() {
            return "mult";
        }
    };

    //divide
    public static NamedBiFunction<Double, Double, Double> divide = new NamedBiFunction<>() {
        @Override
        public String name() {
            return "div";
        }

        //error checking for division by 0 - make sure to include
        @Override
        public Double apply(Double a, Double b) {
            if (Double.compare(b, 0) == 0) {
                throw new ArithmeticException("Division by Zero - Error");
            }
            return a / b;
        }
    };

    /**
     * Applies a given list of bifunctions -- functions that take two arguments of a certain type
     * and produce a single instance of that type -- to a list of arguments of that type. The
     * functions are applied in an iterative manner, and the result of each function is stored in
     * the list in an iterative manner as well, to be used by the next bifunction in the next
     * iteration. For example, given
     * List<Double> args = Arrays.asList(-0.5, 2d, 3d, 0d, 4d), and
     * List<NamedBiFunction<Double, Double, Double>> bfs = Arrays.asList(add, multiply, add, divide),
     * <code>zip(args, bfs)</code> will proceed as follows:
     * - the result of add(-0.5, 2.0) is stored in index 1 to yield args = [-0.5, 1.5, 3.0, 0.0, 4.0]
     * - the result of multiply(1.5, 3.0) is stored in index 2 to yield args = [-0.5, 1.5, 4.5, 0.0, 4.0]
     * - the result of add(4.5, 0.0) is stored in index 3 to yield args = [-0.5, 1.5, 4.5, 4.5, 4.0]
     * - the result of divide(4.5, 4.0) is stored in index 4 to yield args = [-0.5, 1.5, 4.5, 4.5, 1.125]
     *
     * @param args the arguments over which <code>bifunctions</code> will be applied.
     * @param bifunctions the list of bifunctions that will be applied on <code>args</code>.
     * @param <T> the type parameter of the arguments (e.g., Integer, Double)
     * @return the item in the last index of <code>args</code>, which has the final result
     * of all the bifunctions being applied in sequence.
     *
     * @throws IllegalArgumentException if the number of bifunction elements and the number of argument
     * elements do not match up as required.
     */
    public static <T> T zip(List<T> args, List<? extends BiFunction<T, T, T>> bifunctions){
        if (args.size() != bifunctions.size() + 1) { //args have to be one more than the number of functions to apply
            throw new IllegalArgumentException("Number of Args or BiFunctions Don't Match");
        }
        T result = args.get(0);
        for (int i = 0; i < bifunctions.size(); i++) {
            result = bifunctions.get(i).apply(result, args.get(i + 1));
            args.set(i + 1, result);
        }
        return args.get(args.size() - 1);
    }
}
