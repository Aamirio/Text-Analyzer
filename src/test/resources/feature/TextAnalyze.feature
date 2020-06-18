Feature:  Text Analyzer
  As a caller
  I want to see at most the top 3 most occurring words from a given text in descending order of occurrence
  So that I know which words are the most commonly used

  Scenario: Text to analyze contains punctuation and line breaks
    Given the text analyzer can return at most the top three most commonly used words in a given text
    When I ask for most occurring words in
          """
          In a village of La Mancha, the name of which I have
          no desire to call to
          mind, there lived not long since one of those gentlemen that keep a lance
          in the lance-rack, an old buckler, a lean hack, and a greyhound for
          coursing. An olla of rather more beef than mutton, a salad on most
          nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra
          on Sundays, made away with three-quarters of his income.
          """
    Then the following words should be returned in the correct order
    |a|of|on|

  Scenario: Text to analyze contains punctuation, lower case and upper case
    Given the text analyzer can return at most the top three most commonly used words in a given text
    When I ask for most occurring words in
          """
          e e e e DDD ddd DdD: ddd ddd aa aA Aa, bb cc cC e e e
          """
    Then the following words should be returned in the correct order
      |e|ddd|aa|

  Scenario: Text to analyze contains punctuation and apostrophes
    Given the text analyzer can return at most the top three most commonly used words in a given text
    When I ask for most occurring words in
          """
             //wont won't won't
          """
    Then the following words should be returned in the correct order
      |won't|wont|