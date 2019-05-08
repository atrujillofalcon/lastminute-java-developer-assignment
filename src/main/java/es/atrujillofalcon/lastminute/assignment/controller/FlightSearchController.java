package es.atrujillofalcon.lastminute.assignment.controller;

import es.atrujillofalcon.lastminute.assignment.controller.dto.FlightSearchRequest;
import es.atrujillofalcon.lastminute.assignment.controller.dto.FlightSearchResponse;
import es.atrujillofalcon.lastminute.assignment.controller.transformer.FlightControllerServiceTransformer;
import es.atrujillofalcon.lastminute.assignment.service.flight.price.FlightPriceCalculatorService;
import es.atrujillofalcon.lastminute.assignment.service.flight.price.dto.FlightPriceCalculatorServiceODTO;
import es.atrujillofalcon.lastminute.assignment.service.flight.routes.FlightSearchService;
import es.atrujillofalcon.lastminute.assignment.service.flight.routes.dto.FlightSearchServiceIDTO;
import es.atrujillofalcon.lastminute.assignment.service.flight.routes.dto.FlightSearchServiceODTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class FlightSearchController {

    private static final Logger LOG = LoggerFactory.getLogger(FlightSearchController.class);

    private final static String BASE_PATH = "/flight";

    @Autowired
    private FlightSearchService flightSearchService;

    @Autowired
    private FlightPriceCalculatorService flightPriceCalculatorService;

    @Autowired
    private FlightControllerServiceTransformer controllerServiceTransformer;

    @RequestMapping(BASE_PATH + "/search")
    public FlightSearchResponse searchFlight(@Valid FlightSearchRequest request) {

        LOG.info("Searching flights by: " + request.toString());

        FlightSearchServiceIDTO searchServiceIDTO = controllerServiceTransformer.requestToFlightSearchServiceIDTO(request);

        FlightSearchServiceODTO searchServiceODTO = flightSearchService.searchFlights(searchServiceIDTO);

        List<FlightPriceCalculatorServiceODTO> priceResults = Optional.ofNullable(searchServiceODTO)
                .map(FlightSearchServiceODTO::getFlightCodes)
                .orElse(Collections.emptyList())
                .stream()
                .map(flightCode -> controllerServiceTransformer.requestAndCodeToPriceServiceIDTO(flightCode, request))
                .map(flightPriceCalculatorService::calculateFlightPrice)
                .collect(Collectors.toList());

        return controllerServiceTransformer.servicePricesToResponse(priceResults);

    }

}
