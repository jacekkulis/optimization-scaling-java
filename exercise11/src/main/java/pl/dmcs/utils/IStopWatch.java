package pl.dmcs.utils;

import java.time.Duration;

public interface IStopWatch {
    void start();

    Duration stop();

    void reset();
}
