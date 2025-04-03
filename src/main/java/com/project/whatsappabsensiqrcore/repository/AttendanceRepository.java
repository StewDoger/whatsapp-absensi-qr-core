package com.project.whatsappabsensiqrcore.repository;

import com.project.whatsappabsensiqrcore.model.Attendance;
import com.project.whatsappabsensiqrcore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Optional<Attendance> findFirstByUserAndWaktuAbsensiAfter(User user, LocalDateTime waktu);
    List<Attendance> findByUserOrderByWaktuAbsensiDesc(User user);
    boolean existsByUserAndWaktuAbsensiBetween(User user, LocalDateTime start, LocalDateTime end);
}