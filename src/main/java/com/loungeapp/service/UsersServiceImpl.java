package com.loungeapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loungeapp.domain.UserLoginDtl;
import com.loungeapp.exception.ServicesException;
import com.loungeapp.model.BaseWrapper;
import com.loungeapp.model.PaginationDetails;
import com.loungeapp.model.UsersDetailsWrapper;
import com.loungeapp.persitence.UserLoginDtlRepository;
import com.loungeapp.persitence.UserRoleDtlRepository;
import com.loungeapp.utils.CommonUtility;

@Service
@Transactional(rollbackFor = Throwable.class)
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UserRoleDtlRepository userRoleDtlRepository;

	@Autowired
	private CommonUtility commonUtility;

	@Override
	public BaseWrapper doGetUsers(int pageNo, List<String> roles) {

		Pageable pageRequest = commonUtility.getPageRequest(pageNo);
		List<String> adminUserLoginIds = userRoleDtlRepository.selectUserLoginDtlsIdByRoles(roles, pageRequest);

		List<UserLoginDtl> sortedCustomersList;

		if (adminUserLoginIds.size() > 0)
			sortedCustomersList = userLoginDtlRepository.findByLoginDtlsIdList(adminUserLoginIds);
		else
			sortedCustomersList = new ArrayList<>();

		double totalUsersCount = userRoleDtlRepository.selectCountOfAllActiveUsersByRoles(roles);

		PaginationDetails paginationDetails = commonUtility.getPaginationDetails(totalUsersCount, pageNo);

		UsersDetailsWrapper response = new UsersDetailsWrapper(sortedCustomersList, paginationDetails);
		return response;
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserLoginDtlRepository userLoginDtlRepository;

	
	@Override
	public BaseWrapper doValidatePassword(UserLoginDtl request) throws ServicesException {

		String loggedInUserId = commonUtility.getLoggedUserId();
		String passwordToValidate = request.getPassword();

		String userPass = userLoginDtlRepository.selectUserPassByUserDtlsId(loggedInUserId);

		if (passwordEncoder.matches(passwordToValidate, userPass))
			return new BaseWrapper();
		else
			throw new ServicesException("616");
	}

	@Override
	public BaseWrapper doForceChangePassword(UserLoginDtl request) {

		String loggedInUserId = commonUtility.getLoggedUserId();

		// TODO: Validate Password is not null and empty
		String passwordToChange = request.getPassword();

		UserLoginDtl userLoginDtls = userLoginDtlRepository.findByUserDtlsId(loggedInUserId);
		userLoginDtls.setPassword(passwordEncoder.encode(passwordToChange));
		userLoginDtls.setIsPasswordChanged((byte)0);
		
		userLoginDtlRepository.save(userLoginDtls);

		return new BaseWrapper();
	}

	@Override
	public BaseWrapper doChangePasswordOfUserFromAdmin(UserLoginDtl request) {

		userLoginDtlRepository.updatePasswordByUserLoginDtlsId(passwordEncoder.encode(request.getPassword()),
				request.getUserLoginDtlsId().trim());
		return new BaseWrapper();
	}
}
