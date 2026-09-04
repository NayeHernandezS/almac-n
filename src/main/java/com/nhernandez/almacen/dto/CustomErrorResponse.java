package com.nhernandez.almacen.dto;

public record CustomErrorResponse(
        int codigo,
        String mensaje
) {
}
