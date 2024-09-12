package com.aitripplanner.AiTripPlanner.Repository;



import com.aitripplanner.AiTripPlanner.Entites.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {
}
