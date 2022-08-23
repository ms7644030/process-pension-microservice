package com.processPension.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.processPension.entities.PensionDetail;
import com.processPension.entities.ProcessPensionInput;
import com.processPension.services.ProcessPensionService;

@RestController
//@CrossOrigin(origins="*")
@RequestMapping("/processPension")
public class ProcessPensionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessPensionController.class);

	@Autowired
	private ProcessPensionService processPensionService;

	@PostMapping("/aadhaar")
	public PensionDetail getPensionDetail(@RequestBody ProcessPensionInput processPensionInput,
			@RequestHeader(value = "Authorization") String header) {

		LOGGER.info("Received Request to retrieve pension Amount and banking charges for  {}  ",
				processPensionInput.getAadhaar_number());

		return this.processPensionService.getPensionDetailByAadhar(processPensionInput.getAadhaar_number(), header);

	}

}
