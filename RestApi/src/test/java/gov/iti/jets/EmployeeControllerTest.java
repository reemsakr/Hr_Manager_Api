package gov.iti.jets;

import gov.iti.jets.web.model.requestBody.EmployeeBodyReq;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeControllerTest {
    String url = "http://localhost:9090/HrRestApi/webapi/employees/v1";

    @Test
    public void TestGetAllEmployees() {
        Client client = ClientBuilder.newClient();
        Response response = client.target(url)
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(200, response.getStatus());
    }
    @Test
    public void TestEmployeeById() {
        Client client = ClientBuilder.newClient();
        Response response = client.target(url)
                .path("{id:[0-9]+}")
                .resolveTemplate("id", 1)
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(200, response.getStatus());
    }
    @Test
    public void TestAddEmployee() {
        Client client = ClientBuilder.newClient();
        EmployeeBodyReq employeeBodyReq = new EmployeeBodyReq("shrouk","gamal","shrouk@gmail.com", LocalDate.of(2001,4,12),"Female",LocalDate.of(2023,5,15),1,1,new BigDecimal(10000));
        Response response = client.target(url)
                .request()
                .post(Entity.entity(employeeBodyReq, MediaType.APPLICATION_JSON));
        assertEquals(201, response.getStatus());
    }
    @Test
    public void TestUpdateEmployee() {
        Client client = ClientBuilder.newClient();
        EmployeeBodyReq employeeBodyReq = new EmployeeBodyReq("shrouk","gamal","shrouk@gmail.com", LocalDate.of(2001,4,12),"Female",LocalDate.of(2023,5,15),1,1,new BigDecimal(5000));
        Response response = client.target(url)
                .path("{id:[0-9]+}")
                .resolveTemplate("id", 10)
                .request()
                .put(Entity.entity(employeeBodyReq, MediaType.APPLICATION_JSON));
        assertEquals(200, response.getStatus());
    }
    @Test
    public void TestAddressByEmployeeId() {
        Client client = ClientBuilder.newClient();
        Response response = client.target(url)
                .path("address/{id:[0-9]+}")
                .resolveTemplate("id", 1)
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(200, response.getStatus());
    }
    @Test
    public void TestAttendanceInYearByEmployeeId() {
        Client client = ClientBuilder.newClient();
        Response response = client.target(url)
                .path("AttendanceInYearByEmployeeId")
                .resolveTemplate("id", 1)
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(200, response.getStatus());
    }
}
