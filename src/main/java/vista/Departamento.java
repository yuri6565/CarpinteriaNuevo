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
            new Departamento("Amazonas", Arrays.asList("Leticia", "Puerto Nariño", "El Encanto")),
            new Departamento("Antioquia", Arrays.asList("Medellín", "Bello", "Itagüí", "Envigado", "Rionegro", "Marinilla", "Santa Fe de Antioquia")),
            new Departamento("Arauca", Arrays.asList("Arauca", "Saravena", "Tame", "Fortul")),
            new Departamento("Atlántico", Arrays.asList("Barranquilla", "Soledad", "Malambo", "Sabanalarga", "Baranoa")),
            new Departamento("Bolívar", Arrays.asList("Cartagena", "Magangué", "Turbaco", "Arjona", "Santa Rosa del Sur")),
            new Departamento("Boyacá", Arrays.asList("Tunja", "Duitama", "Sogamoso", "Chiquinquirá", "Villa de Leyva")),
            new Departamento("Caldas", Arrays.asList("Manizales", "La Dorada", "Chinchiná", "Villamaría", "Aguadas")),
            new Departamento("Caquetá", Arrays.asList("Florencia", "Belén de los Andaquíes", "San Vicente del Caguán")),
            new Departamento("Casanare", Arrays.asList("Yopal", "Aguazul", "Tauramena", "Villanueva")),
            new Departamento("Cauca", Arrays.asList("Popayán", "Santander de Quilichao", "Patía", "Piendamó")),
            new Departamento("Cesar", Arrays.asList("Valledupar", "Aguachica", "Codazzi", "La Paz")),
            new Departamento("Chocó", Arrays.asList("Quibdó", "Istmina", "Tadó", "Nuquí")),
            new Departamento("Córdoba", Arrays.asList("Montería", "Cereté", "Lorica", "Sahagún", "Tierralta")),
            new Departamento("Cundinamarca", Arrays.asList("Bogotá D.C.", "Soacha", "Facatativá", "Zipaquirá", "Girardot", "Fusagasugá")),
            new Departamento("Guainía", Arrays.asList("Inírida", "Barranco Minas", "Mapiripana")),
            new Departamento("Guaviare", Arrays.asList("San José del Guaviare", "Calamar", "Miraflores")),
            new Departamento("Huila", Arrays.asList("Neiva", "Pitalito", "Garzón", "La Plata", "Campoalegre")),
            new Departamento("La Guajira", Arrays.asList("Riohacha", "Maicao", "Uribia", "Manaure", "Albania")),
            new Departamento("Magdalena", Arrays.asList("Santa Marta", "Ciénaga", "Fundación", "Aracataca", "El Banco")),
            new Departamento("Meta", Arrays.asList("Villavicencio", "Acacías", "Granada", "Puerto López", "Puerto Gaitán")),
            new Departamento("Nariño", Arrays.asList("Pasto", "Tumaco", "Ipiales", "Samaniego", "La Unión")),
            new Departamento("Norte de Santander", Arrays.asList("Cúcuta", "Ocaña", "Pamplona", "Los Patios", "Villa del Rosario")),
            new Departamento("Putumayo", Arrays.asList("Mocoa", "Puerto Asís", "Orito", "Valle del Guamuez")),
            new Departamento("Quindío", Arrays.asList("Armenia", "Calarcá", "La Tebaida", "Circasia", "Montenegro")),
            new Departamento("Risaralda", Arrays.asList("Pereira", "Dosquebradas", "Santa Rosa de Cabal", "La Virginia")),
            new Departamento("Santander", Arrays.asList("Bucaramanga", "Floridablanca", "Girón", "Piedecuesta", "Barrancabermeja")),
            new Departamento("Sucre", Arrays.asList("Sincelejo", "Corozal", "Tolú", "Sampués", "San Marcos")),
            new Departamento("Tolima", Arrays.asList("Ibagué", "Espinal", "Honda", "Mariquita", "Melgar")),
            new Departamento("Valle del Cauca", Arrays.asList("Cali", "Buenaventura", "Palmira", "Tuluá", "Buga", "Cartago")),
            new Departamento("Vaupés", Arrays.asList("Mitú", "Carurú", "Taraira")),
            new Departamento("Vichada", Arrays.asList("Puerto Carreño", "La Primavera", "Santa Rosalía", "Cumaribo"))
        );
    }
}