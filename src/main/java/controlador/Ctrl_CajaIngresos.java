/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Caja;
import modelo.Conexion;

/**
 *
 * @author ADSO
 */
public class Ctrl_CajaIngresos {
       
    private final String movimiento = "ingreso";

    public boolean insertar(Caja caja) {
        String sql = "INSERT INTO caja (fecha, descripcion, monto, movimiento, categoria) VALUES (?,?,?,?,?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, caja.getFecha());
            stmt.setString(2, caja.getDescripcion());
            stmt.setDouble(3, caja.getMonto());
            stmt.setString(4, movimiento);
            stmt.setString(5, caja.getCategoria()); // 👈 'ingreso'
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public List<Caja> obtenerCategorias() {
        List<Caja> lista = new ArrayList<>();
        String sql = "SELECT * FROM caja WHERE movimiento = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, movimiento);  // 👈 Solo 'ingreso'
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Caja(
                        rs.getInt("id_codigo"),
                        rs.getString("fecha"),
                        rs.getString("descripcion"),
                        rs.getDouble("monto"),
                        rs.getString("categoria")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean actualizar(Caja caja) {
        String sql = "UPDATE caja SET fecha = ?, descripcion = ?, monto = ?, categoria = ? WHERE id_codigo = ? AND movimiento = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, caja.getFecha());
            stmt.setString(2, caja.getDescripcion());
            stmt.setDouble(3, caja.getMonto());
            stmt.setInt(4, caja.getId_codigo());
            stmt.setString(5, movimiento);
            stmt.setString(6, caja.getCategoria()); // 👈 Solo si es 'ingreso'
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id_codigo) {
        String sql = "DELETE FROM caja WHERE id_codigo = ? AND movimiento = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id_codigo);
            stmt.setString(2, movimiento);  // 👈 Solo si es 'ingreso'
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
