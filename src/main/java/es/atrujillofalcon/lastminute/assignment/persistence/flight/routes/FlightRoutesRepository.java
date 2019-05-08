package es.atrujillofalcon.lastminute.assignment.persistence.flight.routes;

import es.atrujillofalcon.lastminute.assignment.persistence.flight.routes.dto.FlightRoutesEntryDTO;
import es.atrujillofalcon.lastminute.assignment.persistence.fligth.price.dto.FlightPriceEntryDTO;

import java.util.stream.Stream;

/**
 * @author Arnaldo Trujillo
 */
public interface FlightRoutesRepository {

    Stream<FlightRoutesEntryDTO> getAllFlightRoutes();
}
