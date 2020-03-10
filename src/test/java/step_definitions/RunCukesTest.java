package step_definitions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = "step_definitions",

//      TODO: Implement better reports with Extent Reporter
//      plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html" },

        plugin = { "pretty",
              "json:target/cucumber-reports/Cucumber.json",
                "junit:target/cucumber-reports/Cucumber.xml",
                "html:target/cucumber-reports"
        },
        monochrome = true,
        tags = {}
)
public class RunCukesTest{

}