package pl.dmcs.benchmark;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ListCollectionMethods<T> {
    Random ran = new Random();

    public void testAddStart(List<T> collection, T item) {
        collection.add(0, item);
    }

    public void testAddEnd(List<T> collection, T item) {
        collection.add(collection.size() - 1, item);
    }

    public void testAddRandom(List<T> collection, T item) {
        collection.add(ran.nextInt(collection.size()), item);
    }

    public void testRemoveStart(List<T> collection) {
        collection.remove(0);
    }

    public void testRemoveEnd(List<T> collection) {
        collection.remove(collection.size() - 1);
    }

    public void testRemoveRandom(List<T> collection) {
        collection.remove(ran.nextInt(collection.size()));
    }

    public T testFindIndex(Collection<T> collection, T item) {
        return collection.stream().filter(item::equals).findFirst().orElse(null);
    }

    public T testFindIterator(Collection<T> collection, T item) {
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (iterator.equals(iterator.next())) {
                return item;
            }
        }

        return collection.stream().filter(item::equals).findFirst().orElse(null);
    }
}
