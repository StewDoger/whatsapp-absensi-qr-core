package com.project.whatsappabsensiqrcore.service;

import com.project.whatsappabsensiqrcore.model.User;
import com.project.whatsappabsensiqrcore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(String kodeUnik, String nomorWa) throws Exception {
        if (userRepository.existsByNomorWa(nomorWa)) {
            throw new Exception("Nomor WhatsApp sudah terdaftar");
        }

        if (userRepository.existsByKodeUnik(kodeUnik)) {
            throw new Exception("Kode Unik sudah terdaftar");
        }

        User newUser = new User();
        newUser.setNomorWa(nomorWa);
        newUser.setKodeUnik(kodeUnik);

        return userRepository.save(newUser);
    }
}
