package com.nhernandez.almacen.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "DETALLES_VENTAS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DETALLE_VENTA")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_VENTA", nullable = false)
    private Venta venta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PRODUCTO", nullable = false)
    private Producto producto;

    @Column(name = "CANTIDAD_PRODUCTO")
    private Integer cantidadProducto;

    @Column(name = "PRECIO_PRODUCTO") // sin @Column Hibernate usaría otro nombre y fallaría el validate
    private BigDecimal precioProducto;



    public void asignarVenta(Venta venta) {
        if (venta == null)
            throw new IllegalArgumentException("La venta no a sido asiganada");
        this.venta = venta;
    }

}
