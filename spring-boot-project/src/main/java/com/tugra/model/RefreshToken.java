package com.tugra.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "refresh_tokens")
@Schema(description = "Refresh Token Bilgileri")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "expire_date")
    private Date expireDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username" , referencedColumnName = "username")
    private Kullanici kullanici;

}
