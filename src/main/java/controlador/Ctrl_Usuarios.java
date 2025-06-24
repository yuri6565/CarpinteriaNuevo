package controlador;


import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import modelo.Consulta_Usuarios;
public class Ctrl_Usuarios {

    private static final String REMITENTE = "carpinteriajoseabel2025@gmail.com";
    private static final String CLAVE_REMITENTE = "cmiujrxlppkdubyp"; // Ensure this is the correct App Password (no spaces)

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
                props.put("mail.smtp.connectiontimeout", "10000"); // 10 seconds
                props.put("mail.smtp.timeout", "10000"); // 10 seconds
                props.put("mail.debug", "true"); // Enable debug output

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
                Logger.getLogger(Ctrl_Usuarios.class.getName()).log(Level.INFO, "Email sent successfully to {0}", correo);
                return true;
            } catch (MessagingException ex) {
                Logger.getLogger(Ctrl_Usuarios.class.getName()).log(Level.SEVERE, "Failed to send email to " + correo, ex);
            }
        } else {
            Logger.getLogger(Ctrl_Usuarios.class.getName()).log(Level.WARNING, "No recovery code generated for user {0}, email {1}", new Object[]{usuario, correo});
        }
        return false;
    }

}