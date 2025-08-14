package com.tugra.exception;

import lombok.Getter;

@Getter
public enum MessageType {

    KULLANCI_AD_KULLANILMIS("1001", "Kullanıcı adı zaten kullanılıyor."),
    TELEFON_NUMARASI_KULLANILMIS("1002", "Telefon numarası zaten kullanılıyor.");

    private String code;
    private String message;

    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
