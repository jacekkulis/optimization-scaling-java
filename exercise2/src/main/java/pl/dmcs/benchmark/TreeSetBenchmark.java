package pl.dmcs.benchmark;

import pl.dmcs.utils.Generator;
import pl.dmcs.utils.StopWatch;
import pl.dmcs.utils.Writer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetBenchmark {

    private final List<Integer> benchmarkIterations;
    private Set<String> treeSet;
    private SetCollectionMethods<String> methods;
    private StopWatch stopWatch;
    private Generator generator;
    private int size = 0;

    private List<Duration> addBench;
    private List<Duration> removeBench;
    private List<Duration> findBench;
    private List<Duration> containsBench;


    public TreeSetBenchmark(List<Integer> benchmarkIterations) {
        this.benchmarkIterations = benchmarkIterations;
        methods = new SetCollectionMethods<>();
        addBench = new ArrayList<>();
        removeBench = new ArrayList<>();
        findBench = new ArrayList<>();
        containsBench = new ArrayList<>();
        generator = new Generator();
    }

    void setUp(int size) {
        treeSet = null;
        treeSet = new TreeSet<>();
        this.size = size;
    }


    public void testHashSetAdd() {
        for (int i = 0; i < size; i++) {
            methods.testAdd(treeSet, generator.generateString());
            addBench.add(stopWatch.stop());
        }
    }

    public void testHashSetRemove() {
        for (int i = 0; i < size; i++) {
            methods.testRemove(treeSet, generator.generateString());
            removeBench.add(stopWatch.stop());
        }
    }

    public void testHashSetFind() {
        for (int i = 0; i < size; i++) {
            methods.testFind(treeSet, generator.generateString());

            findBench.add(stopWatch.stop());
        }
    }

    public void testHashSetContains() {
        for (int i = 0; i < size; i++) {
            methods.testContains(treeSet, generator.generateString());
            containsBench.add(stopWatch.stop());
        }
    }

    public void startBenchmark() {
        stopWatch = new StopWatch();

        for (Integer benchmarkIteration : benchmarkIterations) {
            setUp(benchmarkIteration);
            testHashSetAdd();
            testHashSetContains();
            testHashSetFind();
            testHashSetRemove();
            writeToFile();
        }
    }

    public void writeToFile() {
        Writer writer = new Writer();
        writer.createFile("benchmarksTreeSet.txt");
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
