/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import javax.swing.ImageIcon;

/**
 *
 * @author Personal
 */
public class HerramientaDatos {
    private int idInventario;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private double precioUnitario;
    private String estado;
    private int idCategoria;
    private int idMarca;
    private int idUnidadMedida;
    private byte[] imagen; // imagen en formato binario

    public HerramientaDatos(String nombre, String descripcion, int cantidad, double precioUnitario, String estado, int idCategoria, int idMarca, int idUnidadMedida, byte[] imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.estado = estado;
        this.idCategoria = idCategoria;
        this.idMarca = idMarca;
        this.idUnidadMedida = idUnidadMedida;
        this.imagen = imagen;
    }

    public HerramientaDatos(int idInventario, String nombre, String descripcion, int cantidad, double precioUnitario, String estado, int idCategoria, int idMarca, int idUnidadMedida, byte[] imagen) {
        this.idInventario = idInventario;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.estado = estado;
        this.idCategoria = idCategoria;
        this.idMarca = idMarca;
        this.idUnidadMedida = idUnidadMedida;
        this.imagen = imagen;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public int getIdUnidadMedida() {
        return idUnidadMedida;
    }

    public void setIdUnidadMedida(int idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
    
}
