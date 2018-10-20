package pl.dmcs.utils;

import pl.dmcs.model.Person;
import pl.dmcs.model.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Reader {
    private FileInputStream fis;
    private ObjectInputStream ois;

    public Reader() {
    }

    public void openFile(String path) {
        File file = null;
        file = new File(path);
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserialize(Object obj) {
        try {
            if (obj instanceof Person) {
                Person person = (Person) ois.readObject();
                System.out.println(person.toString());
            } else if (obj instanceof User) {
                User person = (User) ois.readObject();
                System.out.println(person.toString());
            }

            this.cleanUp();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void customRead() {

    }

    public void cleanUp() throws IOException {
        fis.close();
        ois.close();
    }
}
