@INTERNAL_EXCEPTION_SCENARIO
Feature: Internal Server Exception Flow
  Description: When we tried to unformatted json

  Background: User generates token for Authorisation
    Given I am an authorized user

  Scenario Outline: the Authorized user can cake add cake with wrong input.
    Given I try to add cake with invalid format json file "<wrong_cake_file>"
    Then I except internal server exception

    Examples:
      | wrong_cake_file    |  |
      | invalidformat.json |  |