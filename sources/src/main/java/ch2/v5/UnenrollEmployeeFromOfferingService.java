package ch2.v5;

import java.util.Set;

class UnenrollEmployeeFromOfferingService {

    private Emailer emailer;
    private OfferingRepository offerings;

    public UnenrollEmployeeFromOfferingService( /* ..., */
                                               OfferingRepository offerings,
                                               Emailer emailer) {
        this.offerings = offerings;
        this.emailer = emailer;                             // 1
    }

    public void unenroll(int enrollmentId) {
        // ...
        // 직원 등록 취소 로직
        // ...
        Offering offering = offerings.getOfferingFrom(enrollmentId);
        notifyWaitingList(offering);                       // 2
    }

    private void notifyWaitingList(Offering offering) { // 3
        Set<Employee> employees = offering.getWaitingList();
        for (Employee employee : employees) {
            emailer.sendWaitingListEmail(offering, employee);
        }
    }
}