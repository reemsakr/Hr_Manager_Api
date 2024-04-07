package gov.iti.jets;

import gov.iti.jets.web.model.dto.DepartmentDto;
import gov.iti.jets.web.model.dto.EmployeeDto;
import gov.iti.jets.web.model.requestBody.DepartmentBodyReq;
import gov.iti.jets.web.model.requestBody.PositionBodyReq;
import gov.iti.jets.web.persistence.repository.PositionRepo;
import gov.iti.jets.web.service.DepartmentServices;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionControllerTest {

    String url = "http://localhost:9090/HrRestApi/webapi/positions/v1";

    @Test
    public void TestGetAllPositions() {
        Client client = ClientBuilder.newClient();
        Response response = client.target(url)
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(200, response.getStatus());
    }
    @Test
    public void TestPositionById() {
        Client client = ClientBuilder.newClient();
        Response response = client.target(url)
                .path("{id:[0-9]+}")
                .resolveTemplate("id", 1)
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void TestAddPosition() {
        Client client = ClientBuilder.newClient();
        PositionBodyReq positionBodyReq = new PositionBodyReq("backend developer","software engineer");
        Response response = client.target(url)
                .request()
                .post(Entity.entity(positionBodyReq, MediaType.APPLICATION_JSON));
        assertEquals(201, response.getStatus());
    }
    @Test
    public void TestUpdatePosition() {
        Client client = ClientBuilder.newClient();
        PositionBodyReq positionBodyReq = new PositionBodyReq("frontend developer","software engineer");
        Response response = client.target(url)
                .path("{id:[0-9]+}")
                .resolveTemplate("id", 3)
                .request()
                .put(Entity.entity(positionBodyReq, MediaType.APPLICATION_JSON));
        assertEquals(200, response.getStatus());
    }

    @Test
    public void TestAllEmployeeByPositionId() {
        Client client = ClientBuilder.newClient();
        Response response = client.target(url)
                .path("{id:[0-9]+}/employees")
                .resolveTemplate("id", 3)
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(200, response.getStatus());
    }
}
