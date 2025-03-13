package controlador;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Consulta_Usuarios;

public class Ctrl_Usuarios {

    private static final String REMITENTE = "carpinteriajoseabel2025@gmail.com"; 
    private static final String CLAVE_REMITENTE = "cmiu jrxl ppkd ubyp";

    public boolean enviarCodigoRecuperacion(String usuario, String correo) {
        Consulta_Usuarios consulta = new Consulta_Usuarios();
        String codigo = consulta.recuperarCuenta(usuario, correo);

        if (codigo != null) { 
            try {
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");
                    props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");

                Session session = Session.getInstance(props, new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(REMITENTE, CLAVE_REMITENTE);
                    }
                });

                Message mensaje = new MimeMessage(session);
                mensaje.setFrom(new InternetAddress(REMITENTE));
                mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(correo));
                mensaje.setSubject("Código de Recuperación");
                mensaje.setText("Hola " + usuario + ",\n\n"
                        + "Recibimos una solicitud para restablecer tu contraseña. "
                        + "Tu código de recuperación es: " + codigo + "\n\n"
                        + "Si no solicitaste este cambio, ignora este mensaje.\n\n"
                        + "Atentamente,\n"
                        + "El equipo de Carpintería José Abel");

                Transport.send(mensaje);
                return true;
            } catch (MessagingException ex) {
                Logger.getLogger(Ctrl_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;  
    }
}