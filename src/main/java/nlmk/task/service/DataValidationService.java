package nlmk.task.service;
import nlmk.task.exeception.NotValidDataException;
import nlmk.task.model.ProductState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Класс для проверки корректности данных для обработки
 */
public class DataValidationService {
    private static final Logger logger = LoggerFactory.getLogger(DataValidationService.class);
    private static final String valid1 = "Дата";
    private static final String valid2 = "Ручей";

    /**
     * Метода корректность переданных данных
     * корректность данных об уровне металла в кристаллизаторе и корректность значение уровня отклонения
     *
     * @param fileName - имя документа
     * @param delta    - значимое отклонение
     * @throws NotValidDataException исключение выбрасывается в случе если данные не корректны
     */
    public static void dataValidation(String fileName, String delta) throws NotValidDataException {
        String header[] = ExtractData.extractHeader(fileName);
        if (!header[0].contains(valid1) || !header[1].contains(valid2) || !header[2].contains(valid2)) {
            throw new NotValidDataException();
        }
        List<ProductState> list = null;
        list = ExtractData.extractAllData(fileName);
        for (ProductState item : list) {
            if (Integer.valueOf(item.getSize()) < 0 || Integer.valueOf(item.getSize2()) < 0) {
                logger.error("Данные в файле не корректны");
                throw new NotValidDataException();
            }
        }
        try {
            Integer.parseInt(delta);
        } catch (NumberFormatException e) {
            System.out.println("неверно ввден уровень отклонения");
            throw new NotValidDataException();
        }
    }
}
