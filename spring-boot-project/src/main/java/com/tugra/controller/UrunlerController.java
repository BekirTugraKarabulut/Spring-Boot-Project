package com.tugra.controller;

import com.tugra.dto.DtoUrunler;

import java.util.List;

public interface UrunlerController {

    public List<DtoUrunler> getAllUrunler();

    public DtoUrunler getUrunByUrunKodu(String urunKodu);

}
