package gov.iti.jets;

import gov.iti.jets.web.model.requestBody.DepartmentBodyReq;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepartmentControllerTest {
    String url = "http://localhost:9090/HrRestApi/webapi/departments/v1";

    @Test
    public void TestGetAllDepartments() {
        Client client = ClientBuilder.newClient();
        Response response = client.target(url)
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(200, response.getStatus());
    }
    @Test
    public void TestDepartmentById() {
        Client client = ClientBuilder.newClient();
        Response response = client.target(url)
                .path("{id:[0-9]+}")
                .resolveTemplate("id", 1)
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void TestAddDepartment() {
        Client client = ClientBuilder.newClient();
        DepartmentBodyReq departmentBodyReq = new DepartmentBodyReq("OR");
        Response response = client.target(url)
                .request()
                .post(Entity.entity(departmentBodyReq, MediaType.APPLICATION_JSON));
        assertEquals(201, response.getStatus());
    }
    @Test
    public void TestUpdateDepartment() {
        Client client = ClientBuilder.newClient();
        DepartmentBodyReq departmentBodyReq = new DepartmentBodyReq("JAVA");
        Response response = client.target(url)
                .path("{id:[0-9]+}")
                .resolveTemplate("id", 3)
                .request()
                .put(Entity.entity(departmentBodyReq, MediaType.APPLICATION_JSON));
        assertEquals(200, response.getStatus());
    }

    @Test
    public void TestAllEmployeeByDepartmentId() {
        Client client = ClientBuilder.newClient();
        Response response = client.target(url)
                .path("{id:[0-9]+}/employees")
                .resolveTemplate("id", 3)
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(200, response.getStatus());
    }

}
