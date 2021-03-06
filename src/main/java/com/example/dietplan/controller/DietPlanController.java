package com.example.dietplan.controller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dietplan.exception.InvalidInputParamException;
import com.example.dietplan.model.DietModel;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/")
@Validated
public class DietPlanController {

	/**
	 * 
	 * @param age
	 * @param gender
	 * @param height
	 * @param weight
	 * @return
	 */
	@GetMapping("/dietPlan")
	@ApiOperation(value = "Get Personal Diet Plan", notes = "Get Personal Diet Plan")
	public ResponseEntity<DietModel> getPersonalDietPlan(
			@RequestParam(name = "age") @Positive(message = "Age should be positive") int age,
			@RequestParam(name = "gender") @NotBlank(message = "Gender should have F or M") String gender,
			@RequestParam(name = "height") @Positive(message = "Height sould be positive") int height,
			@RequestParam(name = "weight") @Positive(message = "Weight sould be positive") int weight) {

		gender = gender.toUpperCase().trim();
		

		if (!gender.matches("F|M")) {
			throw new InvalidInputParamException("Invalid gender exception");
		}

		DietModel dietModel = new DietModel();

		dietModel.setProtein("10%");
		dietModel.setFat("5%");
		dietModel.setVitamins("1%");
		dietModel.setMinerals("0.1%");

		if ((height - weight) > 100) {
			dietModel.setCarbohydrates("30%");
			dietModel.setFibre("53.9%");
		} else if ((height - weight) < 100) {
			dietModel.setCarbohydrates("53.9%");
			dietModel.setFibre("30%");
		} else if ((height - weight) == 100) {
			dietModel.setCarbohydrates("41.95%");
			dietModel.setFibre("41.95%");
		}

		return new ResponseEntity<DietModel>(dietModel, HttpStatus.OK);

	}

}
