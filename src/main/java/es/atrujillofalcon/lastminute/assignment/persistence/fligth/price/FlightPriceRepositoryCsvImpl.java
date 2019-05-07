package es.atrujillofalcon.lastminute.assignment.persistence.fligth.price;

import es.atrujillofalcon.lastminute.assignment.persistence.fligth.price.dto.FlightPriceEntryDTO;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

/**
 * @author Arnaldo Trujillo
 */
@Service
public class FlightPriceRepositoryCsvImpl implements FlightPriceRepository {

    @Override
    public Stream<FlightPriceEntryDTO> getAllFlightPrices() {
        return null;
    }

    @Override
    public Stream<FlightPriceEntryDTO> findFlightPricesByCode() {
        return null;
    }
}
