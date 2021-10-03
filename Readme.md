# ENIGMA BANK

Enigma Bank is a study case of developing REST API using Java Spring-Boot
## Description

This API include :
- UserAccount Entity that have Username, Password, Email Attribute.
- UserData Entity that have Name, Email, Phone Number, Address, Mother Name, and Account Number Attribute.
- Wallet Entity that have Provider Name, Balance Attribute.
- Transaction Entity that have User Data, Wallet Relation, Merchant Relation, Date, Bill Attribute.
- Merchant Entity that have name Attribute.
- LogHistory Entity that have User Name, Activity, Provider Name, Initial Balance, Usage, Final Balance, and Date Attribute.

## Getting Started
**You can download spring module form [start.spring.io](https://start.spring.io/)**
### Dependencies

* Spring-Boot
* Spring-Test
* Spring-Core WebAPP
* Java Persistence API
* PostgreSQL 11
* JUNIT 11
* Maven

### Installing

* This program is open source, just clone and get started
* Remember to check pom.xml and Application.properties

### Executing program

* Just Run it like other API
* Step-by-step bullets
```
localhost/accounts
```
```
localhost/users
```
```
localhost/wallets
```
```
localhost/merchants
```
```
localhost/transactions
```
```
localhost/logs
```

### JSON Example
#### Account Entity
- Show All Accounts
```
GET:localhost/accounts
```
```
POST:localhost/signin
```
```
POST:localhost/register
```
- JSON EXAMPLE for ACCOUNT ENTITY
```
{
    "name":"Ayato",
    "email":"zactura@example.com",
    "phoneNumber":"0218808321",
    "address":"Jl Kebangsaan 21",
    "motherName":"Ardiana",
    "accountNumber":"88219021",
    "userName":"ayato",
    "password":"ayato"
}
```
### USER DATA ENTITY
- UserData included with Account but it has separate table
#### Wallet Entity
- Show All Wallets
```
GET:localhost/wallets
```
- Create New Wallet
```
POST:localhost/wallet
```
- Use Some Feature on 
```
POST:localhost/menu
```
- JSON EXAMPLE for WALLET ENTITY
```
{
    "providerName":"DANA",
    "balance":50000,
    "userDataId":"8a8ab2a17c407e53017c407ee9a50000"
}
```
#### Merchant Entity
- Get All Merchants.
```
GET:localhost/merchants
```
- Create Merchant
```
POST:localhost/merchant
```
- JSON EXAMPLE fot MERCHANT ENTITY
```
{
    "name":"PLN"
}
```
#### Transaction Entity
- Get All Transaction.
```
GET:localhost/transactions
```
- Create Transactions
```
POST:localhost/transaction
```
- Payment Transaction
```
DELETE:localhost/transaction
```
- JSON EXAMPLE fot TRANSACTION ENTITY
```
{
    "userDataId":"8a8ab2a17c407e53017c407ee9a50000",
    "walletId":"8a8ab2a17c417d68017c417da0b90000",
    "merchantId":"8a8ab2a17c447637017c44865aef0000",
    "bill":50000
}
```
### LOG HISTORY
- Log History get it data from transaction
## Help

Any advise for common problems or issues.
```
command to run if program contains helper info
```

## Authors

Contributors names and contact info

ex. Muhammad Ibadurrahman Al-ahsan  
ex. [@Varseeker](https://github.com/varseeker)

## Version History

* 0.3
    * Various bug fixes and adding transactions service feature
    * See [commit change]() or See [release history]()
* 0.2
    * Various bug fixes and adding parking area and vehicle service feature
    * See [commit change]() or See [release history]()
* 0.1
    * Initial Release

## License

This project is licensed under the [AHSAN] License - see the LICENSE.md file for details

## Acknowledgments

Inspiration, code snippets, etc.
* [awesome-readme](https://github.com/matiassingers/awesome-readme)
* [PurpleBooth](https://gist.github.com/PurpleBooth/109311bb0361f32d87a2)
* [dbader](https://github.com/dbader/readme-template)
* [zenorocha](https://gist.github.com/zenorocha/4526327)
* [fvcproductions](https://gist.github.com/fvcproductions/1bfc2d4aecb01a834b46)