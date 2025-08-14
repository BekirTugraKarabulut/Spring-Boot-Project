package com.tugra.service.impl;

import com.tugra.dto.DtoKullanici;
import com.tugra.dto.DtoKullaniciUI;
import com.tugra.dto.RefreshTokenRequest;
import com.tugra.exception.BaseException;
import com.tugra.exception.ErrorMessage;
import com.tugra.exception.MessageType;
import com.tugra.jwt.AuthRequest;
import com.tugra.jwt.AuthResponse;
import com.tugra.jwt.JwtService;
import com.tugra.model.Kullanici;
import com.tugra.model.RefreshToken;
import com.tugra.model.Role;
import com.tugra.repository.KullaniciRepository;
import com.tugra.repository.RefreshTokenRepository;
import com.tugra.service.KullaniciService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class KullaniciServiceImpl implements KullaniciService {

    @Autowired
    private KullaniciRepository kullaniciRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtService jwtService;

    private Logger logger = Logger.getLogger(KullaniciServiceImpl.class.getName());

    @Async
    public void kayitOlurkenBeklemeSuresi(){

        try {
            Thread.sleep(3000);
            logger.info("Kayıt olunuyor lütfen bekleyiniz..");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Async
    public void girisYaparkenBeklemeSuresi(){

        try {
            Thread.sleep(2000);
            logger.info("Giriş yapılıyor lütfen bekleyiniz..");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public DtoKullanici kayit(DtoKullaniciUI dtoKullaniciUI) {

        Kullanici kullanici = new Kullanici();
        kullanici.setUsername(dtoKullaniciUI.getUsername());
        kullanici.setAd(dtoKullaniciUI.getAd());
        kullanici.setPassword(bCryptPasswordEncoder.encode(dtoKullaniciUI.getPassword()));
        kullanici.setTelefonNo(dtoKullaniciUI.getTelefonNo());
        kullanici.setAdres(dtoKullaniciUI.getAdres());
        kullanici.setRole(Role.KULLANICI);

        if(kullaniciRepository.findByUsername(kullanici.getUsername()).isPresent()){
            throw new BaseException(new ErrorMessage(MessageType.KULLANCI_AD_KULLANILMIS , kullanici.getUsername()));
        }

        if(kullaniciRepository.findByTelefonNo(kullanici.getTelefonNo()).isPresent()){
            throw new BaseException(new ErrorMessage(MessageType.TELEFON_NUMARASI_KULLANILMIS , kullanici.getTelefonNo()));
        }

        Kullanici dbKullanici = kullaniciRepository.save(kullanici);
        kayitOlurkenBeklemeSuresi();

        DtoKullanici dtoKullanici = new DtoKullanici();
        BeanUtils.copyProperties(dbKullanici, dtoKullanici);

        return dtoKullanici;
    }

    public RefreshToken createRefreshToken(Kullanici kullanici){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setKullanici(kullanici);
        refreshToken.setCreatedDate(new Date());
        refreshToken.setExpireDate(new Date(System.currentTimeMillis() + 3600 * 1000));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        return refreshToken;
    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
        authenticationProvider.authenticate(authenticationToken);

        Optional<Kullanici> kullanici = kullaniciRepository.findByUsername(authRequest.getUsername());

        if(kullanici.isEmpty()){
            return null;
        }

        String accessToken = jwtService.generateToken(kullanici.get());
        RefreshToken refreshToken = createRefreshToken(kullanici.get());
        RefreshToken dbRefreshToken = refreshTokenRepository.save(refreshToken);

        girisYaparkenBeklemeSuresi();

        return new AuthResponse(accessToken , dbRefreshToken.getRefreshToken());
    }

    public boolean isValidRefreshToken(Date expriredDate){

        return new Date().before(expriredDate);
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {

        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByRefreshToken(refreshTokenRequest.getToken());

        if(!isValidRefreshToken(refreshToken.get().getExpireDate())){
            return null;
        }

        if(refreshToken.isEmpty()){
            return null;
        }

        Kullanici kullanici = refreshToken.get().getKullanici();

        String accessToken = jwtService.generateToken(kullanici);
        RefreshToken newRefreshToken = createRefreshToken(kullanici);
        RefreshToken dbRefreshToken = refreshTokenRepository.save(newRefreshToken);

        return new AuthResponse(accessToken , dbRefreshToken.getRefreshToken());
    }

}
