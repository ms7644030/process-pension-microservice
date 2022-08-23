package com.processPension.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.processPension.customException.BusinessException;
import com.processPension.customException.BusinessException2;
import com.processPension.entities.Bankdetail;
import com.processPension.entities.PensionDetail;
import com.processPension.entities.PensionerDetail;

@Service
public class PensionDetailServiceImpl implements ProcessPensionService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private PensionDetail pensionDetail;

	@Value("${AUTHORIZATION_SERVICE_URI:http://localhost:8088}")
	private String authorizationHost;

	@Value("${PENSIONER_DETAIL_URI:http://localhost:9001}")
	private String pensionerDetailHost;

	@Override
	public PensionDetail getPensionDetailByAadhar(Long aadhaar, String header) {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", header);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<String> authresponse = restTemplate.exchange(
				authorizationHost + "/api/authorization-service/validate", HttpMethod.GET, request, String.class);

		String isvalid = (String) authresponse.getBody();

		if (isvalid.equalsIgnoreCase("Invalid")) {

			throw new BusinessException2(400, "Invalid jwt");
		}

		else if (isvalid.equalsIgnoreCase("valid")) {

			ResponseEntity<String> response = restTemplate.exchange(
					pensionerDetailHost + "/api/pensioner-detail-service/pensionerDetail/" + aadhaar, HttpMethod.GET,
					request, String.class);

			String value = (String) response.getBody();

			if (value.equalsIgnoreCase("Invalid")) {

				throw new BusinessException(601, "Invalid Aadhaar");
			} else {

				ObjectMapper mapper = new ObjectMapper();

				try {
					PensionerDetail pensionerDetail = mapper.readValue(value, PensionerDetail.class);

					String type = pensionerDetail.getPensioner().getSelfOrFamily().trim();

					double lastEarnedSalary = pensionerDetail.getPensioner().getSalaryEarned();
					double allowances = pensionerDetail.getPensioner().getAllowances();

					Bankdetail bankdetail = pensionerDetail.getBankdetail();
					String bankType = bankdetail.getPublicOrprivate_bank().trim();

					// TO CALCULATE PENSION AMOUNT AND BANK SERVICE CHARGE BASED ON PENSIONTYPE i.e.
					// SELF OR FAMILY AND BANK TYPE i.e. PUBLIC OR PRIVATE
					// RESPECTIVELLY.

					if (type.equalsIgnoreCase("self")) {

						double pensionAmmount = (0.80 * lastEarnedSalary) + allowances;

						pensionDetail.setPensionAmount(pensionAmmount);

					} else if (type.equalsIgnoreCase("family")) {

						double pensionAmmount = (0.50 * lastEarnedSalary) + allowances;

						pensionDetail.setPensionAmount(pensionAmmount);

					}

					if (bankType.equalsIgnoreCase("public")) {

						pensionDetail.setBankServiceCharge(500.0);

					}

					else if (bankType.equalsIgnoreCase("private")) {

						pensionDetail.setBankServiceCharge(550.0);

					}

				} catch (JsonProcessingException e) {

					e.printStackTrace();
				}

			}

		}

		return pensionDetail;

	}

}
