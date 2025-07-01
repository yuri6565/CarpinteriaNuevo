package modelo;

import java.sql.Date;


public class Cotizacion {
    private int idCotizacion;
    private String detalle;
    private String unidad;
    private int cantidad;
    private Date fecha;
    private double valorUnitario;
    private double subTotal;
    private double total;
    private int usuarioIdUsuario;
    private int clienteCodigo;

    // Constructores
    public Cotizacion() {
    }

    public Cotizacion(String detalle, String unidad, int cantidad, Date fecha, double valorUnitario, 
                     double subTotal, double total, int usuarioIdUsuario, int clienteCodigo) {
        this.detalle = detalle;
        this.unidad = unidad;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.valorUnitario = valorUnitario;
        this.subTotal = subTotal;
        this.total = total;
        this.usuarioIdUsuario = usuarioIdUsuario;
        this.clienteCodigo = clienteCodigo;
    }

    // Getters y Setters
    public int getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(int idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(int usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    public int getClienteCodigo() {
        return clienteCodigo;
    }

    public void setClienteCodigo(int clienteCodigo) {
        this.clienteCodigo = clienteCodigo;
    }

    @Override
    public String toString() {
        return "Cotizacion{" +
                "idCotizacion=" + idCotizacion +
                ", detalle='" + detalle + '\'' +
                ", unidad='" + unidad + '\'' +
                ", cantidad=" + cantidad +
                ", fecha=" + fecha +
                ", valorUnitario=" + valorUnitario +
                ", subTotal=" + subTotal +
                ", total=" + total +
                ", usuarioIdUsuario=" + usuarioIdUsuario +
                ", clienteCodigo=" + clienteCodigo +
                '}';
    }
}