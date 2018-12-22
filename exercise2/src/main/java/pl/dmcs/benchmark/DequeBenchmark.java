package pl.dmcs.benchmark;

import pl.dmcs.utils.Generator;
import pl.dmcs.utils.StopWatch;
import pl.dmcs.utils.Writer;

import java.time.Duration;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class DequeBenchmark {

    private final List<Integer> benchmarkIterations;
    private ArrayDeque<String> queue;
    private QueueCollectionMethods<String> methods;
    private StopWatch stopWatch;
    private Generator generator;
    private int size = 0;

    private List<Duration> addStartBench;
    private List<Duration> addEndbench;

    private List<Duration> removeStartBench;
    private List<Duration> removeEndbench;

    private List<Duration> findIndexBench;
    private List<Duration> findIteratorBench;


    public DequeBenchmark(List<Integer> benchmarkIterations) {
        this.benchmarkIterations = benchmarkIterations;
        methods = new QueueCollectionMethods<>();
        addStartBench = new ArrayList<>();
        addEndbench = new ArrayList<>();

        removeStartBench = new ArrayList<>();
        removeEndbench = new ArrayList<>();

        findIndexBench = new ArrayList<>();
        findIteratorBench = new ArrayList<>();

        generator = new Generator();
    }

    void setUp(int size) {
        queue = null;
        queue = new ArrayDeque<>();
        this.size = size;
    }

    public void testAddStart() {
        for (int i = 0; i < size; i++) {
            methods.testAddStart(queue, generator.generateString());
            addStartBench.add(stopWatch.stop());
        }
    }

    public void testAddEnd() {
        for (int i = 0; i < size; i++) {
            methods.testAddEnd(queue, generator.generateString());
            addEndbench.add(stopWatch.stop());
        }
    }

    public void testRemoveStart() {
        for (int i = 0; i < size; i++) {
            methods.testRemoveStart(queue);
            removeStartBench.add(stopWatch.stop());
        }
    }

    public void testRemoveEnd() {
        for (int i = 0; i < size; i++) {
            methods.testRemoveEnd(queue);
            removeEndbench.add(stopWatch.stop());
        }
    }

    public void testFindIndex() {
        for (int i = 0; i < size; i++) {
            methods.testFindIndex(queue, generator.generateString());
            findIndexBench.add(stopWatch.stop());
        }
    }

    public void testFindIterator() {
        for (int i = 0; i < size; i++) {
            methods.testFindIterator(queue, generator.generateString());
            findIteratorBench.add(stopWatch.stop());
        }
    }

    public void startBenchmark() {
        stopWatch = new StopWatch();

        for (Integer benchmarkIteration : benchmarkIterations) {
            setUp(benchmarkIteration);

            testAddStart();
            testAddEnd();
            testRemoveStart();
            testRemoveEnd();
            testFindIndex();
            testFindIterator();

            writeToFile();
        }
    }

    public void writeToFile() {
        Writer writer = new Writer();
        writer.createFile("benchmarksQueue.txt");

        writer.write("Adding start, size = " + size + "\n");
        writer.write(String.format("%-50s %-20s ms\n", "[Avg]", addStartBench.stream().mapToLong(Duration::toMillis).average().getAsDouble()));
        writer.write("Adding end, size = " + size + "\n");
        writer.write(String.format("%-50s %-20s ms\n", "[Avg]", addEndbench.stream().mapToLong(Duration::toMillis).average().getAsDouble()));

        writer.write("Removing start, size = " + size + "\n");
        writer.write(String.format("%-50s %-20s ms\n", "[Avg]", removeStartBench.stream().mapToLong(Duration::toMillis).average().getAsDouble()));
        writer.write("Removing end, size = " + size + "\n");
        writer.write(String.format("%-50s %-20s ms\n", "[Avg]", removeEndbench.stream().mapToLong(Duration::toMillis).average().getAsDouble()));

        writer.write("Find index, size = " + size + "\n");
        writer.write(String.format("%-50s %-20s ms\n", "[Avg]", findIndexBench.stream().mapToLong(Duration::toMillis).average().getAsDouble()));

        writer.write("Find iterator, size = " + size + "\n");
        writer.write(String.format("%-50s %-20s ms\n", "[Avg]", findIteratorBench.stream().mapToLong(Duration::toMillis).average().getAsDouble()));

        writer.close();
    }
}
