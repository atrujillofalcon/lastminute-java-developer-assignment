package es.atrujillofalcon.lastminute.assignment.persistence.flight.routes.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

/**
 * @author Arnaldo Trujillo
 */
@Data
public class FlightRoutesEntryDTO {

    @CsvBindByName(column = "origin")
    private String origin;

    @CsvBindByName(column = "destination")
    private String destination;

    @CsvBindByName(column = "code")
    private String code;
}
