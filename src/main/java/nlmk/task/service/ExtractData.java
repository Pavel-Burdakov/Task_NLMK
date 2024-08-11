package nlmk.task.service;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import nlmk.task.model.ProductState;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Класс предназначен для парсинга .csv документа, содержащего информацию о трендах
 * уровня металла в кристаллизаторе в двух ручьях и моментом времени в который имеется
 * значение тренда.
 */
public class ExtractData {
    private static final Logger logger = LoggerFactory.getLogger(ExtractData.class);

    /**
     * Метод извлекает данные из .csv документs
     *
     * @param fileName - имя документа с данными;
     * @return - массив объектов, содержащих даанные из файла;
     * @ProductState.clsss - класс описывающи поля документа.
     */
    public static List<ProductState> extractAllData(String fileName) {
        Reader myReader = null;
        try {
            myReader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
            throw new RuntimeException();
        }
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(ProductState.class)
                .withColumnSeparator(';').withSkipFirstDataRow(true);
        MappingIterator<ProductState> iterator = null;
        List<ProductState> elements = null;
        try {
            iterator = mapper
                    .readerFor(ProductState.class)
                    .with(schema)
                    .readValues(myReader);
            elements = iterator.readAll();
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException();
        }
        return elements;
    }

    /**
     * Метод извлекает заголовок из .csv докумнта
     *
     * @param fileName имя документа с данными;
     * @return массив строк с заголовками документа
     */
    public static String[] extractHeader(String fileName) {
        InputStream stream = null;
        try {
            stream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        }
        CSVReader reader = null;
        try {
            reader = new CSVReader(new InputStreamReader(stream, "Windows-1251"));
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }
        String[] strings = new String[0];
        try {
            strings = new String[]{Arrays.toString(reader.readNext())};
        } catch (CsvValidationException | IOException e) {
            logger.error(e.getMessage());
        }
        return strings[0].split(";");
    }
}
