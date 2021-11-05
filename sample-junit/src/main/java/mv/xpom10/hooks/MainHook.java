package mv.xpom10.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class MainHook {

    @Before
    public void setUp(Scenario scenario) {
        System.out.printf("Before scenario: '%s'%n", scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.printf("After scenario: '%s'%n", scenario.getName());
    }
}
