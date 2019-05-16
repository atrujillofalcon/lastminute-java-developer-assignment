package es.atrujillofalcon.lastminute.assignment.test.cucumber;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.atrujillofalcon.lastminute.assignment.FlightSearcherApplication;
import es.atrujillofalcon.lastminute.assignment.controller.dto.FlightSearchRequest;
import es.atrujillofalcon.lastminute.assignment.controller.dto.FlightSearchResponse;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 * @author Arnaldo Trujillo
 */
@SpringBootTest(classes = FlightSearcherApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration
public class FlightSearchCucumberStep {

    private ResponseEntity<FlightSearchResponse> searchResponse;

    @When("^the client calls /search flights of (\\d+) with origin in (.*) and destination in (.*) for departure date (.*)")
    public void the_client_issues_GET_version(int totalPassengers, String origin, String destination, String departureDate) throws Throwable {

        FlightSearchRequest request = new FlightSearchRequest();
        request.setDepartureDate(LocalDate.parse(departureDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        request.setDestination(destination);
        request.setOrigin(origin);
        request.setTotalPassengers(totalPassengers);

        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.CONTENT_TYPE, Collections.singletonList("application/json"));
        headers.put(HttpHeaders.ACCEPT, Collections.singletonList("application/json"));

        HttpEntity<FlightSearchRequest> flightSearcHttpEntity = new HttpEntity<>(request, headers);

        RestTemplate restTemplate = new RestTemplate();

        try {
            searchResponse = restTemplate.exchange("http://localhost:8080/flight/search", HttpMethod.GET, flightSearcHttpEntity, FlightSearchResponse.class);

        } catch (HttpClientErrorException e) {
            System.out.println(e.getResponseBodyAsString());
            throw e;
        }
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        HttpStatus currentStatusCode = searchResponse.getStatusCode();
        assertEquals("status code is incorrect : " + searchResponse.getBody(), currentStatusCode.value(), searchResponse.getStatusCodeValue());
    }

}
