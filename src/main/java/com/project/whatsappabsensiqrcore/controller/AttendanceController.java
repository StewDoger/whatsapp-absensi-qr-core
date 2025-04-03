package com.project.whatsappabsensiqrcore.controller;

import com.project.whatsappabsensiqrcore.model.Attendance;
import com.project.whatsappabsensiqrcore.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin("*")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/record")
    public ResponseEntity<?> recordAttendance(@RequestBody Map<String, String> request) {
        String kodeUnik = request.get("kodeUnik");

        try {
            Attendance attendance = attendanceService.recordAttendance(kodeUnik);
            return ResponseEntity.ok("Absensi berhasil untuk kode unik: " + kodeUnik);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Absensi gagal: " + e.getMessage());
        }
    }
}