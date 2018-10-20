package pl.dmcs.utils;

import java.time.Duration;
import java.time.Instant;

public class StopWatch implements IStopWatch {
    private Duration time;
    private Instant start;
    private Instant stop;

    public StopWatch() {
        start();
    }

    @Override
    public void start() {
        start = Instant.now();
    }

    @Override
    public Duration stop() {
        stop = Instant.now();
        time = Duration.between(start, stop);
        return time;
    }

    @Override
    public void reset() {
        start = null;
        time = null;
        start();
    }


}
