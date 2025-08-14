package com.tugra.controller.impl;

import com.tugra.controller.KullaniciController;
import com.tugra.dto.DtoKullanici;
import com.tugra.dto.DtoKullaniciUI;
import com.tugra.dto.RefreshTokenRequest;
import com.tugra.jwt.AuthRequest;
import com.tugra.jwt.AuthResponse;
import com.tugra.service.KullaniciService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Kullanici Controller", description = "Kullanici ile API İşlemleri")
public class KullaniciControllerImpl implements KullaniciController {

    @Autowired
    private KullaniciService kullaniciService;

    @Override
    @PostMapping(path = "/register")
    public DtoKullanici kayit(@Valid @RequestBody DtoKullaniciUI dtoKullaniciUI) {
        return kullaniciService.kayit(dtoKullaniciUI);
    }

    @Override
    @PostMapping(path = "/authenticate")
    public AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
        return kullaniciService.authenticate(authRequest);
    }

    @Override
    @PostMapping(path = "/refresh-token")
    public AuthResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return kullaniciService.refreshToken(refreshTokenRequest);
    }

}
