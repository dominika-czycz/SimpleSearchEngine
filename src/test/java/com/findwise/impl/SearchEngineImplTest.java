package com.findwise.impl;


import com.findwise.DocumentRepository;
import com.findwise.IndexEntry;
import com.findwise.SearchEngine;
import com.findwise.data.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SearchEngineImplTest {
    private DocumentRepository documentRepository;
    private SearchEngine searchEngine;
    private final String doc1Id = "doc1";
    private final String strContent1 = "the brown fox jumped over the brown dog";
    private final List<String> content1 = List.of("the", "brown", "fox", "jumped", "over", "the", "brown", "dog");
    private final Document document1 = new Document(doc1Id, content1);
    private final String doc2Id = "doc2";
    private final String strContent2 = "the lazy brown dog sat in the corner";
    private final List<String> content2 = List.of("the", "lazy", "brown", "dog", "sat", "in", "the", "corner");
    private final Document document2 = new Document(doc2Id, content2);
    private final String doc3Id = "doc3";
    private final String stringContent3 = "the red fox bit the lazy dog";
    private final List<String> content3 = List.of("the", "red", "fox", "bit", "the", "lazy", "dog");
    private final Document document3 = new Document(doc3Id, content3);


    @Before
    public void setUp() {
        documentRepository = mock(DocumentRepository.class);
        when(documentRepository.getDocument(doc1Id)).thenReturn(document1);
        when(documentRepository.getDocument(doc2Id)).thenReturn(document2);
        when(documentRepository.getDocument(doc3Id)).thenReturn(document3);
        when(documentRepository.getAllDocuments()).thenReturn(List.of(document1, document2, document3));

    }

    @Test
    public void testSearch() {
        searchEngine = new SearchEngineImpl(new TFIDFSortEngine(), documentRepository);
        searchEngine.indexDocument(doc1Id, strContent1);
        searchEngine.indexDocument(doc2Id, strContent2);
        searchEngine.indexDocument(doc3Id, stringContent3);

        List<IndexEntry> resultFox = searchEngine.search("fox");
        IndexEntryImpl indexEntry1 = new IndexEntryImpl(doc3Id);
        indexEntry1.setScore(0.05792358687259491);
        IndexEntryImpl indexEntry2 = new IndexEntryImpl(doc1Id);
        indexEntry2.setScore(0.05068313851352055);
        List<IndexEntry> expectedFox = List.of(indexEntry1, indexEntry2);

        List<IndexEntry> resultBrown = searchEngine.search("brown");
        IndexEntryImpl indexEntry3 = new IndexEntryImpl(doc1Id);
        indexEntry3.setScore(0.1013662770270411);
        IndexEntryImpl indexEntry4 = new IndexEntryImpl(doc2Id);
        indexEntry4.setScore(0.05068313851352055);
        List<IndexEntry> expectedBrown = List.of(indexEntry3, indexEntry4);

        Assertions.assertIterableEquals(expectedFox, resultFox);
        Assertions.assertIterableEquals(expectedBrown, resultBrown);
    }

    @Test
    public void testNotSingleTerm(){
        searchEngine = new SearchEngineImpl(new TFIDFSortEngine(), documentRepository);
        searchEngine.indexDocument(doc1Id, strContent1);
        searchEngine.indexDocument(doc2Id, strContent2);
        searchEngine.indexDocument(doc3Id, stringContent3);
        List<IndexEntry> resultForDouble = searchEngine.search("fox brown");

        Assertions.assertTrue(resultForDouble.isEmpty());
    }

    @Test
    public void testNotFound(){
        searchEngine = new SearchEngineImpl(new TFIDFSortEngine(), documentRepository);
        searchEngine.indexDocument(doc1Id, strContent1);
        searchEngine.indexDocument(doc2Id, strContent2);
        searchEngine.indexDocument(doc3Id, stringContent3);
        List<IndexEntry> resultForAbsent = searchEngine.search("absent");

        Assertions.assertTrue(resultForAbsent.isEmpty());
    }

}