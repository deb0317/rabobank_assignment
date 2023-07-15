# rabobank_banking_account_assignment

The Strategy pattern is used with the Card interface and its implementations DebitCard and CreditCard. 
This allows us to switch between different payment card types (debit or credit) without modifying the Account class.


API Details

 http://localhost:8080/api/users


GET 

/balance 

/allTransactions

POST

/withdraw

Payload

{
"accountNumber": 1234567890,
"userName": "John Doe",
"amount": 1000
}

/transfer


Payload :
{
"userName": "John Doe",
"sourceAccountNumber": 1234567890,
"destinationAccountNumber": 1234567891,
"amount": 400
}


API Documentation :
http://localhost:8080/swagger-ui/index.html

Business Rules and Validations:

Amount can't be zero or negative.

Extra 1% charge for credit card transfer or withdraw.

Current balance >= withdraw or transfer amount.


Correct UserNames and Account Numbers are checked before initiating any trasnfer or withdraw. 


For API authentication spring security is used to access api endpoints (for withdraw and transfer) 

Technologies Used:

SpringBoot


Java 17

Junit


Karate with Cucumber Reporting ( run TestRunner.java to generate the integration test report in target/cucumber-html-reports)