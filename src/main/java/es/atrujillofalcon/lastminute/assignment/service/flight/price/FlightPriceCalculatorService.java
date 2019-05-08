package es.atrujillofalcon.lastminute.assignment.service.flight.price;

import es.atrujillofalcon.lastminute.assignment.service.flight.price.dto.FlightPriceCalculatorServiceIDTO;
import es.atrujillofalcon.lastminute.assignment.service.flight.price.dto.FlightPriceCalculatorServiceODTO;

public interface FlightPriceCalculatorService {

    FlightPriceCalculatorServiceODTO calculateFlightPrice(FlightPriceCalculatorServiceIDTO idto);

}
