package com.tugra.service;

import com.tugra.dto.DtoKullanici;
import com.tugra.dto.DtoKullaniciUI;
import com.tugra.dto.RefreshTokenRequest;
import com.tugra.jwt.AuthRequest;
import com.tugra.jwt.AuthResponse;

public interface KullaniciService {

    public DtoKullanici kayit(DtoKullaniciUI dtoKullaniciUI);

    public AuthResponse authenticate(AuthRequest authRequest);

    public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
