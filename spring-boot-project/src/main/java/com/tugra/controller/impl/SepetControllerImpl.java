package com.tugra.controller.impl;

import com.tugra.controller.SepetController;
import com.tugra.dto.DtoSepet;
import com.tugra.dto.DtoSepetUI;
import com.tugra.service.SepetService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/sepet")
@Tag(name = "Sepet Controller",description = "Sepet API İşlemleri")
public class SepetControllerImpl implements SepetController {

    @Autowired
    private SepetService sepetService;


    @Override
    @PostMapping(path = "/ekle/{username}")
    public DtoSepet sepeteEkle(@PathVariable(name = "username" , required = true) String username,@RequestBody DtoSepetUI dtoSepetUI) {
        return sepetService.sepeteEkle(username,dtoSepetUI);
    }

}
