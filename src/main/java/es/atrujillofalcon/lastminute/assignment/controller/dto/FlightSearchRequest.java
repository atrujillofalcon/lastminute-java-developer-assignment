package es.atrujillofalcon.lastminute.assignment.controller.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class FlightSearchRequest {

    @NotBlank
    @Size(min = 3, max = 3)
    private String origin;

    @NotBlank
    @Size(min = 3, max = 3)
    private String destination;

    @Future
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate departureDate;

    @NotNull
    private Integer totalPassengers;

}
