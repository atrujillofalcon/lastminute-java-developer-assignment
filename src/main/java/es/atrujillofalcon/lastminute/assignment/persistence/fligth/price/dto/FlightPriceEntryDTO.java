package es.atrujillofalcon.lastminute.assignment.persistence.fligth.price.dto;

import com.opencsv.bean.CsvBindByName;

/**
 * @author Arnaldo Trujillo
 */
public class FlightPriceEntryDTO {

    @CsvBindByName(column = "code")
    private String flightCode;

    @CsvBindByName(column = "base price (EUR)")
    private Double basePrice;
}
