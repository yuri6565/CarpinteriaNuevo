/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package controlador;

import java.util.List;
import modelo.ProveedorDatos;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Personal
 */
public class Ctrl_ProveedorTest {
    
    public Ctrl_ProveedorTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of guardar method, of class Ctrl_Proveedor.
     */
    @Test
    public void testGuardar() {
        System.out.println("guardar");
        ProveedorDatos objeto = null;
        Ctrl_Proveedor instance = new Ctrl_Proveedor();
        int expResult = 0;
        int result = instance.guardar(objeto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerProveedores method, of class Ctrl_Proveedor.
     */
    @Test
    public void testObtenerProveedores() {
        System.out.println("obtenerProveedores");
        Ctrl_Proveedor instance = new Ctrl_Proveedor();
        List<ProveedorDatos> expResult = null;
        List<ProveedorDatos> result = instance.obtenerProveedores();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerProveedorPorid method, of class Ctrl_Proveedor.
     */
    @Test
    public void testObtenerProveedorPorid() {
        System.out.println("obtenerProveedorPorid");
        int id_proveedor = 0;
        Ctrl_Proveedor instance = new Ctrl_Proveedor();
        ProveedorDatos expResult = null;
        ProveedorDatos result = instance.obtenerProveedorPorid(id_proveedor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editar method, of class Ctrl_Proveedor.
     */
    @Test
    public void testEditar() {
        System.out.println("editar");
        ProveedorDatos objeto = null;
        int id_proveedor = 0;
        Ctrl_Proveedor instance = new Ctrl_Proveedor();
        boolean expResult = false;
        boolean result = instance.editar(objeto, id_proveedor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminar method, of class Ctrl_Proveedor.
     */
    @Test
    public void testEliminar() {
        System.out.println("eliminar");
        int id_proveedor = 0;
        Ctrl_Proveedor instance = new Ctrl_Proveedor();
        boolean expResult = false;
        boolean result = instance.eliminar(id_proveedor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerTodosNombresInventario method, of class Ctrl_Proveedor.
     */
    @Test
    public void testObtenerTodosNombresInventario() {
        System.out.println("obtenerTodosNombresInventario");
        Ctrl_Proveedor instance = new Ctrl_Proveedor();
        List<String> expResult = null;
        List<String> result = instance.obtenerTodosNombresInventario();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerIdInventarioPorNombre method, of class Ctrl_Proveedor.
     */
    @Test
    public void testObtenerIdInventarioPorNombre() {
        System.out.println("obtenerIdInventarioPorNombre");
        String nombre = "";
        Ctrl_Proveedor instance = new Ctrl_Proveedor();
        int expResult = 0;
        int result = instance.obtenerIdInventarioPorNombre(nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of guardarSuministra method, of class Ctrl_Proveedor.
     */
    @Test
    public void testGuardarSuministra() {
        System.out.println("guardarSuministra");
        int idProveedor = 0;
        int idInventario = 0;
        Ctrl_Proveedor instance = new Ctrl_Proveedor();
        instance.guardarSuministra(idProveedor, idInventario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerProveedoresConProductos method, of class Ctrl_Proveedor.
     */
    @Test
    public void testObtenerProveedoresConProductos() {
        System.out.println("obtenerProveedoresConProductos");
        Ctrl_Proveedor instance = new Ctrl_Proveedor();
        List<String> expResult = null;
        List<String> result = instance.obtenerProveedoresConProductos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerProductosDeProveedor method, of class Ctrl_Proveedor.
     */
    @Test
    public void testObtenerProductosDeProveedor() {
        System.out.println("obtenerProductosDeProveedor");
        int idProveedor = 0;
        Ctrl_Proveedor instance = new Ctrl_Proveedor();
        List<String> expResult = null;
        List<String> result = instance.obtenerProductosDeProveedor(idProveedor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
