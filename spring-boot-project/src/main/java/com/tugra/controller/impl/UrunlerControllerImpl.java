package com.tugra.controller.impl;

import com.tugra.controller.UrunlerController;
import com.tugra.dto.DtoUrunler;
import com.tugra.service.UrunlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/urunler")
public class UrunlerControllerImpl implements UrunlerController {

    @Autowired
    private UrunlerService urunlerService;


    @Override
    @GetMapping(path = "/getAllUrunler")
    public List<DtoUrunler> getAllUrunler() {
        return urunlerService.getAllUrunler();
    }

    @Override
    @GetMapping(path = "/getUrunByUrunKodu/{urunKodu}")
    public DtoUrunler getUrunByUrunKodu(@PathVariable(name = "urunKodu" ,required = true) String urunKodu) {
        return urunlerService.getUrunByUrunKodu(urunKodu);
    }

}
