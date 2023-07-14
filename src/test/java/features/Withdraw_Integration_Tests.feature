@test1
Feature: Integration Tests for withdraw

  Scenario: To Verify if <field> has correct value for index <index>
    Given url 'http://localhost:8088/api/users/withdraw
    When method  POST
    Then status 200