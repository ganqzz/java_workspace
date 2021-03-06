package com.example.jaxrsdemo.client;

import com.example.jaxrsdemo.model.Activity;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ActivityClient {

    public static final String BASE_URL = "http://localhost:10080/webapi/";

    private Client client;

    public ActivityClient() {
        client = ClientBuilder.newClient();
    }

    public void delete(String activityId) {
        WebTarget target = client.target(BASE_URL);

        Response response = target.path("activities/" + activityId)
                                  .request(MediaType.APPLICATION_JSON)
                                  .delete();

        if (response.getStatus() != 200) { // 204
            throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
        }
    }

    public Activity get(String id) {
        //http://localhost:8080/exercise-services/webapi/activities/1234
        WebTarget target = client.target(BASE_URL);

        Response response = target.path("activities/" + id)
                                  .request(MediaType.APPLICATION_JSON)
                                  .get(Response.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException(
                    response.getStatus() + ": there was an error on the server.");
        }

        return response.readEntity(Activity.class);
    }

    public List<Activity> get() {
        WebTarget target = client.target(BASE_URL);

        List<Activity> response = target.path("activities")
                                        .request(MediaType.APPLICATION_JSON)
                                        .get(new GenericType<List<Activity>>() {});

        return response;
    }

    public Activity create(Activity activity) {
        //http://localhost:8080/exercise-services/webapi/activities/activity
        WebTarget target = client.target(BASE_URL);

        Response response = target.path("activities/activity")
                                  .request(MediaType.APPLICATION_JSON)
                                  .post(Entity.entity(activity, MediaType.APPLICATION_JSON));

        if (response.getStatus() != 200) {
            throw new RuntimeException(
                    response.getStatus() + ": there was an error on the server.");
        }

        return response.readEntity(Activity.class);
    }

    public Activity update(Activity activity) {
        WebTarget target = client.target(BASE_URL);

        Response response = target.path("activities/" + activity.getId())
                                  .request(MediaType.APPLICATION_JSON)
                                  .put(Entity.entity(activity, MediaType.APPLICATION_JSON));

        if(response.getStatus() != 200) {
            throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
        }

        return response.readEntity(Activity.class);
    }
}
