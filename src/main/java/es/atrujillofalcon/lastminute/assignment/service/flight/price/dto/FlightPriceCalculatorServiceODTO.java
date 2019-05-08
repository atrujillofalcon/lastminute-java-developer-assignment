package es.atrujillofalcon.lastminute.assignment.service.flight.price.dto;

import es.atrujillofalcon.lastminute.assignment.type.CurrencyType;
import lombok.Builder;
import lombok.Value;

/**
 * @author Arnaldo Trujillo
 */
@Value
@Builder
public class FlightPriceCalculatorServiceODTO {

    private String flightCode;

    private Double calculatedPrice;

    private CurrencyType currency;

}
