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
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Caja;
import modelo.Conexion;
import modelo.Ingresos;

/**
 *
 * @author ADSO
 */
public class Ctrl_CajaIngresos {

    private Connection conn;

    public Ctrl_CajaIngresos() {
        conn = Conexion.getConnection();
    }

    public class IngresoConDetalles {

        private int idPedido;
        private String numPedido;
        private String nombreCliente;
        private String nombrePedido; // Nuevo campo para el nombre del pedido
        private double montoTotal;
        private double pagado;
        private double debido;
        private List<Ingresos> abonos;

        public IngresoConDetalles(int idPedido, String numPedido, String nombreCliente, String nombrePedido, double montoTotal, double pagado, double debido, List<Ingresos> abonos) {
            this.idPedido = idPedido;
            this.numPedido = numPedido;
            this.nombreCliente = nombreCliente;
            this.nombrePedido = nombrePedido; // Inicialización del nuevo campo
            this.montoTotal = montoTotal;
            this.pagado = pagado;
            this.debido = debido;
            this.abonos = abonos;
        }

        // Getters
        public int getIdPedido() {
            return idPedido;
        }

        public String getNumPedido() {
            return numPedido;
        }

        public String getNombreCliente() {
            return nombreCliente;
        }

        public String getNombrePedido() { // Nuevo getter
            return nombrePedido;
        }

        public double getMontoTotal() {
            return montoTotal;
        }

        public double getPagado() {
            return pagado;
        }

        public double getDebido() {
            return debido;
        }

        public List<Ingresos> getAbonos() {
            return abonos;
        }
    }

    public List<IngresoConDetalles> obtenerIngresos() {
        List<IngresoConDetalles> ingresos = new ArrayList<>();
        String sql = "SELECT p.id_pedido, p.num_pedido, p.nombre AS nombre_pedido, "
                + "CONCAT(c.nombre, ' ', c.apellido) AS nombre_cliente, "
                + "COALESCE(SUM(dp.subtotal), 0) AS monto_total "
                + "FROM pedido p "
                + "LEFT JOIN cliente c ON p.cliente_codigo = c.codigo "
                + "LEFT JOIN detalle_pedido dp ON p.id_pedido = dp.pedido_id_pedido "
                + "GROUP BY p.id_pedido, p.num_pedido, p.nombre, c.nombre, ' ', c.apellido";
        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int idPedido = rs.getInt("id_pedido");
                String numPedido = rs.getString("num_pedido");
                String nombreCliente = rs.getString("nombre_cliente");
                String nombrePedido = rs.getString("nombre_pedido"); // Nuevo campo
                double montoTotal = rs.getDouble("monto_total");
                List<Ingresos> abonos = obtenerAbonosPorPedido(idPedido);
                double pagado = abonos.stream().mapToDouble(Ingresos::getMonto).sum();
                double debido = montoTotal - pagado;
                ingresos.add(new IngresoConDetalles(idPedido, numPedido, nombreCliente, nombrePedido, montoTotal, pagado, debido, abonos));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingresos;
    }

    public List<Ingresos> obtenerAbonosPorPedido(int idPedido) {
        List<Ingresos> abonos = new ArrayList<>();
        String sql = "SELECT id_abono, num_abono, fecha_pago, monto, metodo_pago, referencia, observacion, pedido_id_pedido "
                + "FROM pago_abono WHERE pedido_id_pedido = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPedido);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    abonos.add(new Ingresos(
                            rs.getInt("id_abono"),
                            rs.getInt("num_abono"), // Cambiado a rs.getInt
                            rs.getDate("fecha_pago"),
                            rs.getDouble("monto"),
                            rs.getString("metodo_pago"),
                            rs.getString("referencia"),
                            rs.getString("observacion"),
                            rs.getInt("pedido_id_pedido")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return abonos;
    }

    public boolean registrarAbono(int idPedido, double monto, String metodoPago, String referencia, String observacion) {
        // Cambiamos la consulta para obtener el máximo num_abono POR PEDIDO
        String sqlSelectMax = "SELECT COALESCE(MAX(num_abono), 0) AS max_num FROM pago_abono WHERE pedido_id_pedido = ?";
        String sqlInsert = "INSERT INTO pago_abono (pedido_id_pedido, num_abono, fecha_pago, monto, metodo_pago, referencia, observacion) "
                + "VALUES (?, ?, NOW(), ?, ?, ?, ?)";

        int newNumAbono = 1; // Valor por defecto si es el primer abono para este pedido

        try (PreparedStatement stmtSelect = conn.prepareStatement(sqlSelectMax)) {
            stmtSelect.setInt(1, idPedido); // Filtramos por pedido_id_pedido
            try (ResultSet rs = stmtSelect.executeQuery()) {
                if (rs.next()) {
                    newNumAbono = rs.getInt("max_num") + 1; // Incrementamos el máximo para este pedido
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener el siguiente número de abono: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {
            stmt.setInt(1, idPedido);
            stmt.setInt(2, newNumAbono);
            stmt.setDouble(3, monto);
            stmt.setString(4, metodoPago);
            stmt.setString(5, referencia);
            stmt.setString(6, observacion);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al registrar el abono: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean eliminarAbono(int idAbono) {
        String sql = "DELETE FROM pago_abono WHERE id_abono = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAbono);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarAbono(int idAbono, double monto, String metodoPago, String referencia, String observacion) {
        String sql = "UPDATE pago_abono SET monto = ?, metodo_pago = ?, referencia = ?, observacion = ? WHERE id_abono = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, monto);
            stmt.setString(2, metodoPago);
            stmt.setString(3, referencia);
            stmt.setString(4, observacion);
            stmt.setInt(5, idAbono);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public IngresoConDetalles obtenerDetallesIngreso(int idPedido) {
        String sql = "SELECT p.id_pedido, p.num_pedido, p.nombre AS nombre_pedido, "
                + "CONCAT(c.nombre, ' ', c.apellido) AS nombre_cliente, "
                + "COALESCE(SUM(dp.subtotal), 0) AS monto_total "
                + "FROM pedido p "
                + "LEFT JOIN cliente c ON p.cliente_codigo = c.codigo "
                + "LEFT JOIN detalle_pedido dp ON p.id_pedido = dp.pedido_id_pedido "
                + "WHERE p.id_pedido = ? "
                + "GROUP BY p.id_pedido, p.num_pedido, p.nombre, c.nombre, c.apellido";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPedido);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String numPedido = rs.getString("num_pedido");
                    String nombreCliente = rs.getString("nombre_cliente");
                    String nombrePedido = rs.getString("nombre_pedido");
                    double montoTotal = rs.getDouble("monto_total");

                    List<Ingresos> abonos = obtenerAbonosPorPedido(idPedido);
                    double pagado = abonos.stream().mapToDouble(Ingresos::getMonto).sum();
                    double debido = montoTotal - pagado;

                    return new IngresoConDetalles(
                            idPedido,
                            numPedido,
                            nombreCliente,
                            nombrePedido,
                            montoTotal,
                            pagado,
                            debido,
                            abonos
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener detalles del ingreso: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null; // Si no encuentra el pedido
    }

    public Ingresos obtenerAbonoPorId(int idAbono) {
        String sql = "SELECT * FROM pago_abono WHERE id_abono = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAbono);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Ingresos(
                            rs.getInt("id_abono"),
                            rs.getInt("num_abono"),
                            rs.getDate("fecha_pago"),
                            rs.getDouble("monto"),
                            rs.getString("metodo_pago"),
                            rs.getString("referencia"),
                            rs.getString("observacion"),
                            rs.getInt("pedido_id_pedido")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
