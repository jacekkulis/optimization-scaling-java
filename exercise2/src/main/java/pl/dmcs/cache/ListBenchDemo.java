package pl.dmcs.cache;

import pl.dmcs.benchmark.ArrayListBenchmark;
import pl.dmcs.benchmark.LinkedListBenchmark;

import java.util.*;

public class ListBenchDemo {

    public static void main(String[] args) {
        warmUp();

        LinkedListBenchmark linkedListBenchmark = new LinkedListBenchmark(Arrays.asList(1000, 5000, 10000, 20000));
        linkedListBenchmark.startBenchmark();

        ArrayListBenchmark arrayListBenchmark = new ArrayListBenchmark(Arrays.asList(1000, 5000, 10000, 20000));
        arrayListBenchmark.startBenchmark();
    }


    private static final List<Object> warmUp = new ArrayList<>();

    private static void warmUp() {
        for (int i = 0; i < 1000000; i++) {
            warmUp.add(null);
        }
        for (int i = 0; i < 1000000; i++) {
            warmUp.add(String.valueOf(Math.pow(2, 5)));
        }
        Collections.shuffle(warmUp, new Random(0));
    }
}
