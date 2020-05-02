package com.studentapp.cucumber;

import org.junit.runner.RunWith;

import com.studentapp.testbase.TestBase;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		plugin = {"pretty"},
		features = "src/test/resources/feature/")
public class StudentRunner extends TestBase {

}
