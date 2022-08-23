package com.processPension.entities;



import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Pensioner {
	
	private Long aadhaar_number;
	private String name;
	private String date_of_birth;
	private String pan;
	private double salaryEarned;
	private double allowances;
	private String selfOrFamily;
	
}
