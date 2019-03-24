Feature: Films

  Scenario Outline: Browsing films
    When user reads films
    Then user should get films containing film "<name>" of type "<type>"
    Examples:
      | name          | type    |
      | Matrix 11     | NEW     |
      | Spider Man    | REGULAR |
      | Spider Man 2  | REGULAR |
      | Ouf of Africa | OLD     |
