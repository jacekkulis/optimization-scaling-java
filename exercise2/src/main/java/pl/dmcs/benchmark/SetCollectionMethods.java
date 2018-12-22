package pl.dmcs.benchmark;

import java.util.Collection;

public class SetCollectionMethods<T> {
    public void testAdd(Collection<T> collection, T item) {
        collection.add(item);
    }

    public void testRemove(Collection<T> collection, T item) {
        collection.remove(item);
    }

    public T testFind(Collection<T> collection, T item) {
        return collection.stream().filter(item::equals).findFirst().orElse(null);
    }

    public boolean testContains(Collection<T> collection, T item) {
        return collection.contains(item);
    }
}
