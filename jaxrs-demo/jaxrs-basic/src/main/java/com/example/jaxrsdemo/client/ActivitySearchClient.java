package com.example.jaxrsdemo.client;

import com.example.jaxrsdemo.model.Activity;
import com.example.jaxrsdemo.model.ActivitySearch;

import java.net.URI;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

public class ActivitySearchClient {

    public static final String BASE_URL = "http://localhost:10080/webapi/";

    private Client client;

    public ActivitySearchClient() {
        client = ClientBuilder.newClient();
    }

    public List<Activity> search(ActivitySearch search) {
        URI uri = UriBuilder.fromUri(BASE_URL)
                            .path("search/activities")
                            .build();

        WebTarget target = client.target(uri);

        Response response = target.request(MediaType.APPLICATION_JSON)
                                  .post(Entity.entity(search, MediaType.APPLICATION_JSON));

        if (response.getStatus() != 200) {
            throw new RuntimeException(
                    response.getStatus() + ": there was an error on the server.");
        }

        return response.readEntity(new GenericType<List<Activity>>() {});
    }

    public List<Activity> search(String param, List<String> searchValues,
                                 String secondParam, int durationFrom,
                                 String thirdParam, int durationTo) {

        //http://localhost:8080/exercise-services/webapi//search/activities?description=swimming&description=running

        URI uri = UriBuilder.fromUri(BASE_URL)
                            .path("search/activities")
                            .queryParam(param, searchValues)
                            .queryParam(secondParam, durationFrom)
                            .queryParam(thirdParam, durationTo)
                            .build();

        WebTarget target = client.target(uri);

        List<Activity> response = target.request(MediaType.APPLICATION_JSON)
                                        .get(new GenericType<List<Activity>>() {});

        System.out.println(response);

        return response;

    }


}
