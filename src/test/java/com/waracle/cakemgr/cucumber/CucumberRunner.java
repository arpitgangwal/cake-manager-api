package com.waracle.cakemgr.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.waracle.cakemgr.cucumber"},
        monochrome = true
)

public class CucumberRunner {
}
