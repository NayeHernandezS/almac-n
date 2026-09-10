package com.naye.escuela.dto;

public record CustomErrorResponse(
        int codigo,
        String mensaje
) {
}
