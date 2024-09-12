package com.aitripplanner.AiTripPlanner.Services;

import com.aitripplanner.AiTripPlanner.Entites.Activity;
import com.aitripplanner.AiTripPlanner.Repository.ActivityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public Optional<Activity> getActivityById(Integer id) {
        return activityRepository.findById(id);
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public Activity updateActivity(Integer id, Activity activity) {
        if (activityRepository.existsById(id)) {
            activity.setId(id);
            return activityRepository.save(activity);
        }
        return null;
    }

    public void deleteActivity(Integer id) {
        activityRepository.deleteById(id);
    }
}
