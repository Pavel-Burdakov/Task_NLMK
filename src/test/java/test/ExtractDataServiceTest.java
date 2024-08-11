package test;
import com.opencsv.exceptions.CsvValidationException;
import nlmk.task.TrendAssessment;
import nlmk.task.exeception.NotValidDataException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Сквозные тесты
 * public void testDataNotValid() - тест, предусматривающий некорректные данные в файле
 * public void testNoSuchFile() - тест, предусматривающий отсутсвие фала
 * public void correctData() - тест, предусматривающий корректное исполнене программы
 */
public class ExtractDataServiceTest {
    @Test
    public void testDataNotValid() {
        assertThrows(NotValidDataException.class, () -> TrendAssessment.main(new String[]{"src/test/resources/BadData.csv", "8"}));
    }

    @Test
    public void testNoSuchFile() {
        assertThrows(RuntimeException.class, () -> TrendAssessment.main(new String[]{"noFile.csv", "8"}));
    }

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void correctData() throws Exception {
        TrendAssessment.main(new String[]{"src/test/resources/Data.csv", "17"});
        Assert.assertEquals("Уровень металла в кристаллизаторе Ручей 8 в 01.02.2022 16:05 отличается от предыдущего на 17 мм.", systemOutRule.getLog()
                .trim());
    }
}






