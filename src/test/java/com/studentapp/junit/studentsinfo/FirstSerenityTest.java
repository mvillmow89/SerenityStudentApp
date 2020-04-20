package com.studentapp.junit.studentsinfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Title;

@RunWith(SerenityRunner.class)
public class FirstSerenityTest {
	
	@BeforeClass
	public static void init() {
		RestAssured.baseURI="http://localhost:8080/student";
	}
	
	@Test
	public void getAllStudents() {
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.statusCode(200);
	}
	
	@Test
	public void thisIsaFail() {
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.statusCode(500);
	}
	
	@Pending
	@Test
	public void thisIsAPendingTest() {
		
	}
	
	@Ignore
	@Test
	public void thisIsAnIgnoredTest() {
		
	}
	
	@Test
	public void thisIsAnErrorTest() {
		System.out.println("This is an error " + (5/0));
	}
	
	@Test
	public void FileNotFoundTest() throws FileNotFoundException {
		File file = new File("c://file.txt");
		FileReader fr = new FileReader(file);
		file.exists();
	}
	
	@Test
	public void thisIsACompromisedTest() throws Exception {
		throw new Exception();
	}
	
	@Manual
	@Test
	public void thisIsAManualTest() {
	}
	
	@Title("Get List of All Student Information from Student App")
	@Test
	public void gtest01() {
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.statusCode(200);
	}

}
