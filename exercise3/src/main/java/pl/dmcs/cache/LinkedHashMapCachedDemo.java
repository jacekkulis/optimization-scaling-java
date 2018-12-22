package pl.dmcs.cache;

import java.util.Map;

public class LinkedHashMapCachedDemo {
    public static void main(String... args) {
        Map<Integer, String> cachedMap = new LinkedHashMapCached<>(3);
        cachedMap.put(1, "Object1");
        cachedMap.put(2, "Object2");
        System.out.println(cachedMap);
        cachedMap.put(3, "Object3");
        System.out.println(cachedMap);
        cachedMap.put(4, "Object4");
        System.out.println(cachedMap);
    }
}
