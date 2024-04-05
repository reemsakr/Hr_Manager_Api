package gov.iti.jets.web.persistence.repository;

import gov.iti.jets.web.persistence.entities.Attendance;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

public class AttendanceRepo extends GenericRepoImpl<Attendance,Integer>{
    public AttendanceRepo(EntityManager entityManager) {super(Attendance.class , entityManager);}

    public Optional<Long> getAttendanceInYear(Integer employeeId,int year) {
        try {
            TypedQuery<Long> query = entityManager.createQuery(
                    "SELECT COUNT(a) " +
                            "FROM Attendance a " +
                            "WHERE a.employee.id = :employeeId " +
                            "AND YEAR(a.attendanceDate) = :year", Long.class);
            query.setParameter("employeeId", employeeId);
            query.setParameter("year", year);
            Long count = query.getSingleResult();
            return Optional.of(count);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
