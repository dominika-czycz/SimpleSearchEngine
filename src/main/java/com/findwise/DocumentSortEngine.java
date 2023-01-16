package com.findwise;

import com.findwise.data.Document;

import java.util.List;

public interface DocumentSortEngine {
    /**
     * @param doc  a text document
     * @param docs all documents
     * @param term term
     * @return the TF-IDF of term
     */
    double score(Document doc, List<Document> docs, String term);
}
