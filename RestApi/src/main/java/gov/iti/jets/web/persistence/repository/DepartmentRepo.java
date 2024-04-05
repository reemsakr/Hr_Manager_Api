package gov.iti.jets.web.persistence.repository;

import gov.iti.jets.web.persistence.entities.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

public class DepartmentRepo extends GenericRepoImpl<Department,Integer>{
    public DepartmentRepo(EntityManager entityManager) {super(Department.class , entityManager);}

    public Optional<Department> getDepartmentByName(String departmentName) {
        try {
            TypedQuery<Department> query = entityManager.createQuery(
                    "SELECT c FROM Department c WHERE c.name = :departmentName", Department.class);
            query.setParameter("departmentName", departmentName);
            Department department = query.getSingleResult();
            return Optional.of(department);
        } catch (NoResultException e) {
            System.out.println("Department with name " + departmentName + " not found."); // Print for debugging
            return Optional.empty();
        }
    }
}
