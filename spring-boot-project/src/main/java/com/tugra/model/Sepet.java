package com.tugra.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sepet" ,
        uniqueConstraints = @UniqueConstraint(columnNames = {"username", "urun_id"}))
@Schema(description = "Sepet Bilgileri")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sepet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sepet_id")
    private Long sepetId;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "username" , referencedColumnName = "username")
    private Kullanici kullanici;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "urun_id" , referencedColumnName = "urun_id")
    private Urunler urunler;

}
