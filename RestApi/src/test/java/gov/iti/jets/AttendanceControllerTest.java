package gov.iti.jets;

import gov.iti.jets.web.model.requestBody.AddressBodyReq;
import gov.iti.jets.web.model.requestBody.AttendanceBodyReq;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttendanceControllerTest {
    String url = "http://localhost:9090/HrRestApi/webapi/attendances/v1";

    @Test
    public void TestGetAllAttendances() {
        Client client = ClientBuilder.newClient();
        Response response = client.target(url)
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(200, response.getStatus());
    }
    @Test
    public void TestAttendanceById() {
        Client client = ClientBuilder.newClient();
        Response response = client.target(url)
                .path("{id:[0-9]+}")
                .resolveTemplate("id", 7)
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void TestAddAttendance() {
        Client client = ClientBuilder.newClient();
        AttendanceBodyReq attendanceBodyReq = new AttendanceBodyReq(new Date(), LocalTime.of(8, 30),LocalTime.of(17, 0),2);
        Response response = client.target(url)
                .request()
                .post(Entity.entity(attendanceBodyReq, MediaType.APPLICATION_JSON));
        assertEquals(201, response.getStatus());
    }
    @Test
    public void TestUpdateAttendance() {
        Client client = ClientBuilder.newClient();
        AttendanceBodyReq attendanceBodyReq = new AttendanceBodyReq(new Date(), LocalTime.of(8, 30),LocalTime.of(17, 0),2);
        Response response = client.target(url)
                .path("{id:[0-9]+}")
                .resolveTemplate("id", 13)
                .request()
                .put(Entity.entity(attendanceBodyReq, MediaType.APPLICATION_JSON));
        assertEquals(200, response.getStatus());
    }
    @Test
    public void TestDeleteAttendance() {
        Client client = ClientBuilder.newClient();
        Response response = client.target(url)
                .path("{id:[0-9]+}")
                .resolveTemplate("id", 10)
                .request()
                .delete();
        assertEquals(204, response.getStatus());
    }
}
