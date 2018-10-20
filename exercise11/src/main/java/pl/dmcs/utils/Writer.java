package pl.dmcs.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    private BufferedWriter out;

    public Writer() {

    }

    public void openFile(String path) {
        try {
            out = new BufferedWriter(new FileWriter(path));
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

    public void cleanUp() throws IOException {
        out.flush();
        out.close();
    }
}