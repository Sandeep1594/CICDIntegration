package Gherkins;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/main/java/Gherkins",glue="StepDefinition",monochrome=true,plugin= {"html:target/cucumber.html"})

public class TestngCucumbertest extends AbstractTestNGCucumberTests{

}
