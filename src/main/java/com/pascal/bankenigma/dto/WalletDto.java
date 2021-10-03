package com.pascal.bankenigma.dto;

public class WalletDto {
    private String walletId;
    private String method;
    private Integer balance;

    public WalletDto() {
    }

    public WalletDto(String walletId, String method, Integer balance) {
        this.walletId = walletId;
        this.method = method;
        this.balance = balance;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
