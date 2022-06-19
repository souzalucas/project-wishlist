package com.project.demo.cucumber;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		tags = "", 
		features= {"src/test/java/resources/features"}, 
		glue= {"src.test.java.com.project.demo.steps"}
)
public class CucumberRunner {

}
