package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class AppointmentController {

    @Autowired // 自动注入刚才创建的仓库
    private AppointmentRepository appointmentRepository;
    
    private final int MAX_CAPACITY = 3; // 每个时段最多3人
    
    // 1. 提交预约并存入数据库
    @PostMapping
    public String makeAppointment(@RequestBody Appointment appointment) {
        // 检查冲突逻辑
        List<Appointment> existing = appointmentRepository.findByStartTime(appointment.getStartTime());

        if (existing.size() >= MAX_CAPACITY) {
            return "预约失败：该时段已被占用！";
        }

        appointmentRepository.save(appointment);
        // 这里的返回类型必须和方法定义的 String 对应
        return "预约成功！预约人是：" + appointment.getName();
    }
    
    @GetMapping("/capacity")
    public int getRemainingCapacity(@RequestParam String startTime) {
        List<Appointment> existing = appointmentRepository.findByStartTime(startTime);
        return Math.max(0, MAX_CAPACITY - existing.size());
    }
    
    @GetMapping("/stats")
    public List<Object[]> getStats() {
        return appointmentRepository.findTopBookers();
    }
    // 2. 新增一个接口：查看所有预约
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

}
