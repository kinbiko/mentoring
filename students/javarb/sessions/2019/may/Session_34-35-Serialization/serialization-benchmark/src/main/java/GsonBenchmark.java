import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class GsonBenchmark extends Benchmark {

    @Override
    protected Long runBenchmark(List<Person> people){
        long begin = System.currentTimeMillis();
        new Gson().toJson(people);
        return System.currentTimeMillis() - begin;
    }

}
