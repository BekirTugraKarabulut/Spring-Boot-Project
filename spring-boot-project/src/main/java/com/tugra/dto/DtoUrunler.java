package com.tugra.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DtoUrunler {

    private Long urunId;

    private String urunKodu;

    private String urunAdi;

    private Integer urunFiyati;

    public DtoUrunler(Long urunId, Integer urunFiyati, String urunAdi, String urunKodu) {
        this.urunId = urunId;
        this.urunFiyati = urunFiyati;
        this.urunAdi = urunAdi;
        this.urunKodu = urunKodu;
    }
}
