package com.example.demo;

import jakarta.persistence.*;
import lombok.Data;
@Entity // 告诉系统这是一个数据库表
@Data   // 自动生成 Getter/Setter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;      // 预约人
    private String startTime; // 预约开始时间
    private String status;    // 状态：已预约/已完成
}
