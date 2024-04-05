package gov.iti.jets.web.persistence.repository;

import gov.iti.jets.web.persistence.entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

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
}
