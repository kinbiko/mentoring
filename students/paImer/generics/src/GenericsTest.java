import org.junit.Test;

import java.util.*;

public class GenericsTest {
    @Test
    public void testLists() {
        List<String> countries = new ArrayList<>();
        countries.add("England");
        countries.add("Norway");
        countries.add("Japan");
        countries.add("Japan");
        for (String country : countries) {
            System.out.println(country);
        }
    }

    @Test
    public void testSets() {
        Set<String> countries = new HashSet<>();
        countries.add("England");
        countries.add("Norway");
        countries.add("Japan");
        countries.add("Japan");
        for (String country : countries) {
            System.out.println(country);
        }
    }

    @Test
    public void testMaps() {
        Map<String, Integer> countries = new HashMap<>();
        countries.put("England", 0);
        countries.put("Norway", 1);
        countries.put("Japan", 2);
        countries.put("Japan", 3);
        for (String country : countries.keySet()) {
            System.out.println(country + ", " + countries.get(country));
        }
    }

    @Test
    public void testTuple() {
        Tuple<String, Map<String, Integer>> tuple = new Tuple<>();
        tuple.setFirst("Hello");

        Map<String, Integer> countries = new HashMap<>();
        countries.put("England", 0);
        countries.put("Norway", 1);
        countries.put("Japan", 3);

        tuple.setSecond(countries);
        System.out.println(tuple);
    }
}

class Tuple<F, S> {
    private F first;
    private S second;

    public F getFirst() {
        return first;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public S getSecond() {
        return second;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
