package gov.iti.jets.web.persistence.Repository;

import gov.iti.jets.web.persistence.entities.Address;
import gov.iti.jets.web.persistence.entities.Department;
import gov.iti.jets.web.persistence.entities.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

public class PositionRepo extends GenericRepoImpl<Position,Integer>{
    public PositionRepo(EntityManager entityManager) {super(Position.class , entityManager);}

    public Optional<Position> getPositionByTitle(String title) {
        try {
            TypedQuery<Position> query = entityManager.createQuery(
                    "SELECT c FROM Position c WHERE c.title = :title", Position.class);
            query.setParameter("title", title);
            Position position = query.getSingleResult();
            return Optional.of(position);
        } catch (NoResultException e) {
            System.out.println("Position with title " + title + " not found."); // Print for debugging
            return Optional.empty();
        }
    }
}
