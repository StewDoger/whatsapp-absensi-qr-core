package com.project.whatsappabsensiqrcore.repository;

import com.project.whatsappabsensiqrcore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNomorWa(String nomorWa);
    Optional<User> findByKodeUnik(String kodeUnik);
    boolean existsByNomorWa(String nomorWa);
    boolean existsByKodeUnik(String kodeUnik);
}
