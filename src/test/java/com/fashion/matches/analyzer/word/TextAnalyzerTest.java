package com.fashion.matches.analyzer.word;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class TextAnalyzerTest {

    private final TextAnalyzer textAnalyzer = new TextAnalyzer();

    @Test
    public void shouldReturnOnlyTopThreeUniqueWords_whenGivenTextContainsAtLeastThreeUniqueWords() {

        final String text = "a a a a bb bb bb ccc ccc dddd";

        final String[] words = textAnalyzer.getMostOccurringWords(text);

        assertThat(words).isNotEmpty();
        assertThat(words).containsOnly("a", "bb", "ccc");
    }

    @Test
    public void shouldReturnOnlyTopThreeUniqueWordsInDescendingOrder_whenGivenTextContainsAtLeastThreeUniqueWords() {

        final String text = "a a a a bb bb bb ccc ccc dddd";

        assertWords(textAnalyzer.getMostOccurringWords(text), "a", "bb", "ccc");
    }

    @Test
    public void shouldReturnWordsInLowercase_whenTextGivenInLowerAndUpperCase() {

        final String text = "a A a A Bb bB BB cCc CcC";

        assertWords(textAnalyzer.getMostOccurringWords(text), "a", "bb", "ccc");
    }

    private void assertWords(String[] words, String... expectedWords) {

        assertThat(words).isNotEmpty();
        assertThat(words).hasSize(expectedWords.length);

        IntStream.range(0, expectedWords.length).forEach(x -> assertWord(words[x], expectedWords[x]));
    }

    private void assertWord(String actualWord, String expectedWord) {

        assertThat(actualWord).isNotEmpty();
        assertThat(actualWord).isEqualTo(expectedWord);
    }

}