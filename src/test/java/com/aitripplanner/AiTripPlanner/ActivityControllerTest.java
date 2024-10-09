package com.aitripplanner.AiTripPlanner;

import com.aitripplanner.AiTripPlanner.Controller.ActivityController;
import com.aitripplanner.AiTripPlanner.Entites.Activity;
import com.aitripplanner.AiTripPlanner.Services.ActivityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ActivityControllerTest {

    @Mock
    private ActivityService activityService;

    @InjectMocks
    private ActivityController activityController;

    private Activity activity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        activity = new Activity();
        activity.setId(1);
        activity.setName("Sample Activity");
    }

    @Test
    public void testCreateActivity() {
        when(activityService.createActivity(activity)).thenReturn(activity);
        ResponseEntity<Activity> response = activityController.createActivity(activity);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(activity, response.getBody());
    }

    @Test
    public void testGetActivityById_Exists() {
        when(activityService.getActivityById(1)).thenReturn(Optional.of(activity));
        ResponseEntity<Activity> response = activityController.getActivityById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(activity, response.getBody());
    }

    @Test
    public void testGetActivityById_NotExists() {
        when(activityService.getActivityById(1)).thenReturn(Optional.empty());
        ResponseEntity<Activity> response = activityController.getActivityById(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetAllActivities() {
        List<Activity> activities = new ArrayList<>();
        activities.add(activity);
        when(activityService.getAllActivities()).thenReturn(activities);
        ResponseEntity<List<Activity>> response = activityController.getAllActivities();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(activity, response.getBody().get(0));
    }

    @Test
    public void testUpdateActivity_Exists() {
        when(activityService.updateActivity(1, activity)).thenReturn(activity);
        ResponseEntity<Activity> response = activityController.updateActivity(1, activity);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(activity, response.getBody());
    }

    @Test
    public void testUpdateActivity_NotExists() {
        when(activityService.updateActivity(1, activity)).thenReturn(null);
        ResponseEntity<Activity> response = activityController.updateActivity(1, activity);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteActivity() {
        ResponseEntity<Void> response = activityController.deleteActivity(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(activityService, times(1)).deleteActivity(1);
    }
}
