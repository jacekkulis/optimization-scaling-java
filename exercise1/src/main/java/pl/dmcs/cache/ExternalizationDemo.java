package pl.dmcs.cache;

import pl.dmcs.model.User;
import pl.dmcs.utils.Reader;
import pl.dmcs.utils.Writer;

import java.util.Date;

public class ExternalizationDemo {
    private final static String path = "externalization.txt";

    public static void main(String[] args) {
        User person = new User("Jacek", "Kulis", new Date(), 21);

        Writer writer = new Writer();
        writer.openFile(path);
        writer.serialize(person);

        Reader reader = new Reader();
        reader.openFile(path);
        reader.deserialize(person);

    }
}
