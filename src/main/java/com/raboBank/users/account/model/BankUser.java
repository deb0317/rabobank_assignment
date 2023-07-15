package com.raboBank.users.account.model;

import com.raboBank.users.account.service.Account;
import java.util.ArrayList;
import java.util.List;

public class BankUser {
        private String id;
        private String name;
        private String address;
        private List<Account> accounts;

        public BankUser(String id, String name, String address) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.accounts = new ArrayList<>();
        }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}

