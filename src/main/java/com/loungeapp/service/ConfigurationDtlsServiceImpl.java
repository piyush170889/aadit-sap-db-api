package com.loungeapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loungeapp.domain.ConfigurationDtls;
import com.loungeapp.model.BaseWrapper;
import com.loungeapp.persitence.ConfigurationDtlsRepository;

@Service
@Transactional(rollbackFor = Throwable.class)
public class ConfigurationDtlsServiceImpl implements ConfigurationDtlsService {

	@Autowired
	private ConfigurationDtlsRepository configurationDtlsRepository;

	@Override
	public BaseWrapper updateConfigValue(ConfigurationDtls request) {

		int configDtlsId = configurationDtlsRepository.findByConfigurationName(request.getConfigurationName())
				.getConfigurationDtlsId();
		
		configurationDtlsRepository.updateConfigurationValue(configDtlsId, request.getConfigurationValue());
		
		return new BaseWrapper();
	}
}
