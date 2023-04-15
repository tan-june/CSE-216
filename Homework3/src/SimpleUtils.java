import java.util.*;
import java.util.stream.Collectors;

public class SimpleUtils {
    /**
     * Find and return the least element from a collection of given elements that are comparable.
     *
     * @param items:     the given collection of elements
     * @param from_start a <code>boolean</code> flag that decides how ties are broken.
     *                   If <code>true</code>, the element encountered earlier in the
     *                   iteration is returned, otherwise the later element is returned.
     * @param <T>:       the type parameter of the collection (i.e., the items are all of type T).
     * @return the least element in <code>items</code>, where ties are
     * broken based on <code>from_start</code>.
     */
    public static <T extends Comparable<T>> T least(Collection<T> items, boolean from_start) {
        return items.stream()
                .reduce((element1, element2) -> from_start == true ?
                          element1.compareTo(element2) < 0  ? element1 : element2   //condition one (if)
                        : element1.compareTo(element2) <= 0 ? element1 : element2) //condition two (else)
                .orElseThrow(() -> new IllegalArgumentException("Collection is empty."));
    }

    /**
     * Flattens a map to a list of <code>String</code>s, where each element in the list is formatted
     * as "key -> value" (i.e., each key-value pair is converted to a string in this specific format).
     *
     * @param aMap the specified input map.
     * @param <K> the type parameter of keys in <code>aMap</code>.
     * @param <V> the type parameter of values in <code>aMap</code>.
     * @return the flattened list representation of <code>aMap</code>.
     */
    public static <K, V> List<String> flatten(Map<K, V> aMap) {
        return aMap.entrySet().stream()
                .map(element -> element.getKey() + " -> " + element.getValue())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static void main(String[] args){
        System.out.println("Least Value:");
        List<Integer> intList = Arrays.asList(3, 4, 5, 9, 2, 6, 100, 23, 25, 5, 3, 5);
        int leastInt = SimpleUtils.least(intList, true);
        System.out.println("Value Should Be (2):       " + leastInt);

        List<Double> doubleList = Arrays.asList(3.14, 2.71, 1.618, 1.414, 0.577, 0.001);
        double leastDouble = SimpleUtils.least(doubleList, true);
        System.out.println("Value Should Be (0.001):   " + leastDouble);

        List<String> strList = Arrays.asList("bob", "ana", "ab", "hector", "tanmay", "nicolle");
        String leastStr = SimpleUtils.least(strList, true);
        System.out.println("Value Should Be ('ab'):    " + leastStr);
        System.out.println();


        System.out.println("Flatten List:");
        Map<Double, Boolean> doubleAndBoolean = new HashMap<>();
        doubleAndBoolean.put(3.14, true);
        doubleAndBoolean.put(2.71, true);
        doubleAndBoolean.put(58.0, true);
        doubleAndBoolean.put(19.2, false);
        doubleAndBoolean.put(36.2, false);
        doubleAndBoolean.put(587.2, false);
        doubleAndBoolean.put(68.2, true);
        List<String> result1 = SimpleUtils.flatten(doubleAndBoolean);
        System.out.println(result1);

        Map<Integer, Double> IntegerandDouble = new HashMap<>();
        IntegerandDouble.put(10, 1.14);
        IntegerandDouble.put(20, 2.14);
        IntegerandDouble.put(30, 3.14);
        IntegerandDouble.put(40, 4.14);
        IntegerandDouble.put(50, 5.14);
        IntegerandDouble.put(60, 6.14);
        List<String> result2 = SimpleUtils.flatten(IntegerandDouble);
        System.out.println(result2);

        Map<String, Boolean> StringandBoolean = new HashMap<>();
        StringandBoolean.put("ABC", true);
        StringandBoolean.put("bob", false);
        StringandBoolean.put("tanmay", true);
        StringandBoolean.put("kelly", false);
        StringandBoolean.put("chloe", true);
        StringandBoolean.put("robin", false);
        List<String> result3 = SimpleUtils.flatten(StringandBoolean);
        System.out.println(result3);

        Map<String, String> StringandString = new HashMap<>();
        StringandString.put("ABC", "a");
        StringandString.put("bob", "b");
        StringandString.put("tanmay", "t");
        StringandString.put("kelly", "k");
        StringandString.put("chloe", "c");
        StringandString.put("robin", "r");
        StringandString.put("barney", "d");
        List<String> result4 = SimpleUtils.flatten(StringandString);
        System.out.println(result4);
    }
}
