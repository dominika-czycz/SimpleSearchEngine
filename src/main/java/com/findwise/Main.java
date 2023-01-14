package com.findwise;

import com.findwise.impl.SearchEngineImpl;
import com.findwise.utils.RegexConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


public class Main {
    private static Logger log = LogManager.getLogger(Main.class);
    public static void main(String[] args) throws IOException {
        SearchEngine searchEngine = new SearchEngineImpl();
        List<String> files = List.of("testfile1.txt", "testfile2.txt", "testfile3.txt");

        for (String fileName : files) {
            try (BufferedReader file = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = file.readLine()) != null) {
                    Arrays.stream(line.split(RegexConstants.WORD_REGEX))
                            .forEach(word -> searchEngine.indexDocument(fileName, word));
                }
            } catch (IOException e) {
                log.warn("File {} not found.", fileName);
                e.printStackTrace();
            }

        }
        System.out.println("Print search phrase: ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String phrase = in.readLine();
        Set<String> strings = ((SearchEngineImpl) searchEngine).find(phrase);
        strings.forEach(System.out::println);
    }
}