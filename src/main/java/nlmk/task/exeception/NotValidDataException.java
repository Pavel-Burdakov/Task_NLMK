package nlmk.task.exeception;
/**
 * исключение выбрасываемое, в случае если данные не корректны
 */
public class NotValidDataException extends Exception {
    public NotValidDataException() {
        super("Введенные данные некорректны");
    }
}
