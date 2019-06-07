import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JacksonBenchmark extends Benchmark {

    @Override
    protected Long runBenchmark(List<Person> persons) {
        long begin = System.currentTimeMillis();
        try {
            new ObjectMapper().writeValueAsString(persons);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis() - begin;
    }
}
