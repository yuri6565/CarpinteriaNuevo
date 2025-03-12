/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Personal
 */
public class Cliente {
    private int codigo;
    private String nombre;
    private String identificacion;
    private int numero;
    private String apellido;
    private String telefono;
    private String direccion;

   
    
      public Cliente() {
        this.codigo = 0;
        this.nombre = "";
        this.identificacion = "";
        this.numero = 0;
        this.apellido = "";
        this.telefono = "";
        this.direccion = "";
        
      }

    public Cliente(int codigo, String nombre, String identificacion, int numero, String apellido, String telefono, String direccion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.numero = numero;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

   
    
   
    
}
