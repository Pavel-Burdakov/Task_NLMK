package nlmk.task.model;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonPropertyOrder({"Date, time", "Stream_numder1 size", "Stream_numder2 size"})
@AllArgsConstructor
@NoArgsConstructor
@ToString
/**
 * Класс, описывающий поля обрабатываемого документа
 * private String dateTime - Дата и время в тренде
 * size / size2 - уровни метлла в кристаллизаторе в ручьях
 */
public class ProductState {
    private String dateTime;
    private String size;
    private String size2;

    public String getDateTime() {
        return dateTime;
    }

    public String getSize() {
        return size;
    }

    public String getSize2() {
        return size2;
    }
}
