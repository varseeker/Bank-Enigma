package com.pascal.bankenigma.service.UserAccount;

import com.pascal.bankenigma.dto.ProfileAccountDto;
import com.pascal.bankenigma.entity.UserAccount;
import com.pascal.bankenigma.service.CrudService;

import java.util.Optional;

public interface UserAccountService extends CrudService<UserAccount> {
    public UserAccount createData(ProfileAccountDto profileAccountDto);
}
