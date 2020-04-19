package com.studentapp.junit.studentsinfo;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.ReusableSpecifications;
import com.studentapp.utils.TestUtils;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentCRUDTest extends TestBase {
	
	static String firstName = "SMOKEUSER" + TestUtils.getRandomValue();
	static String lastName = "SMOKEUSER" + TestUtils.getRandomValue();
	static String programm = "ComputerScience";
	static String email = TestUtils.getRandomValue() + "test@yahoo.com";
	static int studentId;
	
	@Steps
	StudentSerenitySteps steps;
	
	@Title("This Test Will Create A New Student")
	@Test
	public void test001() {
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("JAVA");
		courses.add("C++");
		
		steps.createStudent(firstName, lastName, email, programm, courses)
		.statusCode(201)
		.spec(ReusableSpecifications.getGenericResponseSpec());
	}
	
	@Title("Verify the New Student Was Added To the Application")
	@Test
	public void test002() {
		HashMap<String,Object> value = steps.getStudentInfoByFirstName(firstName);
		studentId = (int) value.get("id");
		
		verifyStudent(value);
		
	}
	
	@Title("Update the User Information and Verify Updated Information")
	@Test
	public void test003() {
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("JAVA");
		courses.add("C++");
		
		firstName = firstName + "_Updated";
		
		steps.updateStudent(studentId, firstName, lastName, email, programm, courses);

		HashMap<String,Object> value = steps.getStudentInfoByFirstName(firstName);
		verifyStudent(value);
	}
	
	@Title("Remove the student and verify student is deleted")
	@Test
	public void test004() {
		steps.deleteStudent(studentId);
		steps.getStudentById(studentId).statusCode(404);
	}
	
	public void verifyStudent(HashMap<String,Object> value) {
		assertThat(value,  hasValue(firstName));
		assertThat(value,  hasValue(lastName));
		assertThat(value,  hasValue(email));
		assertThat(value,  hasValue(programm));	
	}

}
