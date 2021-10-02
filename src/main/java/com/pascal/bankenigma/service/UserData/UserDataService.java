package com.pascal.bankenigma.service.UserData;

import com.pascal.bankenigma.dto.ProfileAccountDto;
import com.pascal.bankenigma.entity.UserData;
import com.pascal.bankenigma.service.CrudService;

public interface UserDataService extends CrudService<UserData> {
    public ProfileAccountDto createData(ProfileAccountDto profileAccountDto);

}
