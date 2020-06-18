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

    @Test
    public void shouldReturnWordsWithoutPunctuation_whenTextGivenWithPunctuation() {

        final String text = "a\n (a a) a@ //a b:b %bb b_b ?ccc cc*c";

        assertWords(textAnalyzer.getMostOccurringWords(text), "a", "bb", "ccc");
    }

    @Test
    public void shouldReturnWordsPreservingApostrophes_whenTextGivenWithApostrophes() {

        final String text = "won't wont wont";

        assertWords(textAnalyzer.getMostOccurringWords(text), "wont", "won't");
    }

    @Test
    public void shouldReturnAnyTopThreeUniqueWords_whenGivenTextContainsEqualNumberofTopThreeUniqueWords() {

        final String text = "a a bb bb ccc ccc dddd dddd eeeee";

        final String[] words = textAnalyzer.getMostOccurringWords(text);

        assertThat(words.length).isEqualTo(3);
        assertThat(words).containsAnyOf("a", "b", "c", "d");
        assertThat(words).doesNotContain("eeeee");
    }

    @Test
    public void shouldReturnOnlyTopTwoUniqueWords_whenGivenTextContainsOnlyTwoUniqueWords() {

        final String text = "a a a bb bb";

        assertWords(textAnalyzer.getMostOccurringWords(text), "a", "bb");
    }

    @Test
    public void shouldReturnOnlyOneWord_whenGivenTextContainsOnlyOneUniqueWord() {

        final String text = "a a a";

        assertWords(textAnalyzer.getMostOccurringWords(text), "a");
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