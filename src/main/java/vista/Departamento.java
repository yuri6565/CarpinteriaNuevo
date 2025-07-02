/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.util.Arrays;
import java.util.List;

public class Departamento {
    private String nombre;
    private List<String> municipios;

    public Departamento(String nombre, List<String> municipios) {
        this.nombre = nombre;
        this.municipios = municipios;
    }

    public String getNombre() {
        return nombre;
    }

    public List<String> getMunicipios() {
        return municipios;
    }

    public static List<Departamento> getTodosDepartamentos() {
        return Arrays.asList(
            new Departamento("Antioquia", Arrays.asList("Medellín", "Bello", "Itagüí", "Envigado", "Rionegro", "Marinilla", "Santa Fe de Antioquia")),
            new Departamento("Arauca", Arrays.asList("Arauca", "Saravena", "Tame", "Fortul")),
            new Departamento("Boyacá", Arrays.asList("Tunja", "Duitama", "Sogamoso", "Chiquinquirá", "Villa de Leyva")),
            new Departamento("Caldas", Arrays.asList("Manizales", "La Dorada", "Chinchiná", "Villamaría", "Aguadas")),
            new Departamento("Caquetá", Arrays.asList("Florencia", "Belén de los Andaquíes", "San Vicente del Caguán")),
            new Departamento("Casanare", Arrays.asList("Yopal", "Aguazul", "Tauramena", "Villanueva")),
            new Departamento("Cauca", Arrays.asList("Popayán", "Santander de Quilichao", "Patía", "Piendamó")),
            new Departamento("Cundinamarca", Arrays.asList("Bogotá D.C.", "Soacha", "Facatativá", "Zipaquirá", "Girardot", "Fusagasugá")),
            new Departamento("Huila", Arrays.asList("Neiva", "Pitalito", "Garzón", "La Plata", "Campoalegre")),
            new Departamento("Meta", Arrays.asList("Villavicencio", "Acacías", "Granada", "Puerto López", "Puerto Gaitán")),
            new Departamento("Nariño", Arrays.asList("Pasto", "Tumaco", "Ipiales", "Samaniego", "La Unión")),
            new Departamento("Norte de Santander", Arrays.asList("Cúcuta", "Ocaña", "Pamplona", "Los Patios", "Villa del Rosario")),
            new Departamento("Quindío", Arrays.asList("Armenia", "Calarcá", "La Tebaida", "Circasia", "Montenegro")),
            new Departamento("Santander", Arrays.asList("Bucaramanga", "Floridablanca", "Girón", "Piedecuesta", "Barrancabermeja"))
        
            
        
        
        
        );
    }
}