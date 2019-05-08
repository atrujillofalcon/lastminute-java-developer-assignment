package es.atrujillofalcon.lastminute.assignment.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlightSearchResult {

    private String flightCode;

    private String totalPrice;

}
