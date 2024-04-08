package gov.iti.jets.web.persistence.repository;

import gov.iti.jets.web.persistence.entities.Department;
import gov.iti.jets.web.persistence.entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class DepartmentRepo extends GenericRepoImpl<Department,Integer>{
    public DepartmentRepo(EntityManager entityManager) {super(Department.class , entityManager);}

    public List<Employee> getAllEmployeeByDepartmentId(Integer departmentId) {
            TypedQuery<Employee> query = entityManager.createQuery(
                    "SELECT c.employees FROM Department c WHERE c.id = :departmentId", Employee.class);
            query.setParameter("departmentId", departmentId);
            List<Employee> employees = query.getResultList();
            return employees;
    }
}
