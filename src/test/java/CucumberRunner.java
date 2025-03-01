import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//@CucumberOptions(
//        features = {"src/test/resources/features/Login.feature"}
//)
@Test
@CucumberOptions(
        features = {"src/test/resources/features"}, // Specifies the location of the feature files
        publish = true // Indicates whether to publish the test results
)
public class CucumberRunner extends AbstractTestNGCucumberTests {
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpCucumber() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    //@DataProvider
    //public Object[][] features() {
    //    return testNGCucumberRunner.provideScenarios();
    //}

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterClass
    public void tearDownClass() {
        testNGCucumberRunner.finish();
    }
}