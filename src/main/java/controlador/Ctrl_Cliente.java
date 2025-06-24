package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.Conexion;

public class Ctrl_Cliente {

    public boolean guardar(Cliente objeto) {
        boolean respuesta = false;
        Connection con = Conexion.getConnection();

        try {
            // Verificar si el cliente ya existe
            if (existeCliente(objeto.getIdentificacion(), String.valueOf(objeto.getNumero()))) {
                JOptionPane.showMessageDialog(null, "El número de identificación ya está registrado.");
                return false;
            }

            String sql = "INSERT INTO cliente (identificacion, numero, nombre, apellido, telefono, direccion) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement consulta = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            consulta.setString(1, objeto.getIdentificacion());
            consulta.setInt(2, objeto.getNumero());
            consulta.setString(3, objeto.getNombre());
            consulta.setString(4, objeto.getApellido());
            consulta.setString(5, objeto.getTelefono());
            consulta.setString(6, objeto.getDireccion());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
                // Opcional: Obtener el ID generado
                ResultSet generatedKeys = consulta.getGeneratedKeys();
                if (generatedKeys.next()) {
                    objeto.setid_cliente(generatedKeys.getInt(1)); // Actualizar el ID en el objeto
                }
            }

            consulta.close();
            con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar cliente: " + e.getMessage());
            e.printStackTrace(); // Para depuración
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return respuesta;
    }

    public boolean existeCliente(String tipo, String numero) {
        boolean existe = false;
        Connection con = Conexion.getConnection();

        try {
            String sql = "SELECT COUNT(*) FROM cliente WHERE identificacion = ? AND numero = ?";
            PreparedStatement consulta = con.prepareStatement(sql);
            consulta.setString(1, tipo);
            consulta.setInt(2, Integer.parseInt(numero));

            ResultSet rs = consulta.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                existe = true;
            }

            rs.close();
            consulta.close();
            con.close();

        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error al verificar cliente: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return existe;
    }

    public List<Cliente> obtenerClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setid_cliente(rs.getInt("codigo"));
                cliente.setIdentificacion(rs.getString("identificacion"));
                cliente.setNumero(rs.getInt("numero"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setDireccion(rs.getString("direccion"));
                lista.add(cliente);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener clientes: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    public Cliente obtenerClientePorid(int id_cliente) {
        Cliente cliente = null;
        Connection con = Conexion.getConnection();

        try {
            String sql = "SELECT * FROM cliente WHERE codigo = ?";
            PreparedStatement consulta = con.prepareStatement(sql);
            consulta.setInt(1, id_cliente);

            ResultSet rs = consulta.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setid_cliente(rs.getInt("codigo"));
                cliente.setIdentificacion(rs.getString("identificacion"));
                cliente.setNumero(rs.getInt("numero"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setDireccion(rs.getString("direccion"));
            }

            rs.close();
            consulta.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener cliente: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return cliente;
    }

    public boolean editar(Cliente objeto, int id_cliente) {
        boolean respuesta = false;
        Connection con = Conexion.getConnection();

        try {
            String sql = "UPDATE cliente SET identificacion = ?, numero = ?, nombre = ?, apellido = ?, telefono = ?, direccion = ? WHERE codigo = ?";
            PreparedStatement consulta = con.prepareStatement(sql);
            consulta.setString(1, objeto.getIdentificacion());
            consulta.setInt(2, objeto.getNumero());
            consulta.setString(3, objeto.getNombre());
            consulta.setString(4, objeto.getApellido());
            consulta.setString(5, objeto.getTelefono());
            consulta.setString(6, objeto.getDireccion());
            consulta.setInt(7, id_cliente);

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            consulta.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al editar cliente: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return respuesta;
    }

    public boolean eliminar(int id_cliente) {
        boolean respuesta = false;
        Connection con = Conexion.getConnection();

        try {
            String sql = "DELETE FROM cliente WHERE codigo = ?";
            PreparedStatement consulta = con.prepareStatement(sql);
            consulta.setInt(1, id_cliente);

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            consulta.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar cliente: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return respuesta;
    }
}