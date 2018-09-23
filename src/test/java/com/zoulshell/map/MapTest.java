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
        sourceMap.putIfAbsent("k1", "v11");
        sourceMap.putIfAbsent("k6", "v6");
        System.out.println(sourceMap);
    }
}
