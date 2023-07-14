package com.raboBank.users.account.controller;

import com.raboBank.users.account.exception.UserAccountException;
import com.raboBank.users.account.model.TransferRequest;
import com.raboBank.users.account.model.User;
import com.raboBank.users.account.model.WithdrawRequest;
import com.raboBank.users.account.service.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.raboBank.users.account.repository.DummyData.users;
@Validated
@RestController
@RequestMapping("/api/users")
public class UserController {

    public UserController(List<User> users) {
    }

    @GetMapping("/allTransactions")
    public List<User> getAllUsers() {
        return users;
    }

    @GetMapping("/balance")
    public ResponseEntity<List<Account>> getAllAccountBalances() {
        List<Account> accounts = new ArrayList<>();
        for (User user : users) {
            accounts.addAll(user.getAccounts());
        }
        return ResponseEntity.ok(accounts);
    }

    @PostMapping(value = "/withdraw", consumes = "application/json")
    public ResponseEntity<String> withdraw(@RequestBody WithdrawRequest withdrawRequest) {
        validateUsername(withdrawRequest.getUserName());
        return ResponseEntity.ok(findAccountByNumber(withdrawRequest.getAccountNumber()).withdraw(withdrawRequest.getAmount(), findAccountByNumber(withdrawRequest.getAccountNumber())));
     }

    @PostMapping(value = "/transfer", consumes = "application/json")
    public ResponseEntity<String> transfer(@RequestBody TransferRequest transferRequest) {
        validateUsername(transferRequest.getUserName());

        return ResponseEntity.ok(findAccountByNumber(transferRequest.getSourceAccountNumber()).transfer( transferRequest.getAmount(),findAccountByNumber(transferRequest.getDestinationAccountNumber())));
    }


    private Account findAccountByNumber(int accountNumber) {

        Optional<Account> account = users.stream()
                .flatMap(user -> user.getAccounts().stream())
                .filter(acc -> acc.getAccountNumber() == accountNumber)
                .findFirst();
        return account.orElseThrow(() -> new UserAccountException("Account not found"));
    }

    private  void validateUsername(String username) {
        boolean isValid = users.stream()
                .anyMatch(user -> user.getName().equals(username));
        if (!isValid) {
            throw new UserAccountException("Username not found");
        }
    }

}
