import java.util.*;
import java.util.stream.Collectors;

public class SimpleUtils {

    public static <T extends Comparable<T>> T least(Collection<T> items, boolean from_start) {
        return items.stream()
                .reduce((a, b) -> from_start ? a.compareTo(b) < 0 ? a : b
                        : a.compareTo(b) <= 0 ? a : b)
                .orElseThrow(NoSuchElementException::new);
    }

    public static <K, V> List<String> flatten(Map<K, V> aMap) {
        return aMap.entrySet().stream()
                .map(e -> e.getKey() + " -> " + e.getValue())
                .collect(Collectors.toList());
    }

}
