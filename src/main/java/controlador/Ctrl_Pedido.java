/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Conexion;
import modelo.Pedido;

/**
 *
 * @author ZenBook
 */
public class Ctrl_Pedido {
    // Clase pública y estática para poder acceder desde fuera

    public static class MaterialConDetalles {

        private Pedido pedido;
        private String nombreCliente;

        public MaterialConDetalles(Pedido pedido, String nombreCliente) {
            this.pedido = pedido;
            this.nombreCliente = nombreCliente;

        }

        // Getters
        public Pedido getPedido() {
            return pedido;
        }

        public String getNombreCliente() {
            return nombreCliente != null ? nombreCliente : "Sin cliente";
        }

    }

    public int insertar(Pedido pedido) {
        Connection con = Conexion.getConnection();
        String sql = "INSERT INTO pedido (nombre, preciototal, estado, fecha_inicio, fecha_fin, cliente_codigo) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pedido.getNombre());
            ps.setDouble(2, pedido.getPreciototal());
            ps.setString(3, pedido.getEstado());
            ps.setDate(4, new java.sql.Date(pedido.getFecha_inicio().getTime()));
            ps.setDate(5, new java.sql.Date(pedido.getFecha_fin().getTime()));
            ps.setInt(6, pedido.getIdCliente());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1); // Devuelve el ID generado
                }
            }
            con.close();
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

public List<MaterialConDetalles> obtenerMateriales() {
    List<MaterialConDetalles> lista = new ArrayList<>();
    String sql = "SELECT p.*, CONCAT(c.nombre, ' ', c.apellido) AS nombre_cliente_completo " +
                 "FROM pedido p " +
                 "LEFT JOIN cliente c ON p.cliente_codigo = c.codigo";

    try (Connection con = Conexion.getConnection();
         PreparedStatement stmt = con.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Pedido pedido = new Pedido(
                rs.getInt("id_pedido"),
                rs.getString("nombre"),
                rs.getDouble("preciototal"),
                rs.getString("estado"),
                rs.getDate("fecha_inicio"),
                rs.getDate("fecha_fin"),
                rs.getInt("cliente_codigo")
            );
            
            String nombreClienteCompleto = rs.getString("nombre_cliente_completo");
            if (rs.wasNull()) nombreClienteCompleto = "Sin cliente";
            
            lista.add(new MaterialConDetalles(pedido, nombreClienteCompleto));
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al obtener materiales: " + e.getMessage());
        e.printStackTrace();
    }
    return lista;
}

    public boolean actualizar(Pedido pedido) {
        String sql = "UPDATE pedido SET nombre = ?, preciototal = ?, estado = ?, fecha_inicio = ?, fecha_fin = ?, "
                + "cliente_codigo = ?, "
                + "WHERE id_pedido = ? ";

        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, pedido.getNombre());
            stmt.setDouble(2, pedido.getPreciototal());
            stmt.setString(3, pedido.getEstado());
            stmt.setDate(4, new java.sql.Date(pedido.getFecha_inicio().getTime()));
            stmt.setDate(5, new java.sql.Date(pedido.getFecha_fin().getTime()));
            stmt.setInt(6, pedido.getIdCliente());
            stmt.setInt(7, pedido.getId_pedido());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar pedido: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idPedido) {
        String sql = "DELETE FROM pedido WHERE id_pedido = ? ";

        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idPedido);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar material: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
