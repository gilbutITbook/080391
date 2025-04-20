package ch6.v1;

import java.util.ArrayList;
import java.util.List;

public class AddEmployeeToOfferingValidator {

    private TrainingRepository trainings;

    public ValidationResult validate(
            Offering offering,
            Employee employee) {

        var validation = new ValidationResult();

        if (offering.hasAvailableSpots()) {           // 1
            validation.addError("Offering has no available spots.");
        }

        var timesParticipantTookTheTraining =
                trainings.countParticipations(employee, offering.getTraining());

        if (timesParticipantTookTheTraining >= 3) {    // 2
            validation.addError("Participant can't take the training again.");
        }
        if (offering.isEmployeeRegistered(employee)) {
            validation.addError("Participant already in this offering.");
        }

        return validation;
    }

    static public class ValidationResult {
        List<String> errors = new ArrayList<>();

        public boolean hasErrors() {
            // 검증 로직
            return !errors.isEmpty();
        }

        public void addError(String s) {
            errors.add(s);
        }
    }
}
