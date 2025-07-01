/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Yuri 5
 */ //
public class UsuarioModelo {
    private int Id_usuario;
    private String tipodeiden;
        private byte[] imagen;
    private String Nombre;
    private String Apellido;
    private String Correo_electronico;
    private String Usuario;
    private String Contrasena;
    private String Telefono;
    private String Rol;

    public UsuarioModelo(int Id_usuario, String tipodeiden, byte[] imagen, String Nombre, String Apellido, String Correo_electronico, String Usuario, String Contrasena, String Telefono, String Rol) {
        this.Id_usuario = Id_usuario;
        this.tipodeiden = tipodeiden;
        this.imagen = imagen;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Correo_electronico = Correo_electronico;
        this.Usuario = Usuario;
        this.Contrasena = Contrasena;
        this.Telefono = Telefono;
        this.Rol = Rol;
    }

    public int getId_usuario() {
        return Id_usuario;
    }

    public void setId_usuario(int Id_usuario) {
        this.Id_usuario = Id_usuario;
    }

    public String getTipodeiden() {
        return tipodeiden;
    }

    public void setTipodeiden(String tipodeiden) {
        this.tipodeiden = tipodeiden;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getCorreo_electronico() {
        return Correo_electronico;
    }

    public void setCorreo_electronico(String Correo_electronico) {
        this.Correo_electronico = Correo_electronico;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String Rol) {
        this.Rol = Rol;
    }

    public UsuarioModelo() {
    }

    
    

}