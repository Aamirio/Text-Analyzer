package com.fashion.matches.analyzer.word.acceptance;

import com.fashion.matches.analyzer.word.TextAnalyzer;
import cucumber.api.DataTable;
import cucumber.api.java8.En;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class TextAnalyzerSteps implements En {

    private TextAnalyzer textAnalyzer = new TextAnalyzer();
    private String[] result;

    public TextAnalyzerSteps() {

        Given("^the text analyzer can return at most the top three most commonly used words in a given text$",
                () -> {});

        When("^I ask for most occurring words in$", (String text) -> {
            result = textAnalyzer.getMostOccurringWords(text);
        });

        Then("^the following words should be returned in the correct order$", (DataTable dataTable) -> {

            final List<String> expectedWords = dataTable.asList(String.class);

            assertThat(result.length).isEqualTo(expectedWords.size());

            IntStream.range(0, result.length).forEach(x -> assertThat(result[x]).isEqualTo(expectedWords.get(x)));
        });
    }
}
