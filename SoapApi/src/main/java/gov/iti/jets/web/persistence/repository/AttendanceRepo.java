package gov.iti.jets.web.persistence.repository;

import gov.iti.jets.web.persistence.entities.Attendance;
import jakarta.persistence.EntityManager;

public class AttendanceRepo extends GenericRepoImpl<Attendance,Integer>{
    public AttendanceRepo(EntityManager entityManager) {super(Attendance.class , entityManager);}

}
