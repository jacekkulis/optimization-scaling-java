package pl.dmcs.demo;

import pl.dmcs.model.Person;
import pl.dmcs.utils.Reader;
import pl.dmcs.utils.Writer;

import java.util.Date;

public class SerializationDemo {
    private final static String path = "serialization.txt";

    public static void main(String[] args) {
        Person person = new Person("Jacek", "Kulis", new Date(), 21);

        Writer writer = new Writer();
        writer.openFile(path);
        writer.serialize(person);

        Reader reader = new Reader();
        reader.openFile(path);
        reader.deserialize(person);
    }
}
