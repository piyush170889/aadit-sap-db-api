package com.loungeapp.service;

import com.loungeapp.domain.UserLoginDtl;
import com.loungeapp.exception.ServicesException;
import com.loungeapp.model.BaseWrapper;

public interface LoginService {

	BaseWrapper doLoginUserWitValidCreadentials(UserLoginDtl userLoginDtl) throws ServicesException;

}
