Feature: As a Miranda
  I want to create a survey from Template
  In order to collect feedback from respondents

  Scenario: Create survey from template
    Given Miranda has template with name "HowAreYouToday"
      And Template contains text question "HowAreYouToday?"
    When Miranda creates survey with name "Wednesday mood"
    Then Survey contains questions from template

  Scenario: Survey cannot be created without questions in template
    Given Miranda has template with name "Empty template"
    When Miranda creates survey with name "From Empty"
    Then Miranda receives NoQuestionError error

