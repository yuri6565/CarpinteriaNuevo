package controlador;

import modelo.Cotizacion;
import modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.Cotizacion.cotizacion;

public class CotizacionDAO {
//deep

public static int guardarCotizacion(Cotizacion cotizacion, DefaultTableModel modelo) {
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int idGenerado = 1;

    try {
        con = Conexion.getConnection();
        con.setAutoCommit(false); // Iniciar transacción

        // 1. Insertar cabecera de la cotización
        String sqlCabecera = "INSERT INTO cotizacion (fecha, cliente_codigo, usuario_id_usuario, total) VALUES (?, ?, ?, ?)";
        pstmt = con.prepareStatement(sqlCabecera, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setDate(1, new java.sql.Date(cotizacion.getFecha().getTime()));
        pstmt.setInt(2, cotizacion.getClienteCodigo());
        pstmt.setInt(3, cotizacion.getUsuarioIdUsuario());
        pstmt.setDouble(4, cotizacion.getTotal());

        int filasAfectadas = pstmt.executeUpdate();
        if (filasAfectadas == 0) {
            throw new SQLException("No se pudo insertar la cabecera de cotización");
        }

        // Obtener el ID generado
        rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
            idGenerado = rs.getInt(1);
            System.out.println("DEBUG: ID generado para cabecera: " + idGenerado);
        } else {
            throw new SQLException("No se pudo obtener el ID de la cotización");
        }

        // 2. Insertar detalles de la cotización
        String sqlDetalle = "INSERT INTO detalle_cotizacion (cotizacion_id_cotizacion, detalle, unidad, cantidad, valor_unitario, subtotal) VALUES (?, ?, ?, ?, ?, ?)";
        pstmt = con.prepareStatement(sqlDetalle);

        for (int i = 0; i < modelo.getRowCount(); i++) {
            Object detalleObj = modelo.getValueAt(i, 0);
            Object unidadObj = modelo.getValueAt(i, 1);
            Object cantidadObj = modelo.getValueAt(i, 2);
            Object valorObj = modelo.getValueAt(i, 3);
            Object subtotalObj = modelo.getValueAt(i, 4);

            System.out.println("DEBUG: Fila " + i + ": detalle=" + detalleObj + ", unidad=" + unidadObj + ", cantidad=" + cantidadObj + ", valor=" + valorObj + ", subtotal=" + subtotalObj);

            String detalle = (detalleObj != null) ? detalleObj.toString().trim() : "Sin detalle";
            String unidad = (unidadObj != null) ? unidadObj.toString().trim() : "Unidad";
            int cantidad = (cantidadObj != null) ? Integer.parseInt(cantidadObj.toString().trim()) : 0;

            String valorStr = (valorObj != null) ? valorObj.toString().trim().replace("$", "").replace(",", "") : "0";
            double valorUnitario = Double.parseDouble(valorStr);

            String subtotalStr = (subtotalObj != null) ? subtotalObj.toString().trim().replace("$", "").replace(",", "") : "0";
            double subtotal = Double.parseDouble(subtotalStr);

            if (detalle.isEmpty()) {
                detalle = "Sin detalle";
            }
            if (unidad.isEmpty()) {
                unidad = "Unidad";
            }

            // Insertar detalle
            pstmt.setInt(1, idGenerado);
            pstmt.setString(2, detalle);
            pstmt.setString(3, unidad);
            pstmt.setInt(4, cantidad);
            pstmt.setDouble(5, valorUnitario);
            pstmt.setDouble(6, subtotal);
            pstmt.addBatch();
        }

        // Ejecutar todos los inserts de detalles
        int[] filasAfectadasDetalles = pstmt.executeBatch();
        System.out.println("DEBUG: Filas afectadas en detalles: " + filasAfectadasDetalles.length);
        if (filasAfectadasDetalles.length != modelo.getRowCount()) {
            throw new SQLException("No se insertaron todos los detalles de la cotización");
        }

        con.commit(); // Confirmar transacción
        return idGenerado;
    } catch (SQLException e) {
        try {
            if (con != null) {
                con.rollback();
                System.out.println("DEBUG: Rollback realizado por: " + e.getMessage());
            }
        } catch (SQLException ex) {
            System.err.println("Error al hacer rollback: " + ex.getMessage());
        }
        System.err.println("Error al guardar cotización: " + e.getMessage());
        e.printStackTrace();
        return -1;
    } catch (NumberFormatException e) {
        System.err.println("Error en formato de números: " + e.getMessage());
        return -1;
    } finally {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar conexiones: " + e.getMessage());
        }
    }
}

    public int guardarCotizaciones(List<Cotizacion> cotizaciones) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int idGenerado = -1;

        try {
            con = Conexion.getConnection();
            con.setAutoCommit(false);

            // Validar que haya al menos un item
            if (cotizaciones.isEmpty()) {
                throw new SQLException("La lista de cotizaciones está vacía");
            }

            // Insertar cabecera
            String sqlCabecera = "INSERT INTO cotizacion (fecha, cliente_codigo, usuario_id_usuario, total) VALUES (?, ?, ?, ?)";
            pstmt = con.prepareStatement(sqlCabecera, PreparedStatement.RETURN_GENERATED_KEYS);

            // Usar la fecha de la primera cotización
            pstmt.setDate(1, new java.sql.Date(cotizaciones.get(0).getFecha().getTime()));
            pstmt.setInt(2, cotizaciones.get(0).getClienteCodigo());
            pstmt.setInt(3, cotizaciones.get(0).getUsuarioIdUsuario());
            pstmt.setDouble(4, cotizaciones.get(0).getTotal());

            pstmt.executeUpdate();

            // Obtener ID generado
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }

            // Insertar detalles
            String sqlDetalle = "INSERT INTO detalle_cotizacion (cotizacion_id_cotizacion, detalle, unidad, cantidad, valor_unitario, subtotal) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sqlDetalle);

            for (Cotizacion cot : cotizaciones) {
                pstmt.setInt(1, idGenerado);
                pstmt.setString(2, cot.getDetalle());
                pstmt.setString(3, cot.getUnidad());
                pstmt.setInt(4, cot.getCantidad());
                pstmt.setDouble(5, cot.getValorUnitario());
                pstmt.setDouble(6, cot.getSubTotal());
                pstmt.addBatch();
            }

            pstmt.executeBatch();
            con.commit();
            return idGenerado;
        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return 1;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Object[]> obtenerCotizaciones() {
        List<Object[]> cotizaciones = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = Conexion.getConnection();
            String sql = "SELECT c.id_cotizacion, c.fecha, c.total, CONCAT(cl.nombre, ' ', cl.apellido) AS cliente "
                    + "FROM cotizacion c "
                    + "LEFT JOIN cliente cl ON c.cliente_codigo = cl.codigo "
                    + "ORDER BY c.id_cotizacion DESC";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                cotizaciones.add(new Object[]{
                    rs.getInt("id_cotizacion"),
                    "Cotización " + rs.getInt("id_cotizacion"),
                    rs.getString("cliente"),
                    new java.text.SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("fecha")),
                    "Detalles",
                    "Ver"
                });
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener cotizaciones: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al cargar las cotizaciones: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexiones: " + e.getMessage());
            }
        }
        return cotizaciones;
    }

}
