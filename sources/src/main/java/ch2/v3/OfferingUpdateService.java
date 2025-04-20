package ch2.v3;

public class OfferingUpdateService {
    public void update(Offering updatedOffering) {
        // ...
        // 개설 내용을 변경하는 로직
        // ...

        for(Employee employee : updatedOffering.getEmployees()) {
            if (employee.wantsAnyEmailUpdates() ||
                    (updatedOffering.isDateUpdated() ||
                            updatedOffering.isDescriptionUpdated())) { // 1

                // 직원들에게 변경된 내용을 알리는 이메일을 보낸다
            }
        }
    }
}
