package com.zoulshell.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

    private static Map<String, String> sourceMap = new HashMap<>();

    static {
        sourceMap.put("k1", "v1");
        sourceMap.put("k2", "v2");
        sourceMap.put("k3", "v3");
        sourceMap.put("k4", "v4");
        sourceMap.put("k5", "v5");
    }

    @Test
    public void putIfAbsent() {
        // if key is present, will not replace the value.
        sourceMap.putIfAbsent("k1", "v11");
        // if key is absent, will replace the value.
        sourceMap.putIfAbsent("k6", "v6");
        System.out.println(sourceMap);
    }

    @Test
    public void remove() {
        // remove element base on the key, return previous value.
        String preValue = sourceMap.remove("k1");
        // remove element base on both key and value, return a boolean result.
        boolean flag2 = sourceMap.remove("k2", "v22");
        System.out.println(preValue + "===" + flag2);
    }

    @Test
    public void getOrDefault() {
        System.out.println(sourceMap.getOrDefault("k1", "not exist: k1"));
        System.out.println(sourceMap.getOrDefault("k6", "not exist: k6"));
    }

    @Test
    public void compute() {

    }

    @Test
    public void computeIfAbsent() {
        sourceMap.computeIfAbsent("k1", v -> "computed value is: " + v);
        sourceMap.computeIfAbsent("k6", v -> "computed value is: " + v);
        System.out.println(sourceMap);
    }

    @Test
    public void computeIfPresent() {
        // if value is not null, it will be replaced.
        sourceMap.computeIfPresent("k1", (k, v) -> k + "===>" + v);
        System.out.println(sourceMap);

        // if value is not null and the computed result is null, the key will be removed and the result is null.
        sourceMap.computeIfPresent("k1", (k, v) -> null);
        System.out.println(sourceMap);

        // if value is null, the result is null too, otherwise the result will not change at all.
        sourceMap.computeIfPresent("k6", (k, v) -> k + "===>" + v);
        System.out.println(sourceMap);

        // if value is null, the result is null too.
        sourceMap.put("k2", null);
        sourceMap.computeIfPresent("k2", (k, v) -> k + "===>" + v);
        System.out.println(sourceMap);
    }
}
