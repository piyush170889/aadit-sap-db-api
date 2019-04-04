package com.loungeapp.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.loungeapp.model.BaseWrapper;
import com.loungeapp.model.SAPDORequestWrapper;
import com.loungeapp.service.MasterService;

@RestController
@RequestMapping("/ext/master")
public class MasterController {

	@Autowired
	MasterService masterservice;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public BaseWrapper getMasterData() {
		return new BaseWrapper(masterservice.getMasterData());
	}
	
	@PostMapping("do-details")
	public BaseWrapper getDoDetails(@RequestBody SAPDORequestWrapper request) {
		
		return masterservice.doGetDoDetails(request);
	}
}
