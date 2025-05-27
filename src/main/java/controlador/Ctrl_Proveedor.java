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
import modelo.ProveedorDatos;

public class Ctrl_Proveedor {

    public int guardar(ProveedorDatos objeto) {
        int idGenerado = -1;
        try (Connection con = Conexion.getConnection();
             PreparedStatement consulta = con.prepareStatement(
                 "INSERT INTO proveedor (nombre, correo_electronico, telefono, direccion) VALUES (?, ?, ?, ?)",
    Statement.RETURN_GENERATED_KEYS
       )) {

            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getCorreo_electronico());
            consulta.setString(3, objeto.getTelefono());
            consulta.setString(4, objeto.getDireccion());

            if (consulta.executeUpdate() > 0) {
                ResultSet generatedKeys = consulta.getGeneratedKeys();
                if (generatedKeys.next()) {
                    idGenerado = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar Proveedor: " + e.getMessage());
        }
        return idGenerado;
    }

public List<ProveedorDatos> obtenerProveedores() {
    List<ProveedorDatos> lista = new ArrayList<>();
    String sql = "SELECT id_proveedor, nombre, correo_electronico, telefono, direccion FROM proveedor";

    try (Connection con = Conexion.getConnection();
         PreparedStatement stmt = con.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            ProveedorDatos datosprove = new ProveedorDatos();
            datosprove.setId_proveedor(rs.getInt("id_proveedor"));
            datosprove.setNombre(rs.getString("nombre"));
            datosprove.setCorreo_electronico(rs.getString("correo_electronico"));
            datosprove.setTelefono(rs.getString("telefono"));
            datosprove.setDireccion(rs.getString("direccion"));
            lista.add(datosprove);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al obtener proveedores: " + e.getMessage());
        e.printStackTrace();
    }
    return lista;
}

    public ProveedorDatos obtenerProveedorPorid(int id_proveedor) {
        ProveedorDatos datosproveedor = null;
        String sql = "SELECT * FROM proveedor WHERE id_proveedor = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement consulta = con.prepareStatement(sql)) {
            consulta.setInt(1, id_proveedor);
            try (ResultSet rs = consulta.executeQuery()) {
                if (rs.next()) {
                    datosproveedor = new ProveedorDatos();
                    datosproveedor.setId_proveedor(rs.getInt("id_proveedor"));
                    datosproveedor.setNombre(rs.getString("nombre"));
                    datosproveedor.setCorreo_electronico(rs.getString("correo_electronico"));
                    datosproveedor.setTelefono(rs.getString("telefono"));
                    datosproveedor.setDireccion(rs.getString("direccion"));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener proveedor: " + e.getMessage());
        }
        return datosproveedor;
    }

    public boolean editar(ProveedorDatos objeto, int id_proveedor) {
        boolean respuesta = false;
        String sql = "UPDATE proveedor SET nombre = ?, correo_electronico = ?, telefono = ?, direccion = ? WHERE id_proveedor = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement consulta = con.prepareStatement(sql)) {
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getCorreo_electronico());
            consulta.setString(3, objeto.getTelefono());
            consulta.setString(4, objeto.getDireccion());
            consulta.setInt(5, id_proveedor);

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al editar proveedor: " + e.getMessage());
        }
        return respuesta;
    }

    public boolean eliminar(int id_proveedor) {
        boolean respuesta = false;
        try (Connection con = Conexion.getConnection()) {
            // Primero eliminar relaciones en carpinteriasistema_suministra
            try (PreparedStatement consultaSuministra = con.prepareStatement(
                "DELETE FROM suministra WHERE proveedor_id_proveedor = ?")) {
                consultaSuministra.setInt(1, id_proveedor);
                consultaSuministra.executeUpdate();
            }

            // Luego eliminar el proveedor
            try (PreparedStatement consulta = con.prepareStatement(
                "DELETE FROM proveedor WHERE id_proveedor = ?")) {
                consulta.setInt(1, id_proveedor);
                if (consulta.executeUpdate() > 0) {
                    respuesta = true;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar proveedor: " + e.getMessage());
            e.printStackTrace();
        }
        return respuesta;
    }

   public List<String> obtenerTodosNombresInventario() {
        List<String> nombres = new ArrayList<>();
        String sql = "SELECT nombre FROM inventario";
        
        try (Connection con = new Conexion().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                nombres.add(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // En caso de error, devolvemos null (manejar en el llamador)
        }
        
        return nombres;
    }
    

    public int obtenerIdInventarioPorNombre(String nombre) {
        String sql = "SELECT id_inventario FROM inventario WHERE nombre = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_inventario");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

public boolean guardarSuministra(int idProveedor, int idInventario) {
    String sql = "INSERT INTO suministra (proveedor_id_proveedor, inventario_id_inventario) VALUES (?, ?)";
    try (Connection con = Conexion.getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setInt(1, idProveedor);
        stmt.setInt(2, idInventario);
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al guardar relación proveedor-producto: " + e.getMessage());
        return false;
    }
}

    public List<String> obtenerProveedoresConProductos() {
        List<String> resultados = new ArrayList<>();
        String sql = "SELECT p.nombre AS nombre_proveedor, i.nombre AS nombre_producto " +
                     "FROM proveedor p " +
                     "INNER JOIN suministra s ON p.id_proveedor = s.proveedor_id_proveedor " +
                     "INNER JOIN inventario i ON s.inventario_id_inventario = i.id_inventario";

        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String proveedor = rs.getString("nombre_proveedor");
                String producto = rs.getString("nombre_producto");
                resultados.add("Proveedor: " + proveedor + " | Producto: " + producto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener proveedores con productos: " + e.getMessage());
            e.printStackTrace();
        }
        return resultados;
    }
    
    public List<String> obtenerProductosDeProveedor(int idProveedor) {
    List<String> productos = new ArrayList<>();
    String sql = "SELECT i.nombre FROM suministra s " +
                 "JOIN inventario i ON s.inventario_id_inventario = i.id_inventario " +
                 "WHERE s.proveedor_id_proveedor = ?";
    try (Connection con = Conexion.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, idProveedor);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                productos.add(rs.getString("nombre"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al obtener productos del proveedor: " + e.getMessage());
    }
    return productos;
}


}