package com.fashion.matches.analyzer.word;

import java.util.Arrays;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public class TextAnalyzer {

    /**
     * Returns top 3 most occurring words from the given text. If the given text contains fewer than three unique words,
     * then either the top 2 or top 1 word(s) is returned
     * @param text The text to extract the words from
     * @return Top 3, 2, or 1 most occurring unique words in descending order. Returns empty array if given text is
     * empty or null.
     */
    public String[] getMostOccurringWords(String text) {

        return Arrays.stream(text.split(" ")).parallel()
                .collect(groupingByConcurrent(Function.identity(), counting()))
                .keySet().parallelStream()
                .toArray(String[]::new);
    }
}
