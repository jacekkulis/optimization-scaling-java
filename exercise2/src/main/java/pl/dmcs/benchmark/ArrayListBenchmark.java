package pl.dmcs.benchmark;

import pl.dmcs.utils.Generator;
import pl.dmcs.utils.StopWatch;
import pl.dmcs.utils.Writer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ArrayListBenchmark {

    private final List<Integer> benchmarkIterations;
    private List<String> list;
    private ListCollectionMethods<String> methods;
    private StopWatch stopWatch;
    private Generator generator;
    private int size = 0;

    private List<Duration> addStartBench;
    private List<Duration> addEndbench;
    private List<Duration> addRandbench;

    private List<Duration> removeStartBench;
    private List<Duration> removeEndbench;
    private List<Duration> removeRandbench;

    private List<Duration> findIndexBench;
    private List<Duration> findIteratorBench;


    public ArrayListBenchmark(List<Integer> benchmarkIterations) {
        this.benchmarkIterations = benchmarkIterations;
        methods = new ListCollectionMethods<>();
        addStartBench = new ArrayList<>();
        addEndbench = new ArrayList<>();
        addRandbench = new ArrayList<>();

        removeStartBench = new ArrayList<>();
        removeEndbench = new ArrayList<>();
        removeRandbench = new ArrayList<>();

        findIndexBench = new ArrayList<>();
        findIteratorBench = new ArrayList<>();

        generator = new Generator();
    }

    void setUp(int size) {
        list = null;
        list = new ArrayList<>(size);
        this.size = size;
    }

    public void testAddStart() {
        for (int i = 0; i < size; i++) {
            methods.testAddStart(list, generator.generateString());
            addStartBench.add(stopWatch.stop());
        }
    }

    public void testAddEnd() {
        for (int i = 0; i < size; i++) {
            methods.testAddEnd(list, generator.generateString());
            addEndbench.add(stopWatch.stop());
        }
    }

    public void testAddRandom() {
        for (int i = 0; i < size; i++) {
            methods.testAddRandom(list, generator.generateString());
            addRandbench.add(stopWatch.stop());
        }
    }

    public void testRemoveStart() {
        for (int i = 0; i < size; i++) {
            methods.testRemoveStart(list);
            removeStartBench.add(stopWatch.stop());
        }
    }

    public void testRemoveEnd() {
        for (int i = 0; i < size; i++) {
            methods.testRemoveEnd(list);
            removeEndbench.add(stopWatch.stop());
        }
    }

    public void testRemoveRandom() {
        for (int i = 0; i < size; i++) {
            methods.testRemoveEnd(list);
            removeRandbench.add(stopWatch.stop());
        }
    }

    public void testFindIndex() {
        for (int i = 0; i < size; i++) {
            methods.testFindIndex(list, generator.generateString());
            findIndexBench.add(stopWatch.stop());
        }
    }

    public void testFindIterator() {
        for (int i = 0; i < size; i++) {
            methods.testFindIterator(list, generator.generateString());
            findIteratorBench.add(stopWatch.stop());
        }
    }

    public void startBenchmark() {
        stopWatch = new StopWatch();

        for (Integer benchmarkIteration : benchmarkIterations) {
            setUp(benchmarkIteration);

            testAddStart();
            testAddEnd();
            testAddRandom();
            testRemoveStart();
            testRemoveEnd();
            testRemoveRandom();
            testFindIndex();
            testFindIterator();

            writeToFile();
        }
    }

    public void writeToFile() {
        Writer writer = new Writer();
        writer.createFile("benchmarksArrayList.txt");

        writer.write("Adding start, size = " + size + "\n");
        writer.write(String.format("%-50s %-20s ms\n", "[Avg]", addStartBench.stream().mapToLong(Duration::toMillis).average().getAsDouble()));
        writer.write("Adding end, size = " + size + "\n");
        writer.write(String.format("%-50s %-20s ms\n", "[Avg]", addEndbench.stream().mapToLong(Duration::toMillis).average().getAsDouble()));
        writer.write("Adding rand, size = " + size + "\n");
        writer.write(String.format("%-50s %-20s ms\n", "[Avg]", addRandbench.stream().mapToLong(Duration::toMillis).average().getAsDouble()));

        writer.write("Removing start, size = " + size + "\n");
        writer.write(String.format("%-50s %-20s ms\n", "[Avg]", removeStartBench.stream().mapToLong(Duration::toMillis).average().getAsDouble()));
        writer.write("Removing end, size = " + size + "\n");
        writer.write(String.format("%-50s %-20s ms\n", "[Avg]", removeEndbench.stream().mapToLong(Duration::toMillis).average().getAsDouble()));
        writer.write("Removing rand, size = " + size + "\n");
        writer.write(String.format("%-50s %-20s ms\n", "[Avg]", removeRandbench.stream().mapToLong(Duration::toMillis).average().getAsDouble()));

        writer.write("Find index, size = " + size + "\n");
        writer.write(String.format("%-50s %-20s ms\n", "[Avg]", findIndexBench.stream().mapToLong(Duration::toMillis).average().getAsDouble()));

        writer.write("Find iterator, size = " + size + "\n");
        writer.write(String.format("%-50s %-20s ms\n", "[Avg]", findIteratorBench.stream().mapToLong(Duration::toMillis).average().getAsDouble()));

        writer.close();
    }
}
