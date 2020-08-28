package com.example.dietplan.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class PersonModel {
	
	
	@Positive
	private int age;
	
	@NotNull
	private String gender;
	
	@Positive
	private int height;
	
	@Positive
	private int weight;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}
