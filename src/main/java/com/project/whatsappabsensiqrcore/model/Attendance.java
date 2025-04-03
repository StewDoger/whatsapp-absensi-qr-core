package com.project.whatsappabsensiqrcore.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime waktuAbsensi;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
