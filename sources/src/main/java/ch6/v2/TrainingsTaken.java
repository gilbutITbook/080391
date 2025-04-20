package ch6.v2;

import java.time.LocalDate;
import java.util.List;

public class TrainingsTaken {
    private List<Training> trainings;

    public boolean has(String trainingName) {
        return trainings.stream().anyMatch(training -> training.getName().equals(trainingName));
    }

    public long trainingsInPast3Months() {
        return trainings.stream().filter(
                training -> training.getEndDate().isAfter(LocalDate.now().minusMonths(3))
        ).count();
    }

    public int totalTrainings() {
        return trainings.size();
    }
}
