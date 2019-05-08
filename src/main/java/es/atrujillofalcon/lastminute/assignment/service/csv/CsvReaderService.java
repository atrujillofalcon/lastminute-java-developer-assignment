package es.atrujillofalcon.lastminute.assignment.service.csv;

import java.io.InputStream;
import java.net.URI;
import java.util.stream.Stream;

/**
 * @author Paradigma
 */
public interface CsvReaderService {

    char DEFAULT_SEPARATOR = ';';

    <T> Stream<T> readCsv(URI csvPath, Class<? extends T> beanType, char separator, int skipLines);

    <T> Stream<T> readCsv(InputStream csvStream, Class<? extends T> beanType, char separator, int skipLines);

    default <T> Stream<T> readCsv(URI csvPath, Class<? extends T> beanType, char separator) {
        return readCsv(csvPath, beanType, separator, 0);
    }

    default <T> Stream<T> readCsv(InputStream csvStream, Class<? extends T> beanType, char separator) {
        return readCsv(csvStream, beanType, separator, 0);
    }
}