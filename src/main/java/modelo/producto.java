package modelo;

import java.awt.Image;

public class producto {
    private int id;
    private String nombre;
    private String color;
    private String material;
    private double ancho;
    private double altura;
    private double profundidad;
    private Image imagen;
    private int idCategoria;

    // Constructor completo
    public producto(int id, String nombre, String color, String material, 
                          double ancho, double altura, double profundidad, 
                          Image imagen, int idCategoria) {
        this.id = id;
        this.nombre = nombre;
        this.color = color;
        this.material = material;
        this.ancho = ancho;
        this.altura = altura;
        this.profundidad = profundidad;
        this.imagen = imagen;
        this.idCategoria = idCategoria;
    }

    // Constructor sin ID (para nuevos productos)
    public producto(String nombre, String color, String material, 
                          double ancho, double altura, double profundidad, 
                          Image imagen, int idCategoria) {
        this.nombre = nombre;
        this.color = color;
        this.material = material;
        this.ancho = ancho;
        this.altura = altura;
        this.profundidad = profundidad;
        this.imagen = imagen;
        this.idCategoria = idCategoria;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(double profundidad) {
        this.profundidad = profundidad;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    // Método para obtener las dimensiones formateadas
    public String getDimensiones() {
        return String.format("%.2f x %.2f x %.2f", ancho, altura, profundidad);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        producto that = (producto) obj;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return nombre + " (" + color + ")";
    }
}