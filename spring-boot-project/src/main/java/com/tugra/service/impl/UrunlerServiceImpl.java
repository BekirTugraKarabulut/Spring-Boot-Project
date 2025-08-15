package com.tugra.service.impl;

import com.tugra.dto.DtoUrunler;
import com.tugra.model.Urunler;
import com.tugra.repository.UrunlerRepository;
import com.tugra.service.UrunlerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

        List<DtoUrunler> dtoUrunlerList = urunlerList.stream()
                .map(urunler -> new DtoUrunler(
                        urunler.getUrunId(),
                        urunler.getUrunFiyati(),
                        urunler.getUrunAdi(),
                        urunler.getUrunKodu()
                )).collect(Collectors.toList());

        return dtoUrunlerList;
    }

    @Override
    public DtoUrunler getUrunByUrunKodu(String urunKodu) {

        Optional<Urunler> urun = urunlerRepository.findByUrunKodu(urunKodu);

        if(urun.isEmpty()){
            return null;
        }

        DtoUrunler dtoUrunler = new DtoUrunler();
        BeanUtils.copyProperties(urun.get(),dtoUrunler);

        return dtoUrunler;
    }

}
