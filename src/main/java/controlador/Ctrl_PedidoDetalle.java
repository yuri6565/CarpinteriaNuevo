/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Conexion;
import modelo.PedidoDetalle;

/**
 *
 * @author ZenBook
 */
public class Ctrl_PedidoDetalle {
    // Insertar un detalle específico (por si necesitas hacerlo por separado)
    public int insertar(PedidoDetalle detalle, int idPedido) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int idDetalle = -1;

        try {
            conn = Conexion.getConnection();
            String sql = "INSERT INTO detalle_pedido (descripcion, cantidad, dimension, precio_unitario, subtotal, total, pedido_id_pedido) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, detalle.getDescripcion());
            stmt.setInt(2, detalle.getCantidad());
            stmt.setString(3, detalle.getDimensiones());
            stmt.setDouble(4, detalle.getPrecioUnitario());
            stmt.setDouble(5, detalle.getSubtotal());
            stmt.setDouble(6, detalle.getTotal());
            stmt.setInt(7, idPedido);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idDetalle = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return idDetalle;
    }

    // Obtener detalles de un pedido (opcional)
    public List<PedidoDetalle> obtenerPorPedido(int idPedido) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<PedidoDetalle> detalles = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            String sql = "SELECT * FROM detalle_pedido WHERE pedido_id_pedido = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPedido);
            rs = stmt.executeQuery();

            while (rs.next()) {
                PedidoDetalle detalle = new PedidoDetalle(
                    rs.getInt("iddetalle_pedido"),
                    rs.getString("descripcion"),
                    rs.getInt("cantidad"),
                    rs.getString("dimension"),
                    rs.getDouble("precio_unitario"),
                    rs.getDouble("subtotal"),
                    rs.getDouble("total"),
                    rs.getInt("pedido_id_pedido")
                );
                detalles.add(detalle);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return detalles;
    }
}
