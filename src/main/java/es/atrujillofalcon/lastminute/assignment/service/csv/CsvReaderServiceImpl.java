package es.atrujillofalcon.lastminute.assignment.service.csv;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import com.opencsv.exceptions.CsvBadConverterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author Arnaldo Trujillo
 */
@Service
public class CsvReaderServiceImpl implements CsvReaderService {

    private static final Logger LOG = LoggerFactory.getLogger(CsvReaderServiceImpl.class);

    @Override
    public <T> Stream<T> readCsv(URI csvPath, Class<? extends T> beanType, char separator, int skipLines) {

        LOG.info("READING CSV: " + csvPath.toString());

        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvPath));

            return applyReader(reader, beanType, separator, skipLines);

        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            return Stream.empty();
        }
    }

    @Override
    public <T> Stream<T> readCsv(InputStream csvStream, Class<? extends T> beanType, char separator, int skipLines) {
        Reader reader = new InputStreamReader(csvStream);

        return applyReader(reader, beanType, separator, skipLines);
    }

    private <T> Stream<T> applyReader(Reader reader, Class<? extends T> beanType, char separator, int skipLines) {
        try {

            CsvToBeanBuilder<T> csvBeanBuilder = new CsvToBeanBuilder<T>(reader)
                    .withType(beanType)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSkipLines(skipLines)
                    .withFieldAsNull(CSVReaderNullFieldIndicator.BOTH)
                    .withSeparator(separator);

            CsvToBean<T> csvToBean = csvBeanBuilder.build();

            csvToBean.getCapturedExceptions().forEach(e -> LOG.warn(e.getMessage(), e));

            return StreamSupport.stream(csvToBean.spliterator(), false);

        } catch (CsvBadConverterException | IllegalStateException e) {
            LOG.error(e.getMessage(), e);
            return Stream.empty();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                LOG.error("Error closing csv resource", e);
            }
        }
    }
}