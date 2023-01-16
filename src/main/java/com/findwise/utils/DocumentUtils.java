package com.findwise.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Arrays;
import java.util.List;

public class DocumentUtils {
    private DocumentUtils() {
    }

    public static final String WORD_REGEX = "\\W+";

    public static List<String> parseDocumentToList(String content) {
        return Arrays.stream(content.split(WORD_REGEX)).toList();
    }

    public static boolean isSingleTerm(String term) {
        List<String> toCheck = parseDocumentToList(term);
        return !CollectionUtils.isEmpty(toCheck) && toCheck.size() == NumberUtils.INTEGER_ONE;
    }
}
