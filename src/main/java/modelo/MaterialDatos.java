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
    private String codigo;
    private String nombre;
    private String descripcion;
    private String unidadMedida;
    private String proveedor;
    private String categoria;
    private int cantidad;
    private ImageIcon imagen;

    public MaterialDatos(String codigo, String nombre, String descripcion, String unidadMedida,
                    String proveedor, String categoria, int cantidad, ImageIcon imagen) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.unidadMedida = unidadMedida;
        this.proveedor = proveedor;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.imagen = imagen;
    }
    
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public String getUnidadMedida() { return unidadMedida; }
    public String getProveedor() { return proveedor; }
    public String getCategoria() { return categoria; }
    public int getCantidad() { return cantidad; }
    public ImageIcon getImagen() { return imagen; }
}
