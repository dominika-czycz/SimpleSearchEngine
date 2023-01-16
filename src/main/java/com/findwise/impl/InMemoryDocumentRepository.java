package com.findwise.impl;

import com.findwise.DocumentRepository;
import com.findwise.data.Document;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class InMemoryDocumentRepository implements DocumentRepository {

    private static Logger log = LogManager.getLogger(InMemoryDocumentRepository.class);
    private final Map<String, Document> documentsCache = new HashMap<>();

    @Override
    public void addDocument(String documentId, List<String> content) {
        log.info("Adding file {} to documents", documentId);
        documentsCache.putIfAbsent(documentId, new Document(documentId, content));
    }

    @Override
    public Document getDocument(String documentId) {
        return documentsCache.get(documentId);
    }

    @Override
    public List<Document> getAllDocuments() {
        return new ArrayList<>(documentsCache.values());
    }
}
