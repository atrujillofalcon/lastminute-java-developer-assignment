package es.atrujillofalcon.lastminute.assignment.service.flight.routes.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

/**
 * @author Arnaldo Trujillo
 */
@Value
@Builder
public class FlightSearchServiceIDTO {

    private String origin;

    private String destination;

}
