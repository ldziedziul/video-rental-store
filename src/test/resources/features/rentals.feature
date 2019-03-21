Feature: Rentals

  Scenario Outline: Renting a film
    When user want to rent "<film>" for <days> days
    Then film should be rented for <price> SEK
    Examples:
      | film          | days | price |
      | Matrix 11     | 1    | 40    |
      | Spider Man    | 5    | 90    |
      | Spider Man 2  | 2    | 30    |
      | Ouf of Africa | 7    | 90    |


  Scenario: Renting multiple films at once
    When user want to rent all films
    Then films should be rented for 250 SEK
