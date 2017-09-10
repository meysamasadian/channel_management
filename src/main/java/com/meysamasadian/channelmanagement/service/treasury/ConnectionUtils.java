package com.meysamasadian.channelmanagement.service.treasury;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by rahnema on 9/6/2017.
 */
public class ConnectionUtils {
    private static final String BASE_URL = "http://localhost:8080/treasury/api";
    public static Object request(Path path, String pathVariable ,Object requestData) {
        Entity entity = Entity.entity(requestData, MediaType.APPLICATION_JSON_TYPE);
        return connect(path, entity);
    }

    private static Object connect(Path path, Entity entity) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(urlCreator(path));
        Response response = null;
        response = target.request().
                accept(MediaType.APPLICATION_JSON).
                post(entity);
        return response.getEntity();
    }

    private static String urlCreator(Path path) {
        return BASE_URL.concat(path.toString());
    }
}
