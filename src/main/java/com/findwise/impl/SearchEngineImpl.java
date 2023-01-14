package com.findwise.impl;

import com.findwise.IndexEntry;
import com.findwise.utils.RegexConstants;
import com.findwise.SearchEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.*;

public class SearchEngineImpl implements SearchEngine {

    private static Logger log = LogManager.getLogger(SearchEngineImpl.class);

    private final Map<String, Set<String>> wordFilesIndexes = new HashMap<>();

    @Override
    public void indexDocument(String id, String content) {
        content = content.toLowerCase();
        wordFilesIndexes.putIfAbsent(content, new HashSet<>());
        wordFilesIndexes.get(content).add(id);
    }

    @Override
    public List<IndexEntry> search(String term) {
        return null;
    }

    public Set<String> find(String phrase) {
        String[] words = phrase.split(RegexConstants.WORD_REGEX);
        Set<String> result = wordFilesIndexes.get(words[0].toLowerCase());
        if (result == null) {
            log.info("Not found");
            return Collections.emptySet();
        }

        Arrays.stream(words)
                .forEach(word -> result.retainAll(wordFilesIndexes.get(word)));
        return result;
    }
}
