package com.project.whatsappabsensiqrcore.service;

import com.project.whatsappabsensiqrcore.exception.UserNotFoundException;
import com.project.whatsappabsensiqrcore.model.Attendance;
import com.project.whatsappabsensiqrcore.model.User;
import com.project.whatsappabsensiqrcore.repository.AttendanceRepository;
import com.project.whatsappabsensiqrcore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AttendanceService {

    private static final Logger logger = LoggerFactory.getLogger(AttendanceService.class);

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Mencatat absensi untuk pengguna berdasarkan kode unik dari QR Code.
     * @param kodeUnik kode unik pengguna dari QR Code
     * @return catatan absensi yang disimpan di database
     * @throws UserNotFoundException jika pengguna tidak ditemukan
     */
    public Attendance recordAttendance(String kodeUnik) {
        Optional<User> userOpt = userRepository.findByKodeUnik(kodeUnik);

        if (userOpt.isEmpty()) {
            // Log error jika user tidak ditemukan
            logger.error("Pengguna tidak ditemukan dengan kode unik: {}", kodeUnik);
            throw new UserNotFoundException("Pengguna tidak ditemukan! Pastikan QR Code valid.");
        }

        User user = userOpt.get();

        LocalDateTime todayStart = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime todayEnd = todayStart.plusDays(1).minusNanos(1);
        boolean alreadyAttendedToday = attendanceRepository.existsByUserAndWaktuAbsensiBetween(user, todayStart, todayEnd);

        if (alreadyAttendedToday) {
            logger.warn("Pengguna {} sudah absen hari ini.", user.getKodeUnik());
            throw new RuntimeException("Anda sudah melakukan absensi hari ini.");
        }

        Attendance attendance = new Attendance();
        attendance.setUser(user);
        attendance.setWaktuAbsensi(LocalDateTime.now());

        logger.info("Absensi berhasil dicatat untuk pengguna: {}", user.getKodeUnik());
        return attendanceRepository.save(attendance);
    }
}
