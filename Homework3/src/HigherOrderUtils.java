import java.util.List;
import java.util.function.BiFunction;
import java.util.Arrays;

public class HigherOrderUtils {
    public static interface NamedBiFunction<X,Y,Z> extends BiFunction<X,Y,Z> {
        String name();
    }
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
    public static NamedBiFunction<Double, Double, Double> divide = new NamedBiFunction<>() {
        @Override
        public String name() {
            return "div";
        }

        @Override
        public Double apply(Double a, Double b) {
            if (b == 0) {
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
        if (args.size() != bifunctions.size() + 1) {
            throw new IllegalArgumentException("Number of Args or BiFunctions Don't Match");
        }
        T result = args.get(0);
        for (int i = 0; i < bifunctions.size(); i++) {
            result = bifunctions.get(i).apply(result, args.get(i + 1));
            args.set(i + 1, result);
        }
        return result;
    }


    public static void main(String... args) {

        System.out.println("BiFunction Tests:");
        double a = 9;
        double b = 9;
        System.out.println("A Value: " + a);
        System.out.println("B Value: " + b);
        System.out.println("Add Test (with name):      " + add.name() + "     " +  add.apply(a,b));
        System.out.println("Subtract Test (with name): " + subtract.name() + "    " + subtract.apply(a,b));
        System.out.println("Multiply Test (with name): " + multiply.name() + "     " + multiply.apply(a,b));
        System.out.println("Divide Test (with name):   " + divide.name() + "      " + divide.apply(a,b));
        try {
            System.out.println("Divide Test (divide by 0): " + divide.name() + "      " + divide.apply(a, 0.0));
        } catch (ArithmeticException e) {
            System.out.println("Caught ArithmeticException: " + e.getMessage());
        }
        System.out.println();

        System.out.println("Zip Tests:");
        List<Double> args1 = Arrays.asList(2.0, 3.0, 4.0);
        List<NamedBiFunction<Double, Double, Double>> bfs1 = Arrays.asList(subtract, multiply);
        System.out.println(zip(args1, bfs1));

        List<Double> args2 = Arrays.asList(4.0, 2.0, 1.0, 3.0);
        List<NamedBiFunction<Double, Double, Double>> bfs2 = Arrays.asList(multiply, divide, add);
        System.out.println(zip(args2, bfs2));

        List<Double> args3 = Arrays.asList(5.0, 2.0, 3.0, 1.0, 6.0);
        List<NamedBiFunction<Double, Double, Double>> bfs3 = Arrays.asList(add, divide, subtract, multiply);
        System.out.println(zip(args3, bfs3));

        try {
            List<Double> numbers1 = Arrays.asList(-0.5, -2d, -3d, 1d, 4d);
            List<NamedBiFunction<Double, Double, Double>> operations1 = Arrays.asList(multiply,multiply,multiply);
            Double d1 = zip(numbers1, operations1);
            System.out.println(d1);
        } catch (IllegalArgumentException ex) {
            System.out.println("An IllegalArgumentException was thrown: " + ex.getMessage());
        }

        System.out.println();
        System.out.println("Banarjee Tests:");
        List<Double> numbers = Arrays.asList(-0.5, 2d, 3d, 0d, 4d); // documentation example
        List<NamedBiFunction<Double, Double, Double>> operations = Arrays.asList(add,multiply,add,divide);
        Double d = zip(numbers, operations); // expected correct value: 1.125
        System.out.println(d);
        // different use case, not with NamedBiFunction objects

        List<String> strings = Arrays.asList("a","n","t");
        // note the syntax of this lambda expression
        BiFunction<String, String, String> concat = (s, t) -> s + t;
        String s = zip(strings, Arrays.asList(concat, concat)); // expected correct value: "ant"
        System.out.println(s);
    }
}
