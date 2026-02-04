package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Map;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    // 继承了 JpaRepository 后，你就自动拥有了 save(), findAll(), delete() 等功能
    List<Appointment> findByStartTime(String startTime);
    @Query(value = "SELECT name, COUNT(*) as count FROM Appointment GROUP BY name ORDER BY count DESC LIMIT 10", nativeQuery = true)
    List<Object[]> findTopBookers();
}
