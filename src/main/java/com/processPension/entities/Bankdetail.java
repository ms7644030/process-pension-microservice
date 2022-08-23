package com.processPension.entities;



import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bankdetail {
	
	private Long aadhaar_number;
	private String bank_name;
	private String account_number;
	private String publicOrprivate_bank;
	

}
