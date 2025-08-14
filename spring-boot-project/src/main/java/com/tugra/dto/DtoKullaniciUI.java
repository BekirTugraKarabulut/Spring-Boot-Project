package com.tugra.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoKullaniciUI {

    @Email(message = "Geçerli bir e-posta adresi giriniz.")
    private String username;

    private String ad;

    private String password;

    @Size(min = 11 , max = 11 , message = "Telefon numarası 11 haneli olmalıdır.")
    private String telefonNo;

    private String adres;

}
