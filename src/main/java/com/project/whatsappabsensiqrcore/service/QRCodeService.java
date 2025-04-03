package com.project.whatsappabsensiqrcore.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.project.whatsappabsensiqrcore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class QRCodeService {

    @Autowired
    private UserRepository userRepository;

    public String generateQRCode(String nomorWa) throws WriterException {
        if (userRepository.findByNomorWa(nomorWa).isEmpty()) {
            throw new IllegalArgumentException("Nomor WhatsApp belum terdaftar");
        }

        try {
            String qrContent = "https://wa.me/qr/" + nomorWa;
            int width = 300;
            int height = 300;

            // Membuat QR Code
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(new MultiFormatWriter().encode(qrContent, BarcodeFormat.QR_CODE, width, height), "PNG", outputStream);
            byte[] qrBytes = outputStream.toByteArray();

            return Base64.getEncoder().encodeToString(qrBytes);
        } catch (IOException e) {
            throw new RuntimeException("Gagal membuat QR Code", e);
        }
    }
}