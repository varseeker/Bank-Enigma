package com.pascal.bankenigma.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "mst_wallet")
public class Wallet {
    @Id
    @GeneratedValue(generator = "uuid_generator")
    @GenericGenerator(name = "uuid_generator", strategy = "uuid")
    private String id;
    private String providerName;
    private Integer balance;

    @Transient
    private String userDataId;

    @ManyToOne
    @JoinColumn(name = "user_data_id", nullable = false)
    private UserData userData;

    public Wallet() {
    }

    public Wallet(String providerName, Integer balance) {
        this.providerName = providerName;
        this.balance = balance;
    }

    public Wallet(String providerName, Integer balance, String userDataId) {
        this.providerName = providerName;
        this.balance = balance;
        this.userDataId = userDataId;
    }

    public Wallet(String providerName, Integer balance, String userDataId, UserData userData) {
        this.providerName = providerName;
        this.balance = balance;
        this.userDataId = userDataId;
        this.userData = userData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getUserDataId() {
        return userDataId;
    }

    public void setUserDataId(String userDataId) {
        this.userDataId = userDataId;
    }

    @JsonIgnore
    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
