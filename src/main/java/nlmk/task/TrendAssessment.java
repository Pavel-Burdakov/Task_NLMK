package nlmk.task;
import nlmk.task.exeception.NotValidDataException;
import nlmk.task.service.DataValidationService;
import nlmk.task.service.DeltaService;

public class TrendAssessment {
    public static void main(String args[]) throws NotValidDataException {
        String name = args[0];
        String delta = args[1];
        DataValidationService.dataValidation(name);
        DeltaService.compare(name, Integer.parseInt(delta));
    }
}