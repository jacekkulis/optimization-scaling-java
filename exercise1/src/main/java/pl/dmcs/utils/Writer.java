package pl.dmcs.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Writer {

    private FileOutputStream fos;
    private ObjectOutputStream oos;

    public Writer() {

    }

    public void openFile(String path) {
        File file = new File(path);
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void serialize(Object object) {
        try {
            oos.writeObject(object);
            this.cleanUp();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cleanUp() throws IOException {
        fos.close();
        oos.close();
    }
}