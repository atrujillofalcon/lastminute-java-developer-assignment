package es.atrujillofalcon.lastminute.assignment.controller.transformer;

import es.atrujillofalcon.lastminute.assignment.controller.dto.FlightSearchRequest;
import es.atrujillofalcon.lastminute.assignment.controller.dto.FlightSearchResponse;
import es.atrujillofalcon.lastminute.assignment.service.flight.price.dto.FlightPriceCalculatorServiceIDTO;
import es.atrujillofalcon.lastminute.assignment.service.flight.price.dto.FlightPriceCalculatorServiceODTO;
import es.atrujillofalcon.lastminute.assignment.service.flight.routes.dto.FlightSearchServiceIDTO;

import java.util.List;

public interface FlightControllerServiceTransformer {

    FlightSearchServiceIDTO requestToFlightSearchServiceIDTO(FlightSearchRequest request);

    FlightPriceCalculatorServiceIDTO requestAndCodeToPriceServiceIDTO(String code, FlightSearchRequest request);

    FlightSearchResponse servicePricesToResponse(List<FlightPriceCalculatorServiceODTO> serviceDTO);

}
