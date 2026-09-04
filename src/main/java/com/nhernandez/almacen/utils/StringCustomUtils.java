package com.nhernandez.almacen.utils;

public class StringCustomUtils {

    public static void validarNoVacio(String text, String mensaje){

        if (text == null || text.isBlank()){
            throw  new IllegalArgumentException(mensaje);
        }

    }

    public static void validarTamanio(String text, String mensaje, Integer min, Integer max){
        validarNoVacio(text, mensaje);

        if (text.length() < min || text.length() > max){
            throw new IllegalArgumentException(mensaje);
        }
    }

    public static String quitaracentos(String text){
        return text.toLowerCase()
                .replace("á", "a").replace("é", "e")
                .replace("í", "i").replace("ó", "o");
    }
}
