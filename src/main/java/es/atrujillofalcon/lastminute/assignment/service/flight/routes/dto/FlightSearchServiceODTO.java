package es.atrujillofalcon.lastminute.assignment.service.flight.routes.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

/**
 * @author Arnaldo Trujillo
 */
@Value
@Builder
public class FlightSearchServiceODTO {

    private List<String> flightCodes;

}
