package com.processPension.services;

import com.processPension.entities.PensionDetail;

public interface ProcessPensionService {

	public PensionDetail getPensionDetailByAadhar(Long aadhaar, String header);

}
