package gov.iti.jets.web.persistence.repository;

import gov.iti.jets.web.persistence.entities.Employee;
import gov.iti.jets.web.persistence.entities.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class PositionRepo extends GenericRepoImpl<Position,Integer>{
    public PositionRepo(EntityManager entityManager) {super(Position.class , entityManager);}

    public List<Employee> getAllEmployeeByPositionId(Integer positionId) {
        TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT c.employees FROM Position c WHERE c.id = :positionId", Employee.class);
        query.setParameter("positionId", positionId);
        List<Employee> employees = query.getResultList();
        return employees;
    }

}
