package es.atrujillofalcon.lastminute.assignment.persistence.fligth.price;

import es.atrujillofalcon.lastminute.assignment.persistence.fligth.price.dto.FlightPriceEntryDTO;

import java.util.stream.Stream;

/**
 * @author Arnaldo Trujillo
 */
public interface FlightPriceRepository {

    Stream<FlightPriceEntryDTO> getAllFlightPrices();

    FlightPriceEntryDTO findFlightPricesByCode(String flightCode);

}
