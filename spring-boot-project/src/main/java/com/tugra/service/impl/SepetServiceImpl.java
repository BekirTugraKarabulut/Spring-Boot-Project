package com.tugra.service.impl;

import com.tugra.dto.DtoKullanici;
import com.tugra.dto.DtoSepet;
import com.tugra.dto.DtoSepetUI;
import com.tugra.dto.DtoUrunler;
import com.tugra.model.Kullanici;
import com.tugra.model.Sepet;
import com.tugra.model.Urunler;
import com.tugra.repository.KullaniciRepository;
import com.tugra.repository.SepetRepository;
import com.tugra.repository.UrunlerRepository;
import com.tugra.service.SepetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SepetServiceImpl implements SepetService {

    @Autowired
    private SepetRepository sepetRepository;

    @Autowired
    private KullaniciRepository kullaniciRepository;

    @Autowired
    private UrunlerRepository urunlerRepository;

    @Override
    public DtoSepet sepeteEkle(String username,DtoSepetUI dtoSepetUI) {

        Sepet sepet = new Sepet();

        Kullanici kullanici = new Kullanici();
        kullanici.setUsername(username);

        Optional<Kullanici> optionalKullanici = kullaniciRepository.findByUsername(kullanici.getUsername());

        if(optionalKullanici.isEmpty()){
            return null;
        }

        sepet.setKullanici(optionalKullanici.get());

        Urunler urunler = new Urunler();
        urunler.setUrunKodu(dtoSepetUI.getUrunler().getUrunKodu());

        Optional<Urunler> optionalUrunler = urunlerRepository.findByUrunKodu(urunler.getUrunKodu());

        if(optionalUrunler.isEmpty()){
            return null;
        }

        sepet.setUrunler(optionalUrunler.get());

        Sepet dbSepet = sepetRepository.save(sepet);
        DtoSepet dtoSepet = new DtoSepet();
        BeanUtils.copyProperties(dbSepet,dtoSepet);

        DtoKullanici dtoKullanici = new DtoKullanici();
        BeanUtils.copyProperties(dbSepet.getKullanici(), dtoKullanici);
        dtoSepet.setKullanici(dtoKullanici);

        DtoUrunler dtoUrunler = new DtoUrunler();
        BeanUtils.copyProperties(dbSepet.getUrunler(), dtoUrunler);
        dtoSepet.setUrunler(dtoUrunler);

        return dtoSepet;
    }
}
