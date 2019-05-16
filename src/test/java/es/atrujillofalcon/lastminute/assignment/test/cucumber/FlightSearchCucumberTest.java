package es.atrujillofalcon.lastminute.assignment.test.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import es.atrujillofalcon.lastminute.assignment.FlightSearcherApplication;
import es.atrujillofalcon.lastminute.assignment.controller.dto.FlightSearchResponse;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

/**
 * @author Arnaldo Trujillo
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features")
public class FlightSearchCucumberTest {

}
