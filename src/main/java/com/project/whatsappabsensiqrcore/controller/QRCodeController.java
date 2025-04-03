package com.project.whatsappabsensiqrcore.controller;

import com.project.whatsappabsensiqrcore.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/qrcodes")
public class QRCodeController {

    @Autowired
    private QRCodeService qrCodeService;

    @GetMapping("/generate")
    public ResponseEntity<?> generateQRCode(@RequestParam String nomorWa) {
        try {
            String qrCodeBase64 = qrCodeService.generateQRCode(nomorWa);
            return ResponseEntity.ok(qrCodeBase64);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Gagal membuat QR Code: " + e.getMessage());
        }
    }
}
