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
    // Clase interna para encapsular los datos combinados

    public static class IngresoConDetalles {

        private Ingresos ingreso;
        private String nombreCliente;
        private String nombreDetallePedido;
        private double montoTotalDetalle;
        private String nombrePedido; // Nuevo campo para el nombre del pedido

        public IngresoConDetalles(Ingresos ingreso, String nombreCliente, String nombreDetallePedido, double montoTotalDetalle, String nombrePedido) {
            this.ingreso = ingreso;
            this.nombreCliente = nombreCliente;
            this.nombreDetallePedido = nombreDetallePedido;
            this.montoTotalDetalle = montoTotalDetalle;
            this.nombrePedido = nombrePedido;
        }

        public Ingresos getIngreso() {
            return ingreso;
        }

        public String getNombreCliente() {
            return nombreCliente;
        }

        public String getNombreDetallePedido() {
            return nombreDetallePedido;
        }

        public double getMontoTotalDetalle() {
            return montoTotalDetalle;
        }

        public String getNombrePedido() { // Nuevo getter
            return nombrePedido;
        }
    }

    public static class PedidoConCliente {

        private int idPedido;
        private String nombrePedido;
        private String nombreCliente;
        private double montoTotal;    // <-- nuevo campo
        private Integer idCliente; // Nuevo campo importante

        public int getIdPedido() {
            return idPedido;
        }

        public void setIdPedido(int idPedido) {
            this.idPedido = idPedido;
        }

        public String getNombrePedido() {
            return nombrePedido;
        }

        public void setNombrePedido(String nombrePedido) {
            this.nombrePedido = nombrePedido;
        }

        public String getNombreCliente() {
            return nombreCliente;
        }

        public void setNombreCliente(String nombreCliente) {
            this.nombreCliente = nombreCliente;
        }

        public double getMontoTotal() {
            return montoTotal;
        }

        public void setMontoTotal(double montoTotal) {
            this.montoTotal = montoTotal;
        }

        public Integer getIdCliente() {
            return idCliente;
        }

        public void setIdCliente(Integer idCliente) {
            this.idCliente = idCliente;
        }

        @Override
        public String toString() {
            return nombrePedido;  // Solo mostramos el nombre aquí
        }

    }

    public boolean guardar(Ingresos ingreso) {
        String sql = "INSERT INTO pago_abono (montoTotal, metodo_pago, fecha_pago, pagado, debido, cliente_codigo, pedido_id_pedido) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, ingreso.getMontoTotal());
            pstmt.setString(2, ingreso.getMetodoPago());
            pstmt.setDate(3, new java.sql.Date(ingreso.getFechaPago().getTime()));
            pstmt.setDouble(4, ingreso.getPagado());
            pstmt.setDouble(5, ingreso.getDebido());
            pstmt.setInt(6, ingreso.getIdCliente());
            pstmt.setInt(7, ingreso.getIdPedido()); // Usar idPedido en lugar de idDetallePedido

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Ingresos ingreso) {
        String sql = "UPDATE pago_abono SET montoTotal = ?, metodo_pago = ?, fecha_pago = ?, pagado = ?, debido = ?, cliente_codigo = ?, pedido_id_pedido = ? WHERE id_abono = ?";
        try (Connection conn = Conexion.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, ingreso.getMontoTotal());
            pstmt.setString(2, ingreso.getMetodoPago());
            pstmt.setDate(3, new java.sql.Date(ingreso.getFechaPago().getTime()));
            pstmt.setDouble(4, ingreso.getPagado());
            pstmt.setDouble(5, ingreso.getDebido());
            pstmt.setInt(6, ingreso.getIdCliente());
            pstmt.setInt(7, ingreso.getIdPedido()); // Usar idPedido
            pstmt.setInt(8, ingreso.getIdAbono());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar un ingreso
    public boolean eliminar(int idAbono) {
        String sql = "DELETE FROM  pago_abono WHERE id_abono = ?";
        try (Connection conn = Conexion.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idAbono);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<IngresoConDetalles> obtenerIngresos() {
        List<IngresoConDetalles> ingresosList = new ArrayList<>();
        String sql = "SELECT pago.*, "
                + "CONCAT(cli.nombre, ' ', cli.apellido) AS nombre_cliente, "
                + "ped.nombre AS nombre_pedido "
                + "FROM pago_abono pago "
                + "LEFT JOIN cliente cli ON pago.cliente_codigo = cli.codigo "
                + "LEFT JOIN pedido ped ON pago.pedido_id_pedido = ped.id_pedido";

        try (Connection conn = Conexion.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Ingresos ingreso = new Ingresos();
                ingreso.setIdAbono(rs.getInt("id_abono"));
                ingreso.setMontoTotal(rs.getDouble("montoTotal"));
                ingreso.setMetodoPago(rs.getString("metodo_pago"));
                ingreso.setFechaPago(rs.getDate("fecha_pago"));
                ingreso.setPagado(rs.getDouble("pagado"));
                ingreso.setDebido(rs.getDouble("debido"));
                ingreso.setIdCliente(rs.getInt("cliente_codigo"));
                ingreso.setIdPedido(rs.getInt("pedido_id_pedido"));

                String nombreCliente = rs.getString("nombre_cliente");
                String nombrePedido = rs.getString("nombre_pedido");

                IngresoConDetalles ingresoConDetalles = new IngresoConDetalles(ingreso, nombreCliente, null, 0.0, nombrePedido);
                ingresosList.add(ingresoConDetalles);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingresosList;
    }

    public IngresoConDetalles obtenerIngresoPorId(int idAbono) {
        String sql = "SELECT pago.*, "
                + "CONCAT(cli.nombre, ' ', cli.apellido) AS nombre_cliente, "
                + "ped.nombre AS nombre_pedido "
                + "FROM pago_abono pago "
                + "LEFT JOIN cliente cli ON pago.cliente_codigo = cli.codigo "
                + "LEFT JOIN pedido ped ON pago.pedido_id_pedido = ped.id_pedido "
                + "WHERE pago.id_abono = ?";

        try (Connection conn = Conexion.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idAbono);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Ingresos ingreso = new Ingresos();
                    ingreso.setIdAbono(rs.getInt("id_abono"));
                    ingreso.setMontoTotal(rs.getDouble("montoTotal"));
                    ingreso.setMetodoPago(rs.getString("metodo_pago"));
                    ingreso.setFechaPago(rs.getDate("fecha_pago"));
                    ingreso.setPagado(rs.getDouble("pagado"));
                    ingreso.setDebido(rs.getDouble("debido"));
                    ingreso.setIdCliente(rs.getInt("cliente_codigo"));
                    ingreso.setIdPedido(rs.getInt("pedido_id_pedido"));

                    String nombreCliente = rs.getString("nombre_cliente");
                    String nombrePedido = rs.getString("nombre_pedido");

                    return new IngresoConDetalles(ingreso, nombreCliente, null, 0.0, nombrePedido);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PedidoConCliente> obtenerPedidosConCliente() {
        List<PedidoConCliente> lista = new ArrayList<>();

        String sql = "SELECT p.id_pedido, "
                + "       p.nombre AS nombre_pedido, "
                + "       CONCAT(c.nombre, ' ', c.apellido) AS nombre_cliente, "
                + "       c.codigo AS id_cliente, "
                + "       COALESCE(SUM(det.subtotal), 0.0) AS monto_total " // Corrección aquí
                + "FROM pedido p "
                + "LEFT JOIN cliente c ON p.cliente_codigo = c.codigo "
                + "LEFT JOIN detalle_pedido det ON p.id_pedido = det.pedido_id_pedido "
                + "GROUP BY p.id_pedido, p.nombre, c.codigo, c.nombre, c.apellido "
                + "HAVING COALESCE(SUM(det.subtotal), 0.0) > 0"; // Solo pedidos con monto > 0

        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            System.out.println("Ejecutando consulta: " + sql); // Log para depuración

            while (rs.next()) {
                PedidoConCliente pc = new PedidoConCliente();
                pc.setIdPedido(rs.getInt("id_pedido"));
                pc.setNombrePedido(rs.getString("nombre_pedido"));
                pc.setNombreCliente(rs.getString("nombre_cliente"));
                pc.setIdCliente(rs.getInt("id_cliente"));
                pc.setMontoTotal(rs.getDouble("monto_total"));

                // Debug: Verificar valores recuperados
                System.out.println("Pedido cargado - ID: " + pc.getIdPedido()
                        + ", Nombre: " + pc.getNombrePedido()
                        + ", Monto: " + pc.getMontoTotal());

                lista.add(pc);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener pedidos: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    public boolean guardarPago(PedidoConCliente pedido, double montoPagado, String metodoPago, Date fechaPago) {
        String sql = "INSERT INTO pago_abono (montoTotal, metodo_pago, fecha_pago, "
                + "pagado, debido, cliente_codigo, pedido_id_pedido) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            // Validar que el pedido tenga cliente asociado
            if (pedido.getIdCliente() == null) {
                throw new SQLException("El pedido no tiene cliente asociado");
            }

            // Calcular valores
            double debido = pedido.getMontoTotal() - montoPagado;

            // Setear parámetros
            ps.setDouble(1, montoPagado); // montoTotal (el monto del abono)
            ps.setString(2, metodoPago);
            ps.setDate(3, new java.sql.Date(fechaPago.getTime()));
            ps.setDouble(4, montoPagado); // pagado
            ps.setDouble(5, debido); // debido
            ps.setInt(6, pedido.getIdCliente()); // cliente_codigo
            ps.setInt(7, pedido.getIdPedido()); // pedido_id_pedido

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected <= 0) {
                System.err.println("No se insertó ninguna fila en pago_abono");
                return false;
            }
            return true;

        } catch (SQLException e) {
            System.err.println("Error al guardar pago: " + e.getMessage());
            throw new RuntimeException("Error al guardar el pago en la base de datos: " + e.getMessage(), e);
        }
    }

}
