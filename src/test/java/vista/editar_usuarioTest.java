/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package vista;

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
public class editar_usuarioTest {
    
    public editar_usuarioTest() {
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
     * Test of isGuardado method, of class editar_usuario.
     */
    @Test
    public void testIsGuardado() {
        System.out.println("isGuardado");
        editar_usuario instance = null;
        boolean expResult = false;
        boolean result = instance.isGuardado();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of main method, of class editar_usuario.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        editar_usuario.main(args);
        // TODO review the generated test code and remove the default call to fail.
      
    }
    
}
