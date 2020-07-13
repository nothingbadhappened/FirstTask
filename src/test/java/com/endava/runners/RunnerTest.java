package com.endava.runners;

import com.endava.helpers.configuration.SpringConfig;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;


@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = "src/test/resources/features/",
        glue = {"com/endava/hooks",
                "com/endava/steps"},

        plugin = {"pretty",
                "json:target/cucumber-reports/Cucumber.json",
                "junit:target/cucumber-reports/Cucumber.xml",
                "html:target/cucumber-reports/",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:target/cucumber-reports/fancy/report.html"
        },
        tags = {"@FullRun"},
        stepNotifications = true
)
@ContextConfiguration(classes = SpringConfig.class)
public class RunnerTest {

}
