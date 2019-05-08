package es.atrujillofalcon.lastminute.assignment.service.flight.routes;

import es.atrujillofalcon.lastminute.assignment.service.flight.routes.dto.FlightSearchServiceIDTO;
import es.atrujillofalcon.lastminute.assignment.service.flight.routes.dto.FlightSearchServiceODTO;

public interface FlightSearchService {

    FlightSearchServiceODTO searchFlights(FlightSearchServiceIDTO idto);

}
