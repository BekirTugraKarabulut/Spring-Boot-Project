package com.tugra.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoSepet {

    private Long sepetId;

    private DtoKullanici kullanici;

    private DtoUrunler urunler;

}
