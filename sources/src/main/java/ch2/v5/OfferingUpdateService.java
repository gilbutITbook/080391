package ch2.v5;

public class OfferingUpdateService {
    public void update(Offering updatedOffering) {
        // ...
        // 개설 내용을 변경하는 로직
        // ...

        for(Employee employee : updatedOffering.getEmployees()) {
            if(shouldReceiveAnEmail(updatedOffering, employee)) {
                // 변경 내용을 수강자들에게 보낸다
            }
        }
    }

    /**
     * 직원들은 이메일을 받도록 등록한 경우나
     * 중요한 정보가 변경된 경우 이메일을 받아야 한다.
     */
    boolean shouldReceiveAnEmail(Offering updatedOffering,
                                 Employee employee) {

        boolean importantInfoWasUpdated = updatedOffering.isImportantInfoUpdated();
        boolean employeeWantsUpdates = employee.wantsAnyEmailUpdates();

        return employeeWantsUpdates || importantInfoWasUpdated;
    }

}
