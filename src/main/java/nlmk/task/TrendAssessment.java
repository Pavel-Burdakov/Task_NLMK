package nlmk.task;
import nlmk.task.exeception.NotValidDataException;
import nlmk.task.service.DataValidationService;
import nlmk.task.service.DeltaService;

import java.util.List;

public class TrendAssessment {
    public static void main(String args[]) throws NotValidDataException {
        String name = args[0];
        String delta = args[1];
        DataValidationService.dataValidation(name, delta);
        DeltaService.compare(name, Integer.parseInt(delta));
    }
}