package com.tugra.service.impl;

import com.tugra.dto.DtoUrunler;
import com.tugra.model.Urunler;
import com.tugra.repository.UrunlerRepository;
import com.tugra.service.UrunlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UrunlerServiceImpl implements UrunlerService {

    @Autowired
    private UrunlerRepository urunlerRepository;

    @Override
    public List<DtoUrunler> getAllUrunler() {

        List<Urunler> urunlerList = urunlerRepository.findAll();

        if(urunlerList.isEmpty()){
            return null;
        }

        List<DtoUrunler> dtoList = urunlerList.stream()
                .map(urun -> new DtoUrunler(
                        urun.getUrunId(),
                        urun.getUrunFiyati(),
                        urun.getUrunAdi(),
                        urun.getUrunKodu()
                ))
                .collect(Collectors.toList());

        return dtoList;
    }

}
