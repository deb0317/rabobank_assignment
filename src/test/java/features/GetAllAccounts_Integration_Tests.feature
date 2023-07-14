@test
Feature: Integration Tests for get all transactions

  Scenario: To Verify if <field> has correct value for index <index>
    Given url 'http://localhost:8088/api/users/allTransactions'
    When method  GET
    Then status 200
  And match response[0].x[0].name == 'John Doe'
