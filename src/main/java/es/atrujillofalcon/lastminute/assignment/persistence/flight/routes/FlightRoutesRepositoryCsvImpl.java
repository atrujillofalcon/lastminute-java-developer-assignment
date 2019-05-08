package es.atrujillofalcon.lastminute.assignment.persistence.flight.routes;

import es.atrujillofalcon.lastminute.assignment.persistence.flight.routes.dto.FlightRoutesEntryDTO;
import es.atrujillofalcon.lastminute.assignment.persistence.fligth.price.dto.FlightPriceEntryDTO;
import es.atrujillofalcon.lastminute.assignment.service.csv.CsvReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.stream.Stream;

/**
 * @author Arnaldo Trujillo
 */
@Service
public class FlightRoutesRepositoryCsvImpl implements FlightRoutesRepository {

    private static final Character CSV_SEPARATOR = ',';
    private static final String CSV_FLIGHT_ROUTES_NAME = "flight-routes.csv";

    @Autowired
    private CsvReaderService csvReader;

    @Override
    public Stream<FlightRoutesEntryDTO> getAllFlightRoutes() {

        InputStream csvFileStream = getClass().getClassLoader().getResourceAsStream(CSV_FLIGHT_ROUTES_NAME);

        return csvReader.readCsv(csvFileStream, FlightRoutesEntryDTO.class, CSV_SEPARATOR);
    }
}
