@HAPPY_SCENARIO
Feature: End to End Cake Manager Flow
Description: The purpose of this test is to test happy flow of Cake Manager API
  
  Background: User generates token for Authorisation
    Given I am an authorized user

  Scenario Outline: the Authorized user can cake and list it.

    When I add a cake to my cake list "<input_file>"

    Then the cake is added

    When I want to list cakes

    Then list contain one cake

    And I can download file list

Examples:
  | input_file   |  |
  | vanilla.json |  |