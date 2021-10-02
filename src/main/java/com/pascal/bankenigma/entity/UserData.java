package com.pascal.bankenigma.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mst_user")
public class UserData {
    @Id
    @GeneratedValue(generator = "uuid_generator")
    @GenericGenerator(name = "uuid_generator", strategy = "uuid")
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String motherName;
    private String accountNumber;

    public UserData() {
    }

    public UserData(String name, String email, String phoneNumber, String address, String motherName, String accountNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.motherName = motherName;
        this.accountNumber = accountNumber;
    }


}
