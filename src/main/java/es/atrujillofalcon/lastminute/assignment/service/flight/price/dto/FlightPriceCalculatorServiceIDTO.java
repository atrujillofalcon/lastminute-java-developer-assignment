package es.atrujillofalcon.lastminute.assignment.service.flight.price.dto;

import es.atrujillofalcon.lastminute.assignment.type.CurrencyType;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

/**
 * @author Arnaldo Trujillo
 */
@Value
@Builder
public class FlightPriceCalculatorServiceIDTO {

    private String flightCode;

    private CurrencyType fromCurrency;

    private CurrencyType toCurrency;

    private LocalDate departureDate;

}
