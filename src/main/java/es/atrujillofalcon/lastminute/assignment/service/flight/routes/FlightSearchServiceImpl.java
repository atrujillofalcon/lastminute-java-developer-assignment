package es.atrujillofalcon.lastminute.assignment.service.flight.routes;

import es.atrujillofalcon.lastminute.assignment.persistence.flight.routes.FlightRoutesRepository;
import es.atrujillofalcon.lastminute.assignment.persistence.flight.routes.dto.FlightRoutesEntryDTO;
import es.atrujillofalcon.lastminute.assignment.service.flight.routes.dto.FlightSearchServiceIDTO;
import es.atrujillofalcon.lastminute.assignment.service.flight.routes.dto.FlightSearchServiceODTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightSearchServiceImpl implements FlightSearchService {

    @Autowired
    private FlightRoutesRepository flightRoutesRepository;

    @Override
    public FlightSearchServiceODTO searchFlights(FlightSearchServiceIDTO idto) {

        List<String> codeResults = flightRoutesRepository.getAllFlightRoutes()
                .filter(entry -> entry.getDestination().equalsIgnoreCase(idto.getDestination()))
                .filter(entry -> entry.getOrigin().equalsIgnoreCase(idto.getOrigin()))
                .map(FlightRoutesEntryDTO::getCode)
                .collect(Collectors.toList());

        return FlightSearchServiceODTO.builder()
                .flightCodes(codeResults)
                .build();
    }
}
