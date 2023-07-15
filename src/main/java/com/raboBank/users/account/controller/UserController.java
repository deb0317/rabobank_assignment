package com.raboBank.users.account.controller;

import com.raboBank.users.account.exception.UserAccountException;
import com.raboBank.users.account.model.TransferRequest;
import com.raboBank.users.account.model.BankUser;
import com.raboBank.users.account.model.WithdrawRequest;
import com.raboBank.users.account.service.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.raboBank.users.account.data.DummyData.users;

@RestController
@RequestMapping("/api/users")
public class UserController {

    /**
     * Retrieves a list of all users with their transactions.
     *
     * @return List of BankUser objects representing users and their transactions
     */
    @GetMapping("/allTransactions")
    public List<BankUser> getAllUsers() {
        return users;
    }

    /**
     * Retrieves the balances of all accounts across all users.
     *
     * @return ResponseEntity containing a list of Account objects representing account balances
     */
    @GetMapping("/balance")
    public ResponseEntity<List<Account>> getAllAccountBalances() {
        List<Account> accounts = new ArrayList<>();
        for (BankUser user : users) {
            accounts.addAll(user.getAccounts());
        }
        return ResponseEntity.ok(accounts);
    }

    /**
     * Performs a withdrawal from a specified account.
     *
     * @param withdrawRequest WithdrawRequest object containing withdrawal details
     * @return ResponseEntity with a success message if the withdrawal is successful
     */
    @PostMapping(value = "/withdraw", consumes = "application/json")
    public ResponseEntity<String> withdraw(@RequestBody WithdrawRequest withdrawRequest) {
        validateUsername(withdrawRequest.getUserName());
        return ResponseEntity.ok(findAccountByNumber(withdrawRequest.getAccountNumber()).withdraw(withdrawRequest.getAmount(), findAccountByNumber(withdrawRequest.getAccountNumber())));
    }

    /**
     * Performs a transfer between two specified accounts.
     *
     * @param transferRequest TransferRequest object containing transfer details
     * @return ResponseEntity with a success message if the transfer is successful
     */
    @PostMapping(value = "/transfer", consumes = "application/json")
    public ResponseEntity<String> transfer(@RequestBody TransferRequest transferRequest) {
        validateUsername(transferRequest.getUserName());
        return ResponseEntity.ok(findAccountByNumber(transferRequest.getSourceAccountNumber()).transfer(transferRequest.getAmount(), findAccountByNumber(transferRequest.getDestinationAccountNumber())));
    }

    /**
     * Finds an account by its account number.
     *
     * @param accountNumber The account number to search for
     * @return The Account object with the specified account number
     * @throws UserAccountException if the account is not found
     */
    private Account findAccountByNumber(int accountNumber) {
        Optional<Account> account = users.stream()
                .flatMap(user -> user.getAccounts().stream())
                .filter(acc -> acc.getAccountNumber() == accountNumber)
                .findFirst();
        return account.orElseThrow(() -> new UserAccountException("Account not found"));
    }

    /**
     * Validates if the provided username exists.
     *
     * @param username The username to validate
     * @throws UserAccountException if the username is not found
     */
    private void validateUsername(String username) {
        boolean isValid = users.stream()
                .anyMatch(user -> user.getName().equals(username));
        if (!isValid) {
            throw new UserAccountException("Username not found");
        }
    }
}
