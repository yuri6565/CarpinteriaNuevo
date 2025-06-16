/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Conexion;
import modelo.MaterialDatos;

/**
 *
 * @author ZenBook
 */
public class Ctrl_InventarioMaterial {

    // Clase pública y estática para poder acceder desde fuera
    public static class MaterialConDetalles {

        private MaterialDatos material;
        private String nombreCategoria;
        private String nombreMarca;
        private String nombreUnidadMedida;

        public MaterialConDetalles(MaterialDatos material, String nombreCategoria,
                String nombreMarca, String nombreUnidadMedida) {
            this.material = material;
            this.nombreCategoria = nombreCategoria;
            this.nombreMarca = nombreMarca;
            this.nombreUnidadMedida = nombreUnidadMedida;
        }

        // Getters
        public MaterialDatos getMaterial() {
            return material;
        }

        public String getNombreCategoria() {
            return nombreCategoria != null ? nombreCategoria : "Sin categoría";
        }

        public String getNombreMarca() {
            return nombreMarca != null ? nombreMarca : "Sin marca";
        }

        public String getNombreUnidadMedida() {
            return nombreUnidadMedida != null ? nombreUnidadMedida : "Sin unidad de medida";
        }
    }

    public int insertar(MaterialDatos material) {
        String sql = "INSERT INTO inventario (nombre, descripcion, cantidad, precio_unitario, stockMinimo, tipo, "
                + "categoria_codigo, marca_idmarca, unidad_medida_idunidad_medida, imagen) "
                + "VALUES (?, ?, ?, ?, ?, 'material', ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection(); // IMPORTANTE: Agregar RETURN_GENERATED_KEYS para obtener el ID
                 PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, material.getNombre());
            stmt.setString(2, material.getDescripcion());
            stmt.setString(3, material.getCantidad());
            stmt.setInt(4, material.getPrecioUnitario());
            stmt.setString(5, material.getStockMinimo());
            stmt.setInt(6, material.getIdCategoria());
            stmt.setInt(7, material.getIdMarca());
            stmt.setInt(8, material.getIdUnidadMedida());
            stmt.setBytes(9, material.getImagen());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                // Obtener el ID generado
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1); // Retorna el ID generado
                    }
                }
            }
            return -1; // Retorna -1 si no se pudo obtener el ID
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Retorna -1 en caso de error
        }
    }

    public List<MaterialConDetalles> obtenerMateriales() {
        List<MaterialConDetalles> lista = new ArrayList<>();
        String sql = "SELECT i.*, c.nombre AS nombre_categoria, m.nombre AS nombre_marca, um.nombre AS nombre_unidad_medida "
                + "FROM inventario i "
                + "LEFT JOIN categoria c ON i.categoria_codigo = c.codigo "
                + "LEFT JOIN marca m ON i.marca_idmarca = m.idmarca "
                + "LEFT JOIN unidad_medida um ON i.unidad_medida_idunidad_medida = um.idunidad_medida "
                + "WHERE i.tipo = 'material'";

        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MaterialDatos material = new MaterialDatos(
                        rs.getInt("id_inventario"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("cantidad"),
                        rs.getInt("precio_unitario"),
                        rs.getString("stockMinimo"),
                        rs.getInt("categoria_codigo"),
                        rs.getInt("marca_idmarca"),
                        rs.getInt("unidad_medida_idunidad_medida"),
                        rs.getBytes("imagen")
                );
                String nombreCategoria = rs.getString("nombre_categoria");
                String nombreMarca = rs.getString("nombre_marca");
                String nombreUnidadMedida = rs.getString("nombre_unidad_medida");
                lista.add(new MaterialConDetalles(material, nombreCategoria, nombreMarca, nombreUnidadMedida));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener materiales: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    public boolean actualizar(MaterialDatos material) {
        String sql = "UPDATE inventario SET nombre = ?, descripcion = ?, cantidad = ?, precio_unitario = ?, "
                + "stockMinimo = ?, categoria_codigo = ?, marca_idmarca = ?, unidad_medida_idunidad_medida = ?, imagen = ? "
                + "WHERE id_inventario = ? AND tipo = 'material'";

        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, material.getNombre());
            stmt.setString(2, material.getDescripcion());
            stmt.setString(3, material.getCantidad());
            stmt.setInt(4, material.getPrecioUnitario());
            stmt.setString(5, material.getStockMinimo());
            stmt.setInt(6, material.getIdCategoria());
            stmt.setInt(7, material.getIdMarca());
            stmt.setInt(8, material.getIdUnidadMedida());
            stmt.setBytes(9, material.getImagen());
            stmt.setInt(10, material.getIdInventario());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar material: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idInventario) {
        String sql = "DELETE FROM inventario WHERE id_inventario = ? AND tipo = 'material'";

        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idInventario);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar material: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
