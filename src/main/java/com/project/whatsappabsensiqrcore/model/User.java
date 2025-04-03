package com.project.whatsappabsensiqrcore.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String kodeUnik;
    private String nomorWa;

    // Getter dan Setter
    public String getKodeUnik() {
        return kodeUnik;
    }

    public void setKodeUnik(String kodeUnik) {
        this.kodeUnik = kodeUnik;
    }

    public String getNomorWa() {
        return nomorWa;
    }

    public void setNomorWa(String nomorWa) {
        this.nomorWa = nomorWa;
    }
}

