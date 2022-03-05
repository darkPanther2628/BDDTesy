package runners;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;

@CucumberOptions(
		features = "src/test/resources/features",
		glue = {"stepDefinitions"},	
		plugin= {
				"pretty",
				"html:target/cucumber-reports/cucumber-html-report.html",
				"json:target/cucumber-reports/CucumberTestReport.json",
				"rerun:target/cucumber-reports/rerun.txt",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
		}
		//tags = "@smokeTests"
		)
public class TestRunner{
	
	private TestNGCucumberRunner testNGCucumberRunner;
	
	@BeforeClass(alwaysRun=true)
	public void setupClass() throws Exception
	{
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
		
	}
	
	 @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
	    public void feature(PickleWrapper pickle,FeatureWrapper cucumberFeature) {
		 testNGCucumberRunner.runScenario(pickle.getPickle());
	    }

	@DataProvider
	public Object[][] features()
	{
		return testNGCucumberRunner.provideScenarios();
		
	}
	
	@AfterClass(alwaysRun=true)
	public void tearDownClass() throws Exception
	{
		testNGCucumberRunner.finish();
	}
	
}
