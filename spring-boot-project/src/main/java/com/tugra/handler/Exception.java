package com.tugra.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exception<E> {

    private String path;

    private String hostName;

    private E message;

}
