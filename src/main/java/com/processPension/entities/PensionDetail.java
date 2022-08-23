package com.processPension.entities;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PensionDetail {
	
	private double pensionAmount;
	private double bankServiceCharge;
	
}
