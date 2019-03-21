Feature: Rentals
  All scenarios assume that we have the following film for rental:
  - Matrix 11 (New release)
  - Spider Man (Regular rental)
  - Spider Man 2 (Regular rental)
  - Out of Africa (Old film)

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

  Scenario Outline: Returning film
    Given user rented "<film>" for <rentalDays> days
    When user returns "<film>" after <returnDays> days
    Then user should pay <surcharge> for late return
    Examples:
      | film          | rentalDays | returnDays | surcharge |
      | Matrix 11     | 1          | 3          | 80        |
      | Spider Man    | 5          | 6          | 30        |
      | Spider Man 2  | 2          | 2          | 0         |
      | Ouf of Africa | 7          | 5          | 0         |
