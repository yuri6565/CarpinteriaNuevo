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
        private final String tipo_movimiento = "ingreso";
    
  public boolean insertar(Caja caja) {
        String sql = "INSERT INTO caja (fecha, detalle, total, tipo_movimiento) VALUES (?,?,?,?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, caja.getFecha());
            stmt.setString(2, caja.getDetalle());
            stmt.setDouble(3, caja.getTotal());
            stmt.setString(4, tipo_movimiento);  // 👈 Siempre 'material'
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public List<Caja> obtenerCategorias() {
        List<Caja> lista = new ArrayList<>();
        String sql = "SELECT * FROM caja WHERE tipo_movimiento = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, tipo_movimiento);  // 👈 Solo 'material'
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Caja(rs.getInt("codigo"), rs.getString("fecha"), rs.getString("detalle"), rs.getDouble("total")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean actualizar(Caja caja) {
        String sql = "UPDATE caja SET fecha = ?, detalle = ?, total = ? WHERE codigo = ? AND tipo_movimiento = ?";
        try (Connection conn = Conexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                        stmt.setString(1, caja.getFecha());
            stmt.setString(2, caja.getDetalle());
            stmt.setDouble(3, caja.getTotal());
            stmt.setInt(4, caja.getCodigo());
            stmt.setString(5, tipo_movimiento);  // 👈 Asegura que solo actualice de tipo material
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int codigo) {
        String sql = "DELETE FROM caja WHERE codigo = ? AND tipo_movimiento = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            stmt.setString(2, tipo_movimiento);  // 👈 Asegura que solo borre si es material
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
