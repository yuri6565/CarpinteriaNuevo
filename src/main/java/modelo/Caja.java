
package modelo;

import java.util.List;

public class Caja {
    private int id_codigo;
    private String fecha;
    private String movimiento; 
    private double monto;
    private int cantidad;
    private String descripcion;
    private String categoria;
    private String proveedor;
    private List<String> productos;
    
    // Constructor con parámetros
    public Caja(int id_codigo, String fecha, String movimiento, double monto, String descripcion) {
        this.id_codigo = id_codigo;
        this.fecha = fecha;
        this.movimiento = movimiento;
        this.monto = monto;
        this.cantidad=cantidad;
        this.descripcion = descripcion;
    }
    
    // Constructor vacío (necesario para algunos frameworks)
    public Caja() {
    }

    public int getId_codigo() {
        return id_codigo;
    }

    public void setId_codigo(int id_codigo) {
        this.id_codigo = id_codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public List<String> getProductos() {
        return productos;
    }

    public void setProductos(List<String> productos) {
        this.productos = productos;
    }
    
    
}

