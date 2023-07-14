@Withdraw

Feature: Integration Tests for Mortgage Check Functionality

  Scenario: To Verify if the response after ---
    Given url 'http://localhost:8088/api/users/transfer'
    And def payload = read('files/test1.json')
    And request payload
    When method  POST
    Then status 200
    And match response == "Transfer Amount: 100.00 to account number: 1234567891"