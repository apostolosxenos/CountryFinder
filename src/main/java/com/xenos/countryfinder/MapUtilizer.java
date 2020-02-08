package com.xenos.countryfinder;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import static java.util.stream.Collectors.toMap;

public class MapUtilizer {

    public static Map<String, String> sortByValueDesc(Map<String, String> map) {
        Map<String, String> sortedMap = map
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

        return sortedMap;
    }
}
