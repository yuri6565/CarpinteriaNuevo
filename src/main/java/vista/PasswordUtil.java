/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static boolean verificarContrasena(String contrasenaPlana, String contrasenaHasheada) {
        return encoder.matches(contrasenaPlana, contrasenaHasheada);
    }

    public static String encriptarContrasena(String contrasena) {
        return encoder.encode(contrasena);
    }
}

/**
 *
 * @author Personal
 */

