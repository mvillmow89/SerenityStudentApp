package com.studentapp.cucumber.steps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.utils.TestUtils;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;

public class StudentSteps {
	
	static String email = null;
	
	@Steps
	StudentSerenitySteps steps;
	
	@When("^User sends a GET request to the list endpoint,they must get back a valid status code 200$")
	public void verify_status_code_200_for_list_endpoint() {
		SerenityRest.rest()
		.given()
		.when()
		.get("list")
		.then()
		.statusCode(200);
	}
	
	@When("^I create a new student by providing the information firstName (.*) lastName (.*) email (.*) programme (.*) courses (.*)$")
	public void createStudent(String firstName, String lastName, String _email, String programme, String courses) {
		List<String> courseList = new ArrayList<>();
		courseList.add(courses);
		
		email = TestUtils.getRandomValue() + _email;
		
		steps.createStudent(firstName, lastName, email, programme, courseList)
		.assertThat().statusCode(201);
	}
	
	@Then("^I verify that the student with (.*) is created$")
	public void verifyStudentUsingEmail(String emailId) {
		HashMap<String, Object> actual = steps.getStudentInfoByEmailId(email );
		assertThat(actual, hasValue(email));
	}

}
