Feature: Template Management
  As Miranda
  I want to create/read templates
  In order to prepare survey

  Scenario: Creating new template
    When Miranda creates template with name 'NewTemplate'
    Then Miranda can see template 'NewTemplate' in her account

  Scenario: Duplicated template
    Given Miranda has template with name 'DuplicatedTemplate'
    When Miranda creates template with name 'DuplicatedTemplate'
    Then Miranda receives TemplateAlreadyExists error

  Scenario Outline: Name of template can only contain letters, numbers and whitespaces
    When Miranda creates template with name '<templateName>'
    Then Template created successfully

    Examples:
    | templateName              |
    | OneWordName               |
    | NameWithNumber1           |
    | Name With Whitespaces     |

  Scenario: Error message received when template name is wrong
    When Miranda creates template with name 'WrongTemplate!'
    Then Miranda receives WrongTemplateName error

