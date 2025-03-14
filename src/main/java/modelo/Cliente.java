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
    private int id_cliente;
    private String identificacion;
    private int numero;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;

   
    
      public Cliente() {
        this.id_cliente = 0;
        this.identificacion = "";
        this.numero = 0;
        this.nombre = "";
        this.apellido = "";
        this.telefono = "";
        this.direccion = "";
        
      }

    public Cliente(int id_cliente, String identificacion, int numero, String nombre, String apellido, String telefono, String direccion) {
        this.id_cliente = id_cliente;
        this.identificacion = identificacion;
        this.numero = numero;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public int getid_cliente() {
        return id_cliente;
    }

    public void setid_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
