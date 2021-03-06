package com.loungeapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loungeapp.domain.ConfigurationDtls;
import com.loungeapp.domain.Master;
import com.loungeapp.domain.OITM;
import com.loungeapp.domain.Orders;
import com.loungeapp.domain.RoleMasterDtl;
import com.loungeapp.model.BaseWrapper;
import com.loungeapp.model.SAPDORequestWrapper;
import com.loungeapp.model.SAPDOResponseWrapper;
import com.loungeapp.persitence.ConfigurationDtlsRepository;
import com.loungeapp.persitence.MasterRepository;
import com.loungeapp.persitence.OITMRepository;
import com.loungeapp.persitence.OrdersRepository;
import com.loungeapp.persitence.RoleMasterDtlRepository;
import com.loungeapp.utils.RoleType;

@Service
@Transactional
public class MasterServiceImpl implements MasterService {

	@Autowired
	MasterRepository masterRepository;

	@Autowired
	private ConfigurationDtlsRepository configurationDtlsRepository;

	@Autowired private RoleMasterDtlRepository roleMasterDtlRepository;
	
	
	@Override
	public Map<String, Object> getMasterData() {

		Map<String, Object> finalMasterHashmap = new HashMap<String, Object>();

		// Create HashMap of Master Data and put in final Hashmap
		Map<String, List<Master>> masterHashmap = new HashMap<String, List<Master>>();
		List<Master> outputList = null;

		List<Master> masterList = masterRepository.findByIsActive((byte) 1);

		int listSize = masterList.size();
		for (int i = 0; i < listSize; i++) {
			Master masterObj = masterList.get(i);

			String group = masterObj.getGrp();
			if (masterHashmap.containsKey(group)) {
				outputList = masterHashmap.get(group);
				outputList.add(masterObj);
			} else {
				outputList = new ArrayList<>();
				outputList.add(masterObj);
			}

			masterHashmap.put(group, outputList);
		}

		finalMasterHashmap.putAll(masterHashmap);

		// Get List of Area Data and put in final Hashmap
		/*List<AreaDtl> areaDtlsList = areaRepository.findByIsActive((byte) 1);
		finalMasterHashmap.put("areaDtlsList", areaDtlsList);

		// Get List of City Data and put in final Hashmap
		List<CityDtl> cityDtlsList = cityRepository.findByIsActive((byte) 1);
		finalMasterHashmap.put("cityDtlsList", cityDtlsList);

		// Get List of State Data and put in final Hashmap
		List<StateDtl> stateDtlsList = stateRepository.findByIsActive((byte) 1);
		finalMasterHashmap.put("stateDtlsList", stateDtlsList);

		// Get List of Country Data and put in final Hashmap
		List<CountryDtl> countryDtlsList = countryDtlRepository.findByIsActive((byte) 1);
		finalMasterHashmap.put("countryDtlsList", countryDtlsList);*/

//		List<ItemDtl> itemsList = itemDtlRepository.findByIsActiveOrderByItemNameAsc((byte) 1);
//		finalMasterHashmap.put("itemsList", itemsList);

		List<ConfigurationDtls> configurationDtlsList = configurationDtlsRepository.findByIsActive((byte) 1);
		for (ConfigurationDtls configurationDtls : configurationDtlsList) {
			finalMasterHashmap.put(configurationDtls.getConfigurationName().trim(),
					configurationDtls.getConfigurationValue().trim());
		}

//		List<String> loggedInUserRolesList = userRoleDtlRepository.selectAssignedRolesByUserId(commonUtility.getLoggedUserId());
//		finalMasterHashmap.put("roles", loggedInUserRolesList);
		
		List<RoleMasterDtl> roleMasterDtlList = roleMasterDtlRepository.findRolesMasterAndNotIn((byte)1, RoleType.ROLE_ADMIN.toString());
		List<String> roleDtlList = new ArrayList<String>();
		for (RoleMasterDtl roleMasterDtl : roleMasterDtlList) {
			roleDtlList.add(roleMasterDtl.getRolesMasterDtlsId());
		}
		finalMasterHashmap.put("roles", roleDtlList);

		return finalMasterHashmap;
	}

	@Autowired
	private OITMRepository oitmRepository;
	
	@Autowired
	private OrdersRepository ordersRepository;
	
	@Override
	public BaseWrapper doGetDoDetails(SAPDORequestWrapper request) {

		Set<String> itemCodesSet = request.getItemCodes();
		System.out.println("itemCodesSet = " + itemCodesSet.toString());
		Set<Integer> orderIdsSet = request.getOrderIds();
		System.out.println("orderIdsSet = " + orderIdsSet.toString());
		
		List<OITM> itemDetails = oitmRepository.findByItemCodeIn(itemCodesSet);
		List<Orders> orderDetails = ordersRepository.findByDocNumIn(orderIdsSet);
		
		SAPDOResponseWrapper response = new SAPDOResponseWrapper(itemDetails, orderDetails);
		
		return new BaseWrapper(response);
	}
}
