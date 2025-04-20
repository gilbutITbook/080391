package ch2.v6;

import java.util.Set;

class UnenrollEmployeeFromOfferingService {
    private OfferingRepository offerings;
    private WaitingListNotifier notifier;           // 1

    public UnenrollEmployeeFromOfferingService( /*...,*/
                                               OfferingRepository offerings,
                                               WaitingListNotifier notifier) {
        this.offerings = offerings;
        this.notifier = notifier;
    }

    public void unenroll(int enrollmentId) {
        // ...
        // 직원 등록 취소 로직
        // ...
        Offering offering = offerings.getOfferingFrom(enrollmentId);
        notifier.notify(offering);                     // 2
    }
}

class WaitingListNotifier {
    private Emailer emailer;

    public WaitingListNotifier(Emailer emailer) {    // 3
        this.emailer = emailer;
    }

    public void notify(Offering offering) {          // 4
        Set<Employee> employees = offering.getWaitingList();
        for (Employee employee : employees) {
            emailer.sendWaitingListEmail(offering, employee);
        }
    }
}