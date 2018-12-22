package pl.dmcs.cache;

import pl.dmcs.utils.Generator;
import pl.dmcs.utils.IStopWatch;
import pl.dmcs.utils.Reader;
import pl.dmcs.utils.StopWatch;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class FileAccessDemo {
    public static String fileName = "generatedFile.txt";

    private static List<Duration> mainExcTimes;
    private static List<Duration> generateExcTimes;
    private static List<Duration> readBufferedExcTimes;
    private static List<Duration> readNioExcTimes;
    private static List<Duration> readMappedExcTimes;

    public static void main(String[] args) {
        mainExcTimes = new ArrayList<>();
        generateExcTimes = new ArrayList<>();
        readBufferedExcTimes = new ArrayList<>();
        readNioExcTimes = new ArrayList<>();
        readMappedExcTimes = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            IStopWatch stopWatch = new StopWatch();
            generateAndRun();
            mainExcTimes.add(stopWatch.stop());
        }

        System.out.printf("%-50s %-20s ms\n", "[Avg] Generation: ", generateExcTimes.stream().mapToLong(Duration::toMillis).average());
        System.out.printf("%-50s %-20s ms\n", "[Max] Generation: ", generateExcTimes.stream().mapToLong(Duration::toMillis).max());
        System.out.printf("%-50s %-20s ms\n", "[Min] Generation: ", generateExcTimes.stream().mapToLong(Duration::toMillis).min());
        System.out.println("################");
        System.out.printf("%-50s %-20s ms\n", "[Avg] BufferedRead: ", readBufferedExcTimes.stream().mapToLong(Duration::toMillis).average());
        System.out.printf("%-50s %-20s ms\n", "[Max] BufferedRead: ", readBufferedExcTimes.stream().mapToLong(Duration::toMillis).max());
        System.out.printf("%-50s %-20s ms\n", "[Min] BufferedRead: ", readBufferedExcTimes.stream().mapToLong(Duration::toMillis).min());
        System.out.println("################");
        System.out.printf("%-50s %-20s ms\n", "[Avg] NIO: ", readNioExcTimes.stream().mapToLong(Duration::toMillis).average());
        System.out.printf("%-50s %-20s ms\n", "[Max] NIO: ", readNioExcTimes.stream().mapToLong(Duration::toMillis).max());
        System.out.printf("%-50s %-20s ms\n", "[Min] NIO: ", readNioExcTimes.stream().mapToLong(Duration::toMillis).min());
        System.out.println("################");
        System.out.printf("%-50s %-20s ms\n", "[Avg] Mapped memory: ", readMappedExcTimes.stream().mapToLong(Duration::toMillis).average());
        System.out.printf("%-50s %-20s ms\n", "[Max] Mapped memory: ", readMappedExcTimes.stream().mapToLong(Duration::toMillis).max());
        System.out.printf("%-50s %-20s ms\n", "[Min] Mapped memory: ", readMappedExcTimes.stream().mapToLong(Duration::toMillis).min());
        System.out.println("################");
        System.out.printf("%-50s %-20s ms\n", "[Avg] Total execution: ", mainExcTimes.stream().mapToLong(Duration::toMillis).average());
        System.out.printf("%-50s %-20s ms\n", "[Max] Total execution: ", mainExcTimes.stream().mapToLong(Duration::toMillis).max());
        System.out.printf("%-50s %-20s ms\n", "[Min] Total execution: ", mainExcTimes.stream().mapToLong(Duration::toMillis).min());
    }


    public static void generateAndRun() {
        IStopWatch stopWatch = new StopWatch();
        Generator generator = new Generator();
        generator.generate(fileName, 556555);
        generateExcTimes.add(stopWatch.stop());

        Reader reader = new Reader(fileName);
        stopWatch.reset();
        reader.bufferedReader();
        readBufferedExcTimes.add(stopWatch.stop());

        stopWatch.reset();
        reader.nioRead();
        readNioExcTimes.add(stopWatch.stop());

        stopWatch.reset();
        reader.mappedRead();
        readMappedExcTimes.add(stopWatch.stop());
    }
}
