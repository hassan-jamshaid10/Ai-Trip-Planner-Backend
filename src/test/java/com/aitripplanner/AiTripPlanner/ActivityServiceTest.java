package com.aitripplanner.AiTripPlanner;

import com.aitripplanner.AiTripPlanner.Entites.Activity;
import com.aitripplanner.AiTripPlanner.Repository.ActivityRepository;
import com.aitripplanner.AiTripPlanner.Services.ActivityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ActivityServiceTest {

    @Mock
    private ActivityRepository activityRepository;

    @InjectMocks
    private ActivityService activityService;

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
        when(activityRepository.save(activity)).thenReturn(activity);
        Activity createdActivity = activityService.createActivity(activity);
        assertEquals(activity, createdActivity);
        verify(activityRepository, times(1)).save(activity);
    }

    @Test
    public void testGetActivityById_Exists() {
        when(activityRepository.findById(1)).thenReturn(Optional.of(activity));
        Optional<Activity> foundActivity = activityService.getActivityById(1);
        assertTrue(foundActivity.isPresent());
        assertEquals(activity, foundActivity.get());
    }

    @Test
    public void testGetActivityById_NotExists() {
        when(activityRepository.findById(1)).thenReturn(Optional.empty());
        Optional<Activity> foundActivity = activityService.getActivityById(1);
        assertFalse(foundActivity.isPresent());
    }

    @Test
    public void testGetAllActivities() {
        List<Activity> activities = new ArrayList<>();
        activities.add(activity);
        when(activityRepository.findAll()).thenReturn(activities);
        List<Activity> foundActivities = activityService.getAllActivities();
        assertEquals(1, foundActivities.size());
        assertEquals(activity, foundActivities.get(0));
    }

    @Test
    public void testUpdateActivity_Exists() {
        when(activityRepository.existsById(1)).thenReturn(true);
        when(activityRepository.save(activity)).thenReturn(activity);
        Activity updatedActivity = activityService.updateActivity(1, activity);
        assertEquals(activity, updatedActivity);
        verify(activityRepository, times(1)).save(activity);
    }

    @Test
    public void testUpdateActivity_NotExists() {
        when(activityRepository.existsById(1)).thenReturn(false);
        Activity updatedActivity = activityService.updateActivity(1, activity);
        assertNull(updatedActivity);
    }

    @Test
    public void testDeleteActivity() {
        doNothing().when(activityRepository).deleteById(1);
        activityService.deleteActivity(1);
        verify(activityRepository, times(1)).deleteById(1);
    }
}
