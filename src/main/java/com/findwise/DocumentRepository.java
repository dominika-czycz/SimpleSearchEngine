package com.findwise;

import com.findwise.data.Document;

import java.util.List;

public interface DocumentRepository {
    /**
     * Add a document to the document set
     *
     * @param documentId name of the added document
     * @param content    content of the document
     */
    void addDocument(String documentId, List<String> content);

    /**
     * Get the document with a given id or null if not present
     *
     * @param documentId name of the document to get
     * @return document with a given id or null
     */
    Document getDocument(String documentId);

    /**
     * Get all documents from the document set
     *
     * @return List with all documents
     */
    List<Document> getAllDocuments();

}
