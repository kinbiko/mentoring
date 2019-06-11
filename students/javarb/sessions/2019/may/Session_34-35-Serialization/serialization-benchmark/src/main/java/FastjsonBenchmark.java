import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class FastjsonBenchmark extends Benchmark {

    @Override
    protected Long runBenchmark(List<Person> people) {
        long begin = System.currentTimeMillis();
        JSON.toJSONString(people);
        return System.currentTimeMillis() - begin;
    }

}
