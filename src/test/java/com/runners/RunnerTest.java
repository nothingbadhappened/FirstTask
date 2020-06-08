package com.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = "src/test/resources/features/",
        glue = {"com/step_definitions",
                "com/hooks"},

        plugin = { "pretty",
                "json:target/cucumber-reports/Cucumber.json",
                "junit:target/cucumber-reports/Cucumber.xml",
                "html:target/cucumber-reports/",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:target/cucumber-reports/fancy/report.html"
        },
        tags = {"@FullRun"}
)
public class RunnerTest {

}
