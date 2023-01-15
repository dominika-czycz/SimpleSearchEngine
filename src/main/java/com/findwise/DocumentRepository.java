package com.findwise;

import com.findwise.data.Document;

import java.util.List;

public interface DocumentRepository {
    void addDocument(String pathToDocument, List<String> content);

    Document getDocument(String documentId);

    List<Document> getAllDocuments();

}
