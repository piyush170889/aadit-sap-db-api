package com.loungeapp.service;

import java.util.Map;

import com.loungeapp.model.BaseWrapper;
import com.loungeapp.model.SAPDORequestWrapper;

public interface MasterService {

	Map<String, Object> getMasterData();

	BaseWrapper doGetDoDetails(SAPDORequestWrapper request);

}
