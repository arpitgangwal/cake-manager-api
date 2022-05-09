@BUSINESS_EXCEPTION_SCENARIO
Feature: Bad Input Exception Flow
Description: When we tried to insert bad input in cake api

  Background: User generates token for Authorisation
    Given I am an authorized user

  Scenario Outline: the Authorized user can cake add cake with wrong input.
    Given I try to add cake with wrong input from file "<wrong_cake_file>"
    Then I except bad input exception

Examples:
  | wrong_cake_file   |  |
  | invalidinput.json |  |