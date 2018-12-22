package pl.dmcs.benchmark;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

public class QueueCollectionMethods<T> {
    Random ran = new Random();

    public void testAddStart(ArrayDeque<T> collection, T item) {
        collection.addFirst(item);
    }

    public void testAddEnd(ArrayDeque<T> collection, T item) {
        collection.add(item);
    }

    public void testRemoveStart(ArrayDeque<T> collection) {
        collection.removeFirst();
    }

    public void testRemoveEnd(ArrayDeque<T> collection) {
        collection.removeLast();
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
