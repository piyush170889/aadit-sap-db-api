package com.loungeapp.service;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loungeapp.domain.OtpDtl;
import com.loungeapp.domain.UserLoginDtl;
import com.loungeapp.exception.ServicesException;
import com.loungeapp.model.BaseWrapper;
import com.loungeapp.persitence.UserLoginDtlRepository;
import com.loungeapp.utils.CommonUtility;

@Service
@Transactional(rollbackFor = Throwable.class)
public class CommonServiceImpl implements CommonService {

	@Autowired
	private CommonUtility commonUtility;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserLoginDtlRepository userLoginDtlRepository;

	@Autowired
	Properties configProperties;

	private Logger logger = Logger.getLogger(CommonServiceImpl.class);

	@Override
	public BaseWrapper doSendOtp(OtpDtl otpDetails) throws Exception {
		boolean isOtpSent = false;
		int otp = Integer.parseInt(commonUtility.getOTP());
		System.out.println("otp = " + otp + ", cellNum = " + otpDetails.getCellnumber() + ", deviceInf = "
				+ otpDetails.getDeviceInfo());
		isOtpSent = commonUtility.sendOTP(otpDetails.getCellnumber(), otp, otpDetails.getDeviceInfo());
		if (isOtpSent) {
			return new BaseWrapper();
		} else {
			throw new ServicesException("613");
		}
	}

	@Override
	public BaseWrapper recoverForgetPassword(String recoveryContact) throws ServicesException {

		UserLoginDtl userLoginDtl = userLoginDtlRepository.selectUsersByEmailId(recoveryContact);// GET
																									// USER
																									// DETAILS
																									// BY
																									// CONTACT
																									// NUMBER

		if (userLoginDtl == null) {// CHECK OBJECT IS NULL
			throw new ServicesException("812");
		} else {
			String forgetPasswordCode = commonUtility.getUUID();// GET USER
																// EMAIL ID BY
																// UUID AND SEND
																// UPDATE
																// PASSWORD URL.
			userLoginDtlRepository.updateForgetPasswordCode(forgetPasswordCode, userLoginDtl.getUserLoginDtlsId());
			String message = configProperties.getProperty("email.message.updatepassword") + forgetPasswordCode;
			String subject = configProperties.getProperty("email.subject.updatepassword");
			commonUtility.sendEmail(userLoginDtl.getUserDtl().getEmailId().trim(), message, subject);
			return new BaseWrapper();
		}
	}

	@Override
	public int updateUserLoginDtls(HttpServletRequest request) throws ServicesException {
		try {
			return userLoginDtlRepository.updatePassword(request.getParameter("forgetPasswordCode"),
					passwordEncoder.encode(request.getParameter("password")));
		} catch (Exception e) {
			logger.error("Exception Occured while resetting the password.");
			throw new ServicesException("811");
		}

	}

}
