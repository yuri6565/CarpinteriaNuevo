/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author ADSO
 */
public class Caja {
    
    private int codigo;
    private String fecha;
    private String detalle;
    private Double total;

    public Caja(String fecha, String detalle, Double total) {
        this.fecha = fecha;
        this.detalle = detalle;
        this.total = total;
    }

    public Caja(int codigo, String fecha, String detalle, Double total) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.detalle = detalle;
        this.total = total;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}
