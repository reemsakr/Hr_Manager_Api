package gov.iti.jets.web.persistence.repository;

import gov.iti.jets.web.persistence.entities.Address;
import gov.iti.jets.web.persistence.entities.Attendance;
import gov.iti.jets.web.persistence.entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class EmployeeRepo extends GenericRepoImpl<Employee,Integer>{
    public EmployeeRepo(EntityManager entityManager) {super(Employee.class , entityManager);}

    public Optional<Employee> getEmployeeByEmail(String email) {
        try {
            TypedQuery<Employee> query = entityManager.createQuery(
                    "SELECT c FROM Employee c WHERE c.email = :email", Employee.class);
            query.setParameter("email", email);
            Employee employee = query.getSingleResult();
            return Optional.of(employee);
        } catch (NoResultException e) {
            System.out.println("Employee with email " + email + " not found."); // Print for debugging
            return Optional.empty();
        }
    }

    public Optional<Address> getAddressByEmployeeId(Integer employeeId) {
        try {
            TypedQuery<Address> query = entityManager.createQuery(
                    "SELECT c FROM Address c WHERE c.employee.id = :employeeId", Address.class);
            query.setParameter("employeeId", employeeId);
            Address address = query.getSingleResult();
            return Optional.of(address);
        } catch (NoResultException e) {
            System.out.println("Employee with id " + employeeId + " not found."); // Print for debugging
            return Optional.empty();
        }
    }
    public List<Attendance> getAttendanceInYearByEmployeeId(Integer employeeId,int year) {
            TypedQuery<Attendance> query = entityManager.createQuery(
                    "SELECT a " +
                            "FROM Attendance a " +
                            "WHERE a.employee.id = :employeeId " +
                            "AND YEAR(a.attendanceDate) = :year", Attendance.class);
            query.setParameter("employeeId", employeeId);
            query.setParameter("year", year);
            List<Attendance> attendances = query.getResultList();
            return attendances ;
    }
}
