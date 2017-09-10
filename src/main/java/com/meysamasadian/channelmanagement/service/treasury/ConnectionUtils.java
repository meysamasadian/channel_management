package com.meysamasadian.channelmanagement.service.treasury;

import com.meysamasadian.channelmanagement.exception.BusinessException;
import org.glassfish.jersey.client.ClientProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.Properties;

/**
 * Created by rahnema on 9/6/2017.
 */

public class ConnectionUtils {


    private static final String BASE_URL = "http://localhost:8081/treasury";
    private static final int CONNECT_TIMEOUT = 30000;
    private static final int READ_TIMEOUT = 30000;


    public static Object request(Path path, Class clazz ,String pathVariable ,Object requestData) throws BusinessException {
        Entity entity = Entity.entity(requestData, MediaType.APPLICATION_JSON);
        return connect(path, entity, pathVariable, clazz);
    }

    public static Object transact(Path path, Class clazz ,String pathVariable ,Object requestData)  {
        Entity entity = Entity.entity(requestData, MediaType.APPLICATION_JSON);
        return transact(path, entity, pathVariable, clazz);
    }

    private static<T> T transact(Path path, Entity entity, String pathVariable, Class<T> t)  {
        Client client = ClientBuilder.newClient();
        client.property(ClientProperties.CONNECT_TIMEOUT,
                CONNECT_TIMEOUT);
        client.property(ClientProperties.READ_TIMEOUT,
                READ_TIMEOUT);
        WebTarget target = client.target(urlCreator(path,pathVariable));
        Response response = entity != null ? target.request().
                accept(MediaType.APPLICATION_JSON).
                post(entity) : target.request().
                post(null);
        return response.readEntity(t);
    }


    private static<T> T connect(Path path, Entity entity, String pathVariable, Class<T> t) throws BusinessException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(urlCreator(path,pathVariable));
        Response response = null;
        response = entity != null ? target.request().
                accept(MediaType.APPLICATION_JSON).
                post(entity) : target.request().
                post(null);
        if (response.getStatus() != 200) {
            throw new BusinessException(BusinessException.TREASURY_PROCESS_NOT_COMPLETE);
        }
        return response.readEntity(t);
    }

    private static String urlCreator(Path path,String pathVariable) {
        String raw = BASE_URL.concat(path.toString());
        String result = pathVariable != null ? raw.concat(pathVariable) : raw;
        return result;
    }

    private static Properties properties() {
        try {
            Properties properties = new Properties();
            properties.load(ClassLoader.getSystemResourceAsStream("application.properties"));
            return properties;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
