Feature: As a Miranda
  I want to add Text question to Template
  In order to gather free text responses
  
  Scenario: Free text question can be added to template
    Given Miranda has template with name 'FreeTextQuestion'
    When Miranda adds text question 'Any comments about the product'
    Then Miranda can see 1 text questions in the template


