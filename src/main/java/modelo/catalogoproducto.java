/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author buitr
 */
public class catalogoproducto {
    private int idproducto;
    private String nombre;
    private String alto;
     private String ancho;// Ahora puede ser ID o nombre según el contexto
      private String profundidad;
       private String material;
        private String color;
    private byte[] imagen;
   private int Categoria_idCategoria; 

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlto() {
        return alto;
    }

    public void setAlto(String alto) {
        this.alto = alto;
    }

    public String getAncho() {
        return ancho;
    }

    public void setAncho(String ancho) {
        this.ancho = ancho;
    }

    public String getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(String profundidad) {
        this.profundidad = profundidad;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public int getCategoria_idCategoria() {
        return Categoria_idCategoria;
    }

    public void setCategoria_idCategoria(int Categoria_idCategoria) {
        this.Categoria_idCategoria = Categoria_idCategoria;
    }

    public catalogoproducto() {
    }

    public catalogoproducto(int idproducto, String nombre, String alto, String ancho, String profundidad, String material, String color, byte[] imagen, int Categoria_idCategoria) {
        this.idproducto = idproducto;
        this.nombre = nombre;
        this.alto = alto;
        this.ancho = ancho;
        this.profundidad = profundidad;
        this.material = material;
        this.color = color;
        this.imagen = imagen;
        this.Categoria_idCategoria = Categoria_idCategoria;
    }

  
}
