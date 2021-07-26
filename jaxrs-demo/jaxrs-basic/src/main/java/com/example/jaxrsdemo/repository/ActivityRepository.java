package com.example.jaxrsdemo.repository;

import java.util.List;

import com.example.jaxrsdemo.model.Activity;
import com.example.jaxrsdemo.model.ActivitySearch;

public interface ActivityRepository {

    List<Activity> findAllActivities();

    Activity findActivity(String activityId);

    void create(Activity activity);

    Activity update(Activity activity);

    void delete(String activityId);

    List<Activity> findByDescription(List<String> descriptions, int durationFrom, int durationTo);

    List<Activity> findByConstraints(ActivitySearch search);
}
