package pl.dmcs.cache;

import pl.dmcs.benchmark.HashSetBenchmark;

import java.util.*;

public class SetBenchDemo {

    public static void main(String[] args) {
        warmUp();

//        TreeSetBenchmark treeSetBenchmark = new TreeSetBenchmark(Arrays.asList(100, 1000, 10000, 100000));
//        treeSetBenchmark.startBenchmark();
//
//        HashSetBenchmark hashSetBenchmark = new HashSetBenchmark(Arrays.asList(100, 1000, 10000, 100000));
//        hashSetBenchmark.startBenchmark();


        HashSetBenchmark hashSetBenchmark1 = new HashSetBenchmark(Arrays.asList(100));
        hashSetBenchmark1.startBenchmark();
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
            warmUp.add(String.valueOf(Math.pow(2, 2)));
        }
        Collections.shuffle(warmUp, new Random(0));
    }
}
