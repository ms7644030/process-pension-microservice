package com.processPension.entities;


import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PensionerDetail {
	
	private Pensioner pensioner;
	private Bankdetail bankdetail;
	
}
