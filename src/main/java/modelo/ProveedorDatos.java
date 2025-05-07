/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author buitr
 */
public class ProveedorDatos {
    private String codigo_proveedor;
    private String nombre;
    private String telefono;
    private String direccion;
    private String producto;
    private String tipo;

    public ProveedorDatos(String codigo_proveedor, String nombre, String telefono, String direccion, String producto, String tipo) {
        this.codigo_proveedor = codigo_proveedor;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.producto = producto;
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo_proveedor;
    }

    public void setCodigo(String codigo_proveedor) {
        this.codigo_proveedor = codigo_proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    //ojala le corra, esta igual de bien hecho que usted 
}
