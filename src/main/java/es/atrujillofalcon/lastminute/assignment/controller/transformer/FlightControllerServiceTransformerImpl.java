package es.atrujillofalcon.lastminute.assignment.controller.transformer;

import es.atrujillofalcon.lastminute.assignment.controller.dto.FlightSearchRequest;
import es.atrujillofalcon.lastminute.assignment.controller.dto.FlightSearchResponse;
import es.atrujillofalcon.lastminute.assignment.controller.dto.FlightSearchResult;
import es.atrujillofalcon.lastminute.assignment.service.flight.price.dto.FlightPriceCalculatorServiceIDTO;
import es.atrujillofalcon.lastminute.assignment.service.flight.price.dto.FlightPriceCalculatorServiceODTO;
import es.atrujillofalcon.lastminute.assignment.service.flight.routes.dto.FlightSearchServiceIDTO;
import es.atrujillofalcon.lastminute.assignment.type.CurrencyType;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightControllerServiceTransformerImpl implements FlightControllerServiceTransformer {

    @Override
    public FlightSearchServiceIDTO requestToFlightSearchServiceIDTO(FlightSearchRequest request) {

        return FlightSearchServiceIDTO.builder()
                .origin(request.getOrigin())
                .destination(request.getDestination())
                .build();
    }

    @Override
    public FlightPriceCalculatorServiceIDTO requestAndCodeToPriceServiceIDTO(String code, FlightSearchRequest request) {

        return FlightPriceCalculatorServiceIDTO.builder()
                .departureDate(request.getDepartureDate())
                .flightCode(code)
                .fromCurrency(CurrencyType.EUR)
                .toCurrency(CurrencyType.EUR)
                .passengers(request.getTotalPassengers())
                .build();

    }

    @Override
    public FlightSearchResponse servicePricesToResponse(List<FlightPriceCalculatorServiceODTO> serviceDTO) {

        List<FlightSearchResult> responseResults = Optional.ofNullable(serviceDTO)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::serviceODTOToFlightSearchResult)
                .collect(Collectors.toList());

        return FlightSearchResponse.builder()
                .results(responseResults)
                .build();
    }

    private FlightSearchResult serviceODTOToFlightSearchResult(FlightPriceCalculatorServiceODTO serviceODTO) {

        return FlightSearchResult.builder()
                .flightCode(serviceODTO.getFlightCode())
                .totalPrice(String.format( "%.2f", serviceODTO.getCalculatedPrice()) + " " + serviceODTO.getCurrency().getSymbol())
                .build();

    }
}
