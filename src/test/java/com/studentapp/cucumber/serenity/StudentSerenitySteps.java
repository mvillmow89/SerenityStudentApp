package com.studentapp.cucumber.serenity;

import java.util.HashMap;
import java.util.List;

import com.studentapp.model.StudentClass;
import com.studentapp.utils.ReusableSpecifications;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class StudentSerenitySteps {
	
	@Step("Add a Student - FirstName: {0}, LastName: {1}, Email: {2}, Program: {3}, Courses: {4}")
	public ValidatableResponse createStudent(String firstName, String lastName, String email, String program, List<String> courses) { 
		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(program);
		student.setCourses(courses);
		
		return SerenityRest.rest().given()
				.spec(ReusableSpecifications.getGenericRequestSpec())
				.when().body(student).post().then();
	}
	
	@Step("Getting Student Information Using FirstName: {0}")
	public HashMap<String, Object> getStudentInfoByFirstName(String firstName) {
		return SerenityRest.rest().given().when()
				.get("/list").then().statusCode(200)
				.extract().path("findAll{it.firstName=='" + firstName + "'}.get(0)");
	}
	
	@Step("Getting student information with StudentID:{0}")
	public ValidatableResponse getStudentById(int studentId) {
		return SerenityRest.rest().given()
				.when().get("/"+studentId).then();
	}
	
	@Step("Updating student information with StudentID: {0}, FirstName: {1}, LastName: {2}, Email: {3}, Program: {4}, Courses: {5}")
	public ValidatableResponse updateStudent(int studentId, String firstName, String lastName, String email, String program, List<String> courses) {
		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(program);
		student.setCourses(courses);
		
		return SerenityRest.rest().given()
				.spec(ReusableSpecifications.getGenericRequestSpec())
				.log().all()
				.when().body(student).put("/"+studentId).then();
	}
	
	@Step("Delete the Student with StudentID: {0}")
	public void deleteStudent(int studentId) {
		SerenityRest.rest().given().when().delete("/" + studentId);
	}

}
