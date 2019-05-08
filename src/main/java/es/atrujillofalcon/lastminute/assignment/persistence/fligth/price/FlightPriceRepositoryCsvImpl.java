package es.atrujillofalcon.lastminute.assignment.persistence.fligth.price;

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
public class FlightPriceRepositoryCsvImpl implements FlightPriceRepository {

    private static final Character CSV_SEPARATOR = ',';
    private static final String CSV_FLIGHT_PRICES_NAME = "flight-prices.csv";

    @Autowired
    private CsvReaderService csvReader;

    @Override
    public Stream<FlightPriceEntryDTO> getAllFlightPrices() {

        InputStream csvFileStream = getClass().getClassLoader().getResourceAsStream(CSV_FLIGHT_PRICES_NAME);

        return csvReader.readCsv(csvFileStream, FlightPriceEntryDTO.class, CSV_SEPARATOR);
    }

    @Override
    public FlightPriceEntryDTO findFlightPricesByCode(String flightCode) {

        return this.getAllFlightPrices()
                .filter(entry -> entry.getFlightCode().equalsIgnoreCase(flightCode))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not flight price found with code: " + flightCode));
    }
}
