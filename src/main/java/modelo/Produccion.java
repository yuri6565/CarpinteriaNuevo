/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author pc
 */
public class Produccion {
    private int id_produccion;
    private String fecha_inicio;
    private String fecha_fin;
    private String estado;
    private String pedido_codigo;

    public Produccion(String fecha_inicio, String fecha_fin, String estado, String pedido_codigo) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.estado = estado;
        this.pedido_codigo = pedido_codigo;
    }

    public Produccion(int id_produccion, String fecha_inicio, String fecha_fin, String estado, String pedido_codigo) {
        this.id_produccion = id_produccion;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.estado = estado;
        this.pedido_codigo = pedido_codigo;
    }

    public int getId_produccion() {
        return id_produccion;
    }

    public void setId_produccion(int id_produccion) {
        this.id_produccion = id_produccion;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPedido_codigo() {
        return pedido_codigo;
    }

    public void setPedido_codigo(String pedido_codigo) {
        this.pedido_codigo = pedido_codigo;
    }
    
    
    

    
            
            
            
            
            
}
