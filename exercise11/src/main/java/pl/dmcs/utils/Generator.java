package pl.dmcs.utils;

import java.io.IOException;
import java.util.Random;

public class Generator {
    private Random rand;
    private Writer writer;

    public Generator() {
        this.rand = new Random();
        this.writer = new Writer();
    }

    public void generate(String path, int iterations) {
        writer.openFile(path);
        for (int i = 0; i < iterations; i++) {
            writer.write(String.valueOf(rand.nextDouble()) + "\n");
        }

        try {
            writer.cleanUp();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
