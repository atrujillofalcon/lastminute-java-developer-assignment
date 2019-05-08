package es.atrujillofalcon.lastminute.assignment.service.flight.price;

import es.atrujillofalcon.lastminute.assignment.persistence.fligth.price.FlightPriceRepository;
import es.atrujillofalcon.lastminute.assignment.service.flight.price.dto.FlightPriceCalculatorServiceIDTO;
import es.atrujillofalcon.lastminute.assignment.service.flight.price.dto.FlightPriceCalculatorServiceODTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

@Service
public class FlightPriceCalculatorServiceImpl implements FlightPriceCalculatorService {

    @Autowired
    private FlightPriceRepository flightPriceRepository;

    @Override
    public FlightPriceCalculatorServiceODTO calculateFlightPrice(FlightPriceCalculatorServiceIDTO idto) {

        Double flightBasePrice = flightPriceRepository.findFlightPricesByCode(idto.getFlightCode()).getBasePrice();

        Double calculatedPrice = idto.getPassengers() * (flightBasePrice * getPricePercentageDependingOnDepartureDay(idto.getDepartureDate()));

        return FlightPriceCalculatorServiceODTO.builder()
                .calculatedPrice(calculatedPrice)
                .currency(idto.getToCurrency())
                .flightCode(idto.getFlightCode())
                .build();
    }

    private Double getPricePercentageDependingOnDepartureDay(LocalDate departureDate) {

        long dayDiff = ChronoUnit.DAYS.between(LocalDate.now(),departureDate);

        if (dayDiff < 3)
            return 1.5D;

        if (dayDiff < 16)
            return 1.2D;

        if (dayDiff < 31)
            return 1D;

        return 0.8D;
    }
}
