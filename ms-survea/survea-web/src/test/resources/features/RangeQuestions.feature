Feature: As a Miranda
  I want to add range question
  In order to collect evaluation

  Scenario: Range question can be added to template
    Given Miranda has template with name 'RangeQuestion'
    When Miranda adds range question 'Rate the product' with min value 1 and max value 10
    Then Miranda can see 1 range questions in the template

  Scenario: Range question must be defined with valid range
    Given Miranda has template with name 'InvalidRangeQuestion'
    When Miranda adds range question 'Rate the product' with min value 10 and max value 9
    Then Miranda receives WrongRangeQuestion error
