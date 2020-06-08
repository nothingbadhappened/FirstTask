package com.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;


import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = "src/test/resources/features/",
        glue = {"com/step_definitions",
                "com/hooks"},

//      TODO: Implement better reports with Extent Reporter
//      plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/fancy/report.html" },

        plugin = { "pretty",
                "json:target/cucumber-reports/Cucumber.json",
                "junit:target/cucumber-reports/Cucumber.xml",
                "html:target/cucumber-reports/",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:target/cucumber-reports/fancy/report.html"
        },
        tags = {"@FullRun"}
)
public class RunnerTest {
//        @AfterClass
//        public static void writeExtentReport() {
//                Reporter.loadXMLConfig(new File(System.getProperty("user.dir") + "/src/test/resources/configurations/extent-config.xml"));
//                Reporter.setSystemInfo("user", System.getProperty("user.name"));
//                Reporter.setSystemInfo("os", System.getProperty("os.name"));
//                Reporter.setTestRunnerOutput("Test Run complete. Check this fancy report for the details ;)");
//        }
}
