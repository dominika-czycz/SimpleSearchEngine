package com.findwise.impl;

import com.findwise.DocumentSortEngine;
import com.findwise.data.Document;

import java.util.List;

public class TFIDFSortEngine implements DocumentSortEngine {

    /**
     * @param wordsInDoc list of words in a document
     * @param term       String represents a term
     * @return term frequency of term in document
     */
    private double tf(List<String> wordsInDoc, String term) {
        double result = wordsInDoc.stream()
                .filter(term::equalsIgnoreCase)
                .count();
        return result / wordsInDoc.size();
    }

    /**
     * @param docs lists of words for each document in the dataset
     * @param term String represents a term
     * @return the inverse term frequency of term in documents
     */
    private double idf(List<Document> docs, String term) {
        double n = docs.stream()
                .filter(doc -> doc.content().contains(term))
                .count();
        return Math.log(docs.size() / n);
    }


    @Override
    public double score(Document document, List<Document> docs, String term) {
        return tf(document.content(), term) * idf(docs, term);
    }

}
