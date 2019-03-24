package com.loungeapp.service;

import java.util.List;

import com.loungeapp.domain.UserLoginDtl;
import com.loungeapp.exception.ServicesException;
import com.loungeapp.model.BaseWrapper;

public interface UsersService {

	BaseWrapper doGetUsers(int pageNo, List<String> roles);

	BaseWrapper doValidatePassword(UserLoginDtl request) throws ServicesException;

	BaseWrapper doForceChangePassword(UserLoginDtl request);

	BaseWrapper doChangePasswordOfUserFromAdmin(UserLoginDtl request);

}
