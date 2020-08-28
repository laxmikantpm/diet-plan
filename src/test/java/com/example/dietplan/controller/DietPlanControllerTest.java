package com.example.dietplan.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.dietplan.exception.InvalidInputParamException;
import com.example.dietplan.model.DietModel;
import com.example.dietplan.model.PersonModel;


@WebMvcTest(DietPlanController.class)
public class DietPlanControllerTest {
	
	@InjectMocks
	DietPlanController dietController;
	

	@Test
	public void givenWith_Valid_Input_Param_Shouldreturn_Dietplan() {
		PersonModel pModel = getPerson();
		//sResponseEntity<DietModel> dietModel = new ResponseEntity<DietModel>(getDietModel(), HttpStatus.OK);			
		assertThat((dietController.getPersonalDietPlan(30, "M", 170, 78).getStatusCode())).isEqualTo(HttpStatus.OK);//.thenReturn(dietModel);
		//assertThat(dietModel.getStatusCode().equals(HttpStatus.OK));
		
	}
	
	@Test
	public void givenWith_InValid_Input_Param_ShouldThrow_Exception(){
		PersonModel pModel = getPerson();
		ResponseEntity<DietModel> dietModel = new ResponseEntity<DietModel>(getDietModel(), HttpStatus.OK);	
		try {
			when((dietController.getPersonalDietPlan(30, "C", 170, 78).getStatusCode())).thenReturn(dietModel.getStatusCode());
		}catch(InvalidInputParamException ex) {
			assertEquals("Invalid gender exception", ex.getMessage() );
		}		
	}
	

	public PersonModel getPerson() {
		PersonModel model = new PersonModel();
		model.setAge(30);
		model.setGender("F");
		model.setHeight(170);
		model.setWeight(78);
		return model;
	}
	
	public DietModel getDietModel() {
		
		DietModel dietModel = new DietModel();
		
		dietModel.setProtein("10%");
		dietModel.setFat("5%");
		dietModel.setVitamins("1%");
		dietModel.setMinerals("0.1%");
		dietModel.setCarbohydrates("53.9%");
		dietModel.setFibre("30%");
		return dietModel;
	}
}
