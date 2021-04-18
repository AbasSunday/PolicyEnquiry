Feature: New Policy

  @Execute
  Scenario: New Policy Enquiry Test
    Given I opened new policy enquiry page
    When I fill about you section
      | TITLE                  | Dr               |
      | FIRST NAME             | Abas             |
      | LAST NAME              | Sunday           |
      | DATE OF BIRTH          | 10/10/1958       |
      | MARITAL STATUS         | Married          |
      | OCCUPATION             | Computer Analyst |
      | HAS ANOTHER OCCUPATION | Yes              |