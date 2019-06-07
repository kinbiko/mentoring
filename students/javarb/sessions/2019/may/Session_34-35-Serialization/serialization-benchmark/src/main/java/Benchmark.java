import java.util.ArrayList;
import java.util.List;

public abstract class Benchmark {
    public void benchmark(int samplesize, int objects) {
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < objects; i++) {
            Person person = new Person();
            person.setId(i);
            person.setName("Name_" + i);
            person.setAge(i % 100);
            persons.add(i, person);
        }

        List<Long> results = new ArrayList<>();

        for (int i = 0; i < samplesize; i++) {
            results.add(runBenchmark(persons));
        }

        long mean = mean(results);
        System.out.println("Result of " + getClass().getSimpleName() + " = "+ mean + " ms");

    }

    protected abstract Long runBenchmark(List<Person> persons);

    private long mean(List<Long> benchmarks) {
        long sum = 0;
        for (Long benchmark : benchmarks) {
            sum += benchmark;
        }
        return sum / benchmarks.size();
    }
}
