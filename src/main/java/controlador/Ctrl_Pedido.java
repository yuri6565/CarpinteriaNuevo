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
import modelo.PedidoDetalle;

/**
 *
 * @author ZenBook
 */
public class Ctrl_Pedido {

    // Clase para combinar pedido y nombre del cliente
    public static class MaterialConDetalles {

        private Pedido pedido;
        private String nombreCliente;

        public MaterialConDetalles(Pedido pedido, String nombreCliente) {
            this.pedido = pedido;
            this.nombreCliente = nombreCliente;
        }

        public Pedido getPedido() {
            return pedido;
        }

        public String getNombreCliente() {
            return nombreCliente != null ? nombreCliente : "Sin cliente";
        }
    }

    // Insertar un pedido y sus detalles
    public int insertar(Pedido pedido, List<PedidoDetalle> detalles) {
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    int idPedido = -1;

    try {
        con = Conexion.getConnection();
        con.setAutoCommit(false); // Iniciar transacción

        // Insertar el pedido
        String sql = "INSERT INTO pedido (nombre, estado, fecha_inicio, fecha_fin, cliente_codigo) VALUES (?, ?, ?, ?, ?)";
        stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, pedido.getNombre());
        stmt.setString(2, pedido.getEstado());
        stmt.setDate(3, new java.sql.Date(pedido.getFecha_inicio().getTime()));
        stmt.setDate(4, new java.sql.Date(pedido.getFecha_fin().getTime()));
        stmt.setInt(5, pedido.getIdCliente());
        stmt.executeUpdate();

        // Obtener el ID del pedido generado
        rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            idPedido = rs.getInt(1);
        }

        // Insertar los detalles y luego los registros en produccion
        if (detalles != null && !detalles.isEmpty()) {
            String sqlDetalle = "INSERT INTO detalle_pedido (descripcion, cantidad, dimension, precio_unitario, subtotal, total, pedido_id_pedido) VALUES (?, ?, ?, ?, ?, ?, ?)";
            String sqlProduccion = "INSERT INTO produccion (fecha_inicio, fecha_fin, estado, detalle_pedido_iddetalle_pedido) VALUES (?, ?, 'pendiente', ?)";
            
            stmt = con.prepareStatement(sqlDetalle, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement stmtProduccion = con.prepareStatement(sqlProduccion);
            
            for (PedidoDetalle detalle : detalles) {
                // Insertar detalle
                stmt.setString(1, detalle.getDescripcion());
                stmt.setInt(2, detalle.getCantidad());
                stmt.setString(3, detalle.getDimensiones());
                stmt.setDouble(4, detalle.getPrecioUnitario());
                stmt.setDouble(5, detalle.getSubtotal());
                stmt.setDouble(6, detalle.getTotal());
                stmt.setInt(7, idPedido);
                stmt.executeUpdate();

                // Obtener el ID del detalle generado
                rs = stmt.getGeneratedKeys();
                int idDetalle = -1;
                if (rs.next()) {
                    idDetalle = rs.getInt(1);
                }

                // Insertar en la tabla produccion
                stmtProduccion.setDate(1, new java.sql.Date(pedido.getFecha_inicio().getTime()));
                stmtProduccion.setDate(2, new java.sql.Date(pedido.getFecha_fin().getTime()));
                // Mapear el estado del pedido al ENUM de produccion
                String estadoProduccion = mapearEstadoProduccion(pedido.getEstado());
                stmtProduccion.setString(3, estadoProduccion);
                stmtProduccion.setInt(4, idDetalle);
                stmtProduccion.executeUpdate();
            }
        }

        con.commit(); // Confirmar transacción
        return idPedido;

    } catch (SQLException e) {
        try {
            if (con != null) {
                con.rollback(); // Revertir transacción en caso de error
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Error al insertar el pedido: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        return -1;
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

// Método auxiliar para mapear el estado del pedido al ENUM de produccion
private String mapearEstadoProduccion(String estadoPedido) {
    // Mapear el estado del pedido al ENUM permitido en la tabla produccion
    switch (estadoPedido.toLowerCase()) {
        case "pendiente":
            return "pendiente";
        case "proceso":
            return "proceso";
        case "completado":
        case "finalizado":
            return "finalizado";
        default:
            return "pendiente"; // Estado por defecto
    }
}
    // Obtener todos los pedidos con el nombre del cliente
    public List<MaterialConDetalles> obtenerMateriales() {
        List<MaterialConDetalles> lista = new ArrayList<>();
        String sql = "SELECT p.*, CONCAT(c.nombre, ' ', c.apellido) AS nombre_cliente_completo "
                + "FROM pedido p "
                + "LEFT JOIN cliente c ON p.cliente_codigo = c.codigo";

        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pedido pedido = new Pedido(
                        rs.getInt("id_pedido"),
                        rs.getString("nombre"),
                        rs.getString("estado"),
                        rs.getDate("fecha_inicio"),
                        rs.getDate("fecha_fin"),
                        rs.getInt("cliente_codigo")
                );

                String nombreClienteCompleto = rs.getString("nombre_cliente_completo");
                if (rs.wasNull()) {
                    nombreClienteCompleto = "Sin cliente";
                }

                lista.add(new MaterialConDetalles(pedido, nombreClienteCompleto));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener materiales: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return lista;
    }

    public MaterialConDetalles obtenerPedidoPorId(int idPedido) {
        String sql = "SELECT p.*, CONCAT(c.nombre, ' ', c.apellido) AS nombre_cliente_completo "
                + "FROM pedido p "
                + "LEFT JOIN cliente c ON p.cliente_codigo = c.codigo "
                + "WHERE p.id_pedido = ?";

        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idPedido);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Pedido pedido = new Pedido(
                        rs.getInt("id_pedido"),
                        rs.getString("nombre"),
                        rs.getString("estado"),
                        rs.getDate("fecha_inicio"),
                        rs.getDate("fecha_fin"),
                        rs.getInt("cliente_codigo")
                );
                String nombreClienteCompleto = rs.getString("nombre_cliente_completo");
                if (rs.wasNull()) {
                    nombreClienteCompleto = "Sin cliente";
                }
                return new MaterialConDetalles(pedido, nombreClienteCompleto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el pedido: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return null;
    }

    public List<PedidoDetalle> obtenerDetallesPorPedido(int idPedido) {
        List<PedidoDetalle> detalles = new ArrayList<>();
        String sql = "SELECT * FROM detalle_pedido WHERE pedido_id_pedido = ?";

        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idPedido);
            ResultSet rs = stmt.executeQuery();

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
            JOptionPane.showMessageDialog(null, "Error al obtener detalles del pedido: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return detalles;
    }

    // Actualizar un pedido
    public boolean actualizar(Pedido pedido) {
        String sql = "UPDATE pedido SET nombre = ?, estado = ?, fecha_inicio = ?, fecha_fin = ?, cliente_codigo = ? WHERE id_pedido = ?";

        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, pedido.getNombre());
            stmt.setString(2, pedido.getEstado());
            stmt.setDate(3, new java.sql.Date(pedido.getFecha_inicio().getTime()));
            stmt.setDate(4, new java.sql.Date(pedido.getFecha_fin().getTime()));
            stmt.setInt(5, pedido.getIdCliente());
            stmt.setInt(6, pedido.getId_pedido());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar pedido: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar un pedido y sus detalles
    public boolean eliminarPedido(int idPedido) {
        Connection con = null;
        try {
            con = Conexion.getConnection();
            con.setAutoCommit(false); // Iniciar transacción

            // Eliminar detalles asociados
            String sqlDeleteDetalles = "DELETE FROM detalle_pedido WHERE pedido_id_pedido = ?";
            try (PreparedStatement stmtDetalles = con.prepareStatement(sqlDeleteDetalles)) {
                stmtDetalles.setInt(1, idPedido);
                stmtDetalles.executeUpdate();
            }

            // Eliminar el pedido
            String sqlDeletePedido = "DELETE FROM pedido WHERE id_pedido = ?";
            try (PreparedStatement stmtPedido = con.prepareStatement(sqlDeletePedido)) {
                stmtPedido.setInt(1, idPedido);
                int rowsAffected = stmtPedido.executeUpdate();
                if (rowsAffected == 0) {
                    throw new SQLException("No se encontró el pedido con ID: " + idPedido);
                }
            }

            con.commit(); // Confirmar transacción
            return true;

        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback(); // Revertir transacción en caso de error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
