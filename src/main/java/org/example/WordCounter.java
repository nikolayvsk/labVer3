package org.example;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WordCounter {
    public static Map<String, Integer> countWords(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Error: null");
        }
        if (input.isEmpty()) {
            return Collections.emptyMap();
        }

        String[] words = input.split("[^a-zA-Z]+");
        Map<String, Integer> result = new HashMap<>();
        for (String word : words) {
            result.put(word, result.getOrDefault(word, 0) + 1);
        }
        return result;
    }
}
