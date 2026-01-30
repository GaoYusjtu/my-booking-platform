package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    // 继承了 JpaRepository 后，你就自动拥有了 save(), findAll(), delete() 等功能
    List<Appointment> findByStartTime(String startTime);
}