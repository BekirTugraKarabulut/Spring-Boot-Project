package com.tugra.controller;

import com.tugra.dto.DtoSepet;
import com.tugra.dto.DtoSepetUI;

public interface SepetController {

    public DtoSepet sepeteEkle(String username,DtoSepetUI dtoSepetUI);

}
