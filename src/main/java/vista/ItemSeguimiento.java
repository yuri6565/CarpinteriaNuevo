/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.Ctrl_InventarioHerramienta;
import controlador.Ctrl_InventarioMaterial;

/**
 *
 * @author ZenBook
 */
public class ItemSeguimiento {

    public enum Tipo {
        MATERIAL, HERRAMIENTA
    }

    private Tipo tipo;
    private Ctrl_InventarioMaterial.MaterialConDetalles material;
    private Ctrl_InventarioHerramienta.MaterialConDetalles herramienta;
    private String prioridad; // "ALTA", "MEDIA", "BAJA"

    // Constructor para materiales
    public ItemSeguimiento(Ctrl_InventarioMaterial.MaterialConDetalles material) {
        this.tipo = Tipo.MATERIAL;
        this.material = material;
        this.prioridad = calcularPrioridadMaterial(material);
    }

    // Constructor para herramientas
    public ItemSeguimiento(Ctrl_InventarioHerramienta.MaterialConDetalles herramienta) {
        this.tipo = Tipo.HERRAMIENTA;
        this.herramienta = herramienta;
        this.prioridad = calcularPrioridadHerramienta(herramienta);
    }

    private String calcularPrioridadMaterial(Ctrl_InventarioMaterial.MaterialConDetalles mat) {
        double cantidad = Double.parseDouble(mat.getMaterial().getCantidad().replace(",", "."));
        double minimo = Double.parseDouble(mat.getMaterial().getStockMinimo().replace(",", "."));
        return (cantidad <= minimo) ? "ALTA" : "MEDIA";
    }

    private String calcularPrioridadHerramienta(Ctrl_InventarioHerramienta.MaterialConDetalles herr) {
        return herr.getMaterial().getEstado().equals("Dañado") ? "ALTA" : "MEDIA";
    }

    // Getters
    public Tipo getTipo() {
        return tipo;
    }

    public Object getItem() {
        return tipo == Tipo.MATERIAL ? material : herramienta;
    }

    public String getPrioridad() {
        return prioridad;
    }
}
