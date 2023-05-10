package org.example;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text:");
        String input = scanner.nextLine();

        WordCounter wordCounter = new WordCounter();
        Map<String, Integer> wordCounts = wordCounter.countWords(input);
        for (Map.Entry<String, Integer> words : wordCounts.entrySet()) {
            System.out.println(words.getKey() + " = " + words.getValue());
        }
    }
}
