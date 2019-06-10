import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class FastjsonBenchmark extends Benchmark {

    @Override
    protected Long runBenchmark(List<Object> persons) {
        long begin = System.currentTimeMillis();
        JSON.toJSONString(persons);
        return System.currentTimeMillis() - begin;
    }

}
