package com.tugra.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "urunler")
@Schema(description = "Ürün Bilgileri")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Urunler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "urun_id")
    private Long urunId;

    @Column(name = "urun_kodu", unique = true)
    private String urunKodu;

    @Column(name = "urun_adi")
    private String urunAdi;

    @Column(name = "urun_fiyati")
    private Integer urunFiyati;

    @OneToMany(mappedBy = "urunler" , cascade = CascadeType.ALL)
    private List<Sepet> sepetler;


}
