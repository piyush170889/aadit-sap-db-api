package com.loungeapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loungeapp.constants.Constants;
import com.loungeapp.domain.OITM;
import com.loungeapp.exception.ServicesException;
import com.loungeapp.model.BaseWrapper;
import com.loungeapp.persitence.OITMRepository;
import com.loungeapp.utils.CommonUtility;

@Service
@Transactional(rollbackFor = Throwable.class)
public class ItemsServiceImpl implements ItemsService {

	@Autowired
	private OITMRepository oitmRepository;

	@Autowired
	private CommonUtility commonUtility;

	@Override
	public BaseWrapper doGetItems(int pageNo, Optional<Integer> limit) throws ServicesException {

		PageRequest pageRequest = null;
		List<OITM> itemsMasterListing = null;

		if (limit.isPresent())
			pageRequest = new PageRequest((pageNo-1), limit.get());
		else
			pageRequest = commonUtility.getPageRequest(pageNo);

		itemsMasterListing = oitmRepository.findByItmsGrpCod(Constants.ITEM_GRP_CD, pageRequest);

		return new BaseWrapper(itemsMasterListing);
	}

	@Override
	public BaseWrapper doGetAllItems() {

		List<OITM> itemsMasterListing = oitmRepository.findByItmsGrpCod(Constants.ITEM_GRP_CD);

		return new BaseWrapper(itemsMasterListing);
	}
}
