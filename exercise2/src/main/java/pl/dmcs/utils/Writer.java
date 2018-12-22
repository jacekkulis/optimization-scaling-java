package pl.dmcs.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Writer {
    private BufferedWriter out;

    public Writer() {

    }

    public void createFile(String path) {
        try {
            out = Files.newBufferedWriter(Paths.get(path), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String s) {
        try {
            out.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}