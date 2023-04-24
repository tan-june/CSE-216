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
                .reduce((element1, element2) -> from_start ?
                          element1.compareTo(element2) < 0  ? element1 : element2   //condition one (if)
                        : element1.compareTo(element2) <= 0 ? element1 : element2) //condition two (else)
                .orElseThrow(null);
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
        System.out.println(intList);
        System.out.println("Value Should Be (2):       " + leastInt);

        System.out.println();
        List<Double> doubleList = Arrays.asList(3.14, 2.71, 1.618, 1.414, 0.577, 0.001);
        double leastDouble = SimpleUtils.least(doubleList, true);
        System.out.println(doubleList);
        System.out.println("Value Should Be (0.001):   " + leastDouble);

        System.out.println();
        List<String> strList = Arrays.asList("bob", "ana", "ab", "hector", "tanmay", "nicolle");
        String leastStr = SimpleUtils.least(strList, true);
        System.out.println(strList);
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
        System.out.println(doubleAndBoolean);
        List<String> result1 = SimpleUtils.flatten(doubleAndBoolean);
        System.out.println(result1);

        Map<Integer, Double> map = new HashMap<>();
        map.put(10, 1.14);
        map.put(20, 2.14);
        map.put(30, 3.14);
        map.put(40, 4.14);
        map.put(50, 5.14);
        map.put(60, 6.14);
        System.out.println();
        System.out.println(map);
        List<String> result2 = SimpleUtils.flatten(map);
        System.out.println(result2);

        Map<String, Boolean> map2 = new HashMap<>();
        map2.put("ABC", true);
        map2.put("bob", false);
        map2.put("tanmay", true);
        map2.put("kelly", false);
        map2.put("chloe", true);
        map2.put("robin", false);
        System.out.println();
        System.out.println(map2);
        List<String> result3 = SimpleUtils.flatten(map2);
        System.out.println(result3);

        Map<String, String> k = new HashMap<>();
        k.put("ABC", "a");
        k.put("bob", "b");
        k.put("tanmay", "t");
        k.put("kelly", "k");
        k.put("chloe", "c");
        k.put("robin", "r");
        k.put("barney", "d");
        System.out.println();
        System.out.println(k);
        List<String> result4 = SimpleUtils.flatten(k);
        System.out.println(result4);
    }
}
