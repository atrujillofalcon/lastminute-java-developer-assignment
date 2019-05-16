package es.atrujillofalcon.lastminute.assignment.test.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import es.atrujillofalcon.lastminute.assignment.FlightSearcherApplication;
import es.atrujillofalcon.lastminute.assignment.controller.dto.FlightSearchRequest;
import es.atrujillofalcon.lastminute.assignment.controller.dto.FlightSearchResponse;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author Arnaldo Trujillo
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources")
@SpringBootTest(classes = FlightSearcherApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration
public class FlightSearchCucumberStep {

    private ResponseEntity<FlightSearchResponse> searchResponse;

    @When("^the client calls /search flights of (\\d+) with origin in (.*) and destination in (.*) for departure date (.*)")
    public void the_client_issues_GET_version(int totalPassengers, String origin, String destination, String departureDate) throws Throwable {

        FlightSearchRequest request = FlightSearchRequest.builder()
                .departureDate(LocalDate.parse(departureDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .destination(destination)
                .origin(origin)
                .totalPassengers(totalPassengers)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.CONTENT_TYPE, Collections.singletonList("application/json"));
        headers.put(HttpHeaders.ACCEPT, Collections.singletonList("application/json"));

        HttpEntity<FlightSearchRequest> flightSearcHttpEntity = new HttpEntity<>(request, headers);

        RestTemplate restTemplate = new RestTemplate();

        searchResponse = restTemplate.exchange("http://localhost:8080/flight/search", HttpMethod.GET, flightSearcHttpEntity, FlightSearchResponse.class);
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        HttpStatus currentStatusCode = searchResponse.getStatusCode();
        assertEquals("status code is incorrect : " + searchResponse.getBody(), currentStatusCode.value(), searchResponse.getStatusCodeValue());
    }

}
