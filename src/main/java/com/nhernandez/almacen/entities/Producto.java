package com.nhernandez.almacen.entities;

import com.nhernandez.almacen.enums.Categoria;
import com.nhernandez.almacen.utils.StringCustomUtils;
import com.nhernandez.almacen.utils.ValoresNumericosUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCTOS")
@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "ID_PRODUCTO")
    private Long id;

    @Column(name = "NOMBRE", length = 30, nullable = false)
    private String nombre;

    @Column(name = "CATEGORIA", nullable = false)
    @Enumerated(EnumType.STRING)//en lugar de poner el número del enum pone el nombre
    private Categoria categoria;

    @Column(name = "PRECIO", nullable = false)
    private BigDecimal precio;

    @Column(name = "CANTIDAD", nullable = false)
    private Integer cantidad;

    public  void aumentarCantidad(int cantidad){
        ValoresNumericosUtils.validarEnteroPositivo(
                cantidad, "La cantidad debe ser positiva"
        );
        if (cantidad > this.cantidad) {
            throw new IllegalArgumentException("La cantidad debe ser menor o igual");
        }
        this.cantidad += cantidad;

        }

    public  void descontarrCantidad(int cantidad){
        ValoresNumericosUtils.validarEnteroPositivo(
                cantidad, "La cantidad debe ser positiva"
        );
        if (cantidad > this.cantidad)

            throw new IllegalArgumentException("La cantidad debe ser menor o igual");
        this.cantidad -= cantidad;

    }

        public void validarDatos(String nombre, Categoria categoria, BigDecimal precio, Integer cantidad){

            StringCustomUtils.validarTamanio(nombre, "El nombre es requerido y debe tener entre 5 y 30 caracteree", 5, 30);

            if (categoria == null){
                throw new IllegalArgumentException("La categoria es requerida");
            }

            ValoresNumericosUtils.validarEnteroPositivo(cantidad, "la cantidad debe ser requerida y positiva");
    }

    public  void  actualizar(String nombre, Categoria categoria, BigDecimal precio, Integer cantidad){
        validarDatos(nombre, categoria, precio, cantidad);

        this.nombre = nombre.trim();
        this.categoria = categoria;
        this.precio = precio;
        this.cantidad = cantidad;
    }



}
