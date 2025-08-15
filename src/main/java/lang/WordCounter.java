package lang;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class WordCounter {

    public static void main(String[] args) {
        String str = "Hello World Hello";
        count(str, 2);
    }

    public static void count(String str, Integer n) {
        Map<String, Integer> words = new HashMap<>();
        Stream
                .of(str.split(" "))
                .forEach(word -> words.put(word, words.getOrDefault(word, 0) + 1));

        for (Map.Entry<String, Integer> entry : words.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (Objects.equals(value, n)) {
                System.out.println(key);
            }
        }
    }
}
