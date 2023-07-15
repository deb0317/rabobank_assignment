@getBalances
Feature: Integration Tests for get all transactions

  Scenario: To Verify if <field> has correct value for index <index>
    Given url 'http://localhost:8080/api/users/balance'
    When method  GET
    Then status 200
    And match response[0].accountNumber == 1234567890