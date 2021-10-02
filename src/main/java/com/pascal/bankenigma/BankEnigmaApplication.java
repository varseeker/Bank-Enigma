package com.pascal.bankenigma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
public class BankEnigmaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankEnigmaApplication.class, args);
    }

}
