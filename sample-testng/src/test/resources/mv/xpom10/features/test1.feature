Feature: Second pack tests of Main Service

  Scenario: First test
    When given 5
    Then square of given value is 25

  Scenario: Second test
    When given 2
    Then square of given value is 5

  Scenario Outline: Outline tests <value> and <expectedValue>
    When given <value>
    Then square of given value is <expectedValue>
    Examples:
      | value | expectedValue |
      | 3     | 9             |
      | 2     | 4             |
      | 1     | 0             |