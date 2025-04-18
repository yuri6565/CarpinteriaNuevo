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
public class MaterialDatos {
    private int idInventario;
    private String nombre;
    private String descripcion;
    private String estado;
    private String stock;
    private int cantidad;
    private int idCategoria;
    private int idMarca;
    private int idUnidadMedida;
    private byte[] imagen; // imagen en formato binario

    public MaterialDatos(String nombre, String descripcion, String estado, String stock, int cantidad, int idCategoria, int idMarca, int idUnidadMedida, byte[] imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.stock = stock;
        this.cantidad = cantidad;
        this.idCategoria = idCategoria;
        this.idMarca = idMarca;
        this.idUnidadMedida = idUnidadMedida;
        this.imagen = imagen;
    }

    public MaterialDatos(int idInventario, String nombre, String descripcion, String estado, String stock, int cantidad, int idCategoria, int idMarca, int idUnidadMedida, byte[] imagen) {
        this.idInventario = idInventario;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.stock = stock;
        this.cantidad = cantidad;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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
