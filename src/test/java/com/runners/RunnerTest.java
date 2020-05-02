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

//      TODO: Implement better reports with Extent Reporter
//      plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html" },

        plugin = { "pretty",
                "json:target/cucumber-reports/Cucumber.json",
                "junit:target/cucumber-reports/Cucumber.xml",
                "html:target/cucumber-reports/"
        },
        tags = {}
)
public class RunnerTest {

}