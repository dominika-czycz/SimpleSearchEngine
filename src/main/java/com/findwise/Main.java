package com.findwise;

import com.findwise.impl.InMemoryDocumentRepository;
import com.findwise.impl.SearchEngineImpl;
import com.findwise.impl.TFIDFSortEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class Main {
    private static Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        String doc1Id = "doc1";
        String doc1 = "the brown fox jumped over the brown dog";

        String doc2Id = "doc2";
        String doc2 = "the lazy brown dog sat in the corner";

        String doc3Id = "doc3";
        String doc3 = "the red fox bit the lazy dog";

        SearchEngine searchEngine = new SearchEngineImpl(new TFIDFSortEngine(),  new InMemoryDocumentRepository());

        searchEngine.indexDocument(doc1Id, doc1);
        searchEngine.indexDocument(doc2Id, doc2);
        searchEngine.indexDocument(doc3Id, doc3);


        System.out.println("Enter search term: ");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String phrase = in.readLine();
            List<IndexEntry> results = searchEngine.search(phrase);
            System.out.println("Results:  ");
            results.forEach(System.out::println);
        } catch (IOException e) {
            log.error("Error while reading data from console", e);
        }

    }
}