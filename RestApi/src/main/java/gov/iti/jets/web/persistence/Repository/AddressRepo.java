package gov.iti.jets.web.persistence.Repository;

import gov.iti.jets.web.persistence.entities.Address;
import gov.iti.jets.web.persistence.entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

public class AddressRepo extends GenericRepoImpl<Address,Integer>{
    public AddressRepo(EntityManager entityManager) {super(Address.class , entityManager);}

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
}
