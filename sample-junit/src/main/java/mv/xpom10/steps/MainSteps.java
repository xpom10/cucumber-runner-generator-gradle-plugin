package mv.xpom10.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import mv.xpom10.service.Calculator;

import static org.junit.Assert.assertEquals;

public class MainSteps {

    private final Calculator calculator = new Calculator();
    private int actualValue;

    @When("given {int}")
    public void getValue(int value) {
        this.actualValue = calculator.square(value);
    }

    @Then("square of given value is {int}")
    public void checkValue(int expectedValue) {
        assertEquals("value is not valid", expectedValue, actualValue);
    }
}
