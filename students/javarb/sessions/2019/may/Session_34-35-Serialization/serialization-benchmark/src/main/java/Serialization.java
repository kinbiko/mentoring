public class Serialization {

    public static void main(String[] args) {
        new JacksonBenchmark().benchmark(150, 200000);
        new GsonBenchmark().benchmark(150, 200000);
        new FastjsonBenchmark().benchmark(150, 200000);
    }

}
