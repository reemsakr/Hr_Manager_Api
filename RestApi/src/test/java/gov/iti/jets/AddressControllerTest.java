package gov.iti.jets;

import gov.iti.jets.web.model.requestBody.AddressBodyReq;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class AddressControllerTest {
    String url = "http://localhost:9090/HrRestApi/webapi/addresses/v1";

    @Test
    public void TestGetAllAddresses() {
        Client client = ClientBuilder.newClient();
        Response response = client.target(url)
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(200, response.getStatus());
    }
    @Test
    public void TestAddressById() {
        Client client = ClientBuilder.newClient();
        Response response = client.target(url)
                .path("{id:[0-9]+}")
                .resolveTemplate("id", 7)
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void TestAddAddress() {
        Client client = ClientBuilder.newClient();
        AddressBodyReq addressBodyReq = new AddressBodyReq("street1","city1","12345",8);
        Response response = client.target(url)
                .request()
                .post(Entity.entity(addressBodyReq, MediaType.APPLICATION_JSON));
        assertEquals(201, response.getStatus());
    }
    @Test
    public void TestUpdateAddress() {
        Client client = ClientBuilder.newClient();
        AddressBodyReq addressBodyReq = new AddressBodyReq("street1","city11","12345",8);
        Response response = client.target(url)
                .path("{id:[0-9]+}")
                .resolveTemplate("id", 15)
                .request()
                .put(Entity.entity(addressBodyReq, MediaType.APPLICATION_JSON));
        assertEquals(200, response.getStatus());
    }
    @Test
    public void TestDeleteAddress() {
        Client client = ClientBuilder.newClient();
        Response response = client.target(url)
                .path("{id:[0-9]+}")
                .resolveTemplate("id", 2)
                .request()
                .delete();
        assertEquals(204, response.getStatus());
    }

}
