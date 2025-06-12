/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author ZenBook
 */
public class Ingresos {
    private int idAbono;
    private double montoTotal;
    private String metodoPago;
    private Date fechaPago;
    private double pagado;
    private double debido;
    private int idCliente;
    private int idPedido;

    public Ingresos() {
    }

    public Ingresos(int idAbono, double montoTotal, String metodoPago, Date fechaPago, double pagado, double debido, int idCliente, int idPedido) {
        this.idAbono = idAbono;
        this.montoTotal = montoTotal;
        this.metodoPago = metodoPago;
        this.fechaPago = fechaPago;
        this.pagado = pagado;
        this.debido = debido;
        this.idCliente = idCliente;
        this.idPedido = idPedido;
    }

    public int getIdAbono() {
        return idAbono;
    }

    public void setIdAbono(int idAbono) {
        this.idAbono = idAbono;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getPagado() {
        return pagado;
    }

    public void setPagado(double pagado) {
        this.pagado = pagado;
    }

    public double getDebido() {
        return debido;
    }

    public void setDebido(double debido) {
        this.debido = debido;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    
    
}
