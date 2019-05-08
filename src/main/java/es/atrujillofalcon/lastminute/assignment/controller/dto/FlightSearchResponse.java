package es.atrujillofalcon.lastminute.assignment.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FlightSearchResponse {

    private List<FlightSearchResult> results;

}
