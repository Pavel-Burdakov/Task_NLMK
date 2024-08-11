package nlmk.task.service;
import nlmk.task.model.ProductState;

import java.util.ArrayList;
import java.util.List;

public class DeltaService {
    /**
     * Метод для поиска записей о значимом отклонении значений в тренде
     *
     * @param fileName   имя документа
     * @param errorDelta значение, на которое должны отклониться значения тренда,
     *                   чтобы это вызвало нестабильность данных.
     *                   Отклоняющиеся от тренда знаения выводятся на консоль.
     */
    public static void compare(String fileName, int errorDelta) {
        List<ProductState> list = ExtractData.extractAllData(fileName);
        String[] headers = ExtractData.extractHeader(fileName);
        List<String> s = new ArrayList<>();
        for (int i = 0; i < list.size() - 1; i++) {
            ProductState m1 = list.get(i);
            ProductState m2 = list.get(i + 1);
            int delta1 = Math.abs(Integer.parseInt(m1.getSize()) - Integer.parseInt(m2.getSize()));
            int delta2 = Math.abs(Integer.parseInt(m1.getSize2()) - Integer.parseInt(m2.getSize2()));
            if (delta1 >= errorDelta) {
                String s1 = "Уровень металла в кристаллизаторе " + headers[1].split(":")[0] + " в " + m2.getDateTime() + " отличается от предыдущего на " + delta1 + " мм.";
                System.out.println(s);
                s.add(s1);
            }
            if (delta2 >= errorDelta) {
                String s1 = "Уровень металла в кристаллизаторе " + headers[2].split(":")[0] + " в " + m2.getDateTime() + " отличается от предыдущего на " + delta2 + " мм.";
                System.out.println(s1);
                s.add(s1);
            }
        }
       // return s;

    }

}
