import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class GsonBenchmark extends Benchmark {

    @Override
    protected Long runBenchmark(List<Person> persons){
        long begin = System.currentTimeMillis();
        new Gson().toJson(persons);
        return System.currentTimeMillis() - begin;
    }

}
