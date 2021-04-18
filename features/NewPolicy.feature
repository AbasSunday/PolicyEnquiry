Feature: New Policy

  @Execute
  Scenario: Fill about yourself section
    Given I opened new policy enquiry page
    When I fill about you section
      | TITLE                  | Dr                      |
      | FIRST NAME             | Abas                    |
      | LAST NAME              | Sunday                  |
      | DATE OF BIRTH          | 10/10/1958              |
      | MARITAL STATUS         | Married                 |
      | OCCUPATION             | Computer Analyst        |
      | HAS ANOTHER OCCUPATION | No                      |
      | ANOTHER OCCUPATION     | Account Director        |
      | PHONE NUMBER           | 0123456789              |
      | EMAIL                  | vketipisz@qmetric.co.uk |

  @Execute
  Scenario Outline: Say you <ANOTHER OCCUPATION> another occupation
    Given I opened new policy enquiry page
    When I say that I "<ANOTHER OCCUPATION>" another occupation
    Then Another occupation input should be "<ANOTHER OCCUPATION INPUT STATE>"
    Examples:
      | ANOTHER OCCUPATION | ANOTHER OCCUPATION INPUT STATE |
      | Have               | Present                        |
      | Don't have         | Not Present                    |