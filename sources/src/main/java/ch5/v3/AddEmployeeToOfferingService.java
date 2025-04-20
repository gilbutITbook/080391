package ch5.v3;

public class AddEmployeeToOfferingService {
    private OfferingRepository offerings;
    private EmployeeRepository employees;
    private AddEmployeeToOfferingValidator validator;

    AddEmployeeToOfferingService(OfferingRepository offerings,
                                 EmployeeRepository employees,
                                 AddEmployeeToOfferingValidator validator) {
        this.offerings = offerings;
    }

    public void addEmployee(int offeringId, String employeeEmail) {
        var offeringOpt = offerings.findById(offeringId);
        var employeeOpt = employees.findByEmail(employeeEmail);

        if(offeringOpt.isEmpty() || employeeOpt.isEmpty())
            throw new InvalidRequestException(
                    "Offering and employee IDs should be valid");

        var offering = offeringOpt.get();
        var employee = employeeOpt.get();

        var validation = validator.validate(offering, employee);
        if(validation.hasErrors()) {
            throw new ValidationException(validation);
        }

        offering.enroll(employee);
    }
}
