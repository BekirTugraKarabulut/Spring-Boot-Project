package com.tugra.service;

import com.tugra.dto.DtoSepet;
import com.tugra.dto.DtoSepetUI;

public interface SepetService {

    public DtoSepet sepeteEkle(String username,DtoSepetUI dtoSepetUI);

}
