package pl.dmcs.cache;

import pl.dmcs.benchmark.DequeBenchmark;

import java.util.*;

public class QueueBenchDemo {

    public static void main(String[] args) {
        warmUp();

        DequeBenchmark dequeBenchmark = new DequeBenchmark(Arrays.asList(10, 100, 1000, 10000));
        dequeBenchmark.startBenchmark();
    }


    /**
     * twenty-five percent nulls
     */
    private static final List<Object> warmUp = new ArrayList<>();

    static void warmUp() {
        for (int i = 0; i < 1000000; i++) {
            warmUp.add(null);
        }
        for (int i = 0; i < 1000000; i++) {
            warmUp.add(String.valueOf(Math.pow(2, 5)));
        }
        Collections.shuffle(warmUp, new Random(0));
    }
}
