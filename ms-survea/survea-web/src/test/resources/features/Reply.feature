Feature: As a Jimmy
  I want to complete a survey
  In order to send feedback to Miranda

  Background:
    Given Miranda has template with name "HowAreYouToday"
    And Template contains text question "HowAreYouToday?"
    And Template contains range question "HowAreYouToday?" with min value 1 and max value 10
    And Miranda creates survey with name "Wednesday mood"

  Scenario: Reply can be empty
    Given Jimmy retrieves survey with name "Wednesday mood"
    When Jimmy submits a reply
    Then Miranda can see answers in the survey

  Scenario: Reply can contain all answers
    Given Jimmy retrieves survey with name "Wednesday mood"
    And Jimmy answers with OK to next question in survey
    And Jimmy answers with 8 to next question in survey
    When Jimmy submits a reply
    Then Miranda can see answers in the survey

  Scenario: Reply is not submitted in case of invalid answer
    Given Jimmy retrieves survey with name "Wednesday mood"
    And Jimmy answers with OK to next question in survey
    And Jimmy answers with INVALID_ANSWER to next question in survey
    When Jimmy submits a reply
    Then Jimmy receives WrongAnswerError error

