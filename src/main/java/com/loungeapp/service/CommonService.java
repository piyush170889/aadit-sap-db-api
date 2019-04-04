package com.loungeapp.service;

import javax.servlet.http.HttpServletRequest;

import com.loungeapp.domain.OtpDtl;
import com.loungeapp.exception.ServicesException;
import com.loungeapp.model.BaseWrapper;

public interface CommonService {

//	BaseWrapper doSendOtp(OtpDtl otpDetails) throws Exception;

	BaseWrapper recoverForgetPassword(String recoveryContact) throws ServicesException;

	int updateUserLoginDtls(HttpServletRequest request) throws ServicesException;

}
