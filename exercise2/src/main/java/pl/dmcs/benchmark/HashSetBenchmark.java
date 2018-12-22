package pl.dmcs.benchmark;


import pl.dmcs.utils.Generator;
import pl.dmcs.utils.StopWatch;
import pl.dmcs.utils.Writer;

import java.time.Duration;
import java.util.*;

public class HashSetBenchmark {

    private final List<Integer> benchmarkIterations;
    private Set<String> hashSet;
    private SetCollectionMethods<String> methods;
    private StopWatch stopWatch;
    private Generator generator;
    private int size = 0;

    private List<Duration> addBench;
    private List<Duration> removeBench;
    private List<Duration> findBench;
    private List<Duration> containsBench;


    public HashSetBenchmark(List<Integer> benchmarkIterations) {
        this.benchmarkIterations = benchmarkIterations;
        methods = new SetCollectionMethods<>();
        addBench = new ArrayList<>();
        removeBench = new ArrayList<>();
        findBench = new ArrayList<>();
        containsBench = new ArrayList<>();
        generator = new Generator();
    }

    void setUp(int size) {
        hashSet = null;
        hashSet = new HashSet<>(size);
        this.size = size;
    }


    public void testHashSetAdd() {
        for (int i = 0; i < size; i++) {
            methods.testAdd(hashSet, generator.generateString());
            addBench.add(stopWatch.stop());
        }
    }

    public void testHashSetRemove() {
        for (int i = 0; i < size; i++) {
            methods.testRemove(hashSet, generator.generateString());
            removeBench.add(stopWatch.stop());
        }
    }

    public void testHashSetFind() {
        for (int i = 0; i < size; i++) {
            methods.testFind(hashSet, generator.generateString());

            findBench.add(stopWatch.stop());
        }
    }

    public void testHashSetContains() {
        for (int i = 0; i < size; i++) {
            methods.testContains(hashSet, generator.generateString());
            containsBench.add(stopWatch.stop());
        }
    }

    public void startBenchmark() {
        stopWatch = new StopWatch();

        for (Integer benchmarkIteration : benchmarkIterations) {
            setUp(benchmarkIteration);
            testHashSetAdd();

            System.out.println(Arrays.asList(hashSet.toArray()));
            Iterator<String> it = hashSet.iterator();
            String value = "";
            int i = 0;
            while (it.hasNext()) {
                value = it.next();
                if (i == 2) {
                    System.out.println(value);
                    break;
                }
                i++;
            }

//            testHashSetContains();
//            testHashSetFind();
//            testHashSetRemove();
//            writeToFile();
        }
    }

    public void writeToFile() {
        Writer writer = new Writer();
        writer.createFile("benchmarksHashSet.txt");
        writer.write("Adding, size = " + size + "\n");
        writer.write(String.format("%-50s %-20s ms\n", "[Avg]", addBench.stream().mapToLong(Duration::toMillis).average().getAsDouble()));
        writer.write("Adding end ###################\n");
        writer.write("Contains, size = " + size + "\n");
        writer.write(String.format("%-50s %-20s ms\n", "[Avg]", containsBench.stream().mapToLong(Duration::toMillis).average().getAsDouble()));
        writer.write("Contains end ###################\n");
        writer.write("Find, size = " + size + "\n");
        writer.write(String.format("%-50s %-20s ms\n", "[Avg]", findBench.stream().mapToLong(Duration::toMillis).average().getAsDouble()));
        writer.write("Find end ###################\n");
        writer.write("Remove, size = " + size + "\n");
        writer.write(String.format("%-50s %-20s ms\n", "[Avg]", removeBench.stream().mapToLong(Duration::toMillis).average().getAsDouble()));
        writer.write("Remove end ###################\n");
        writer.close();
    }
}
