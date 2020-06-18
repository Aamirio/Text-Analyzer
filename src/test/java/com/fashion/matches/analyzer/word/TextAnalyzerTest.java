package com.fashion.matches.analyzer.word;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TextAnalyzerTest {

    private final TextAnalyzer textAnalyzer = new TextAnalyzer();

    @Test
    public void shouldReturnOnlyTopThreeUniqueWords_whenGivenTextContainsDuplicateWords() {

        final String text = "a a a a bb bb bb ccc ccc dddd";

        final String[] words = textAnalyzer.getMostOccurringWords(text);

        assertThat(words).containsOnly("a", "bb", "ccc");
    }

}