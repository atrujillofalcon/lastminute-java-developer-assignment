package es.atrujillofalcon.lastminute.assignment.persistence.fligth.price.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

/**
 * @author Arnaldo Trujillo
 */
@Data
public class FlightPriceEntryDTO {

    @CsvBindByName(column = "code")
    private String flightCode;

    @CsvBindByName(column = "price")
    private Double basePrice;
}
