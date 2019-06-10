import java.util.ArrayList;
import java.util.List;

public abstract class Benchmark {
    public void benchmark(Configuration config) {
        List<Object> objects = new ArrayList<>();

        for (int i = 0; i < config.getObjects(); i++) {
            objects.add(i, config.getPojo());

        }

        List<Long> results = new ArrayList<>();

        for (int i = 0; i < config.getSamplesize(); i++) {
            results.add(runBenchmark(objects));
        }

        long mean = mean(results);
        System.out.println(config.getUseCase() + ": Result of " + getClass().getSimpleName() + " = "+ mean + " ms");

    }

    protected abstract Long runBenchmark(List<Object> objects);

    private long mean(List<Long> benchmarks) {
        long sum = 0;
        for (Long benchmark : benchmarks) {
            sum += benchmark;
        }
        return sum / benchmarks.size();
    }
}
