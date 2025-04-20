package ch3.v1;

public class AddEmployeeToOfferingService {
    private OfferingRepository offerings;

    AddEmployeeToOfferingService(OfferingRepository offerings) {
        this.offerings = offerings;
    }

    void enroll(int offeringId, Employee employeeThatWantsToParticipate) {
        var offering = offerings.getOfferingFrom(offeringId);              // 1

        if(offering.getAvailableSpots() > 0) {              // 2
            offering.getEmployees().add(employeeThatWantsToParticipate);  // 3
            offering.setAvailableSpots(offering.getAvailableSpots() - 1); // 4
        }
    }
}
