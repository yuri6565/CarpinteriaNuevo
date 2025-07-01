package modelo;

import java.util.List;

public class ProveedorDatos {
    private int id_proveedor;
    private String tipoIdentificacion; // Nuevo campo
    private String nombre;
    private String apellido;
    private String correo_electronico;
    private String telefono;
    private String direccion;
    private String estado;
    private String departamento;
    private String municipio;
    private List<String> productos;

    // Constructores
    public ProveedorDatos() {
    }

    public ProveedorDatos(int id_proveedor, String tipoIdentificacion, String nombre, String apellido, String correo_electronico,
                          String telefono, String direccion, String estado, String departamento, String municipio,
                          List<String> productos) {
        this.id_proveedor = id_proveedor;
        this.tipoIdentificacion = tipoIdentificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo_electronico = correo_electronico;
        this.telefono = telefono;
        this.direccion = direccion;
        this.estado = estado;
        this.departamento = departamento;
        this.municipio = municipio;
        this.productos = productos;
    }

    // Getters y Setters
    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
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

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public List<String> getProductos() {
        return productos;
    }

    public void setProductos(List<String> productos) {
        this.productos = productos;
    }
}