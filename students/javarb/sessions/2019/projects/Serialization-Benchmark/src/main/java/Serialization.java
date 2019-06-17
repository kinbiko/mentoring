public class Serialization {

    public static void main(String[] args) {

        for (Configuration configuration : ConfigurationFactory.getConfigurations()) {
            new JacksonBenchmark().benchmark(configuration);
            new GsonBenchmark().benchmark(configuration);
            new FastjsonBenchmark().benchmark(configuration);
        }

    }

}
