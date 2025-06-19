package controlador;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneradorIngresosPDF {
    public void generarPDF(String cliente, DefaultTableModel tablaModel, String total, String archivoSalida, String fecha) {
        System.out.println("Iniciando generación de PDF...");
        try (PDDocument document = new PDDocument()) {
            System.out.println("Documento PDF creado");
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                System.out.println("ContentStream creado");

                // Agregar imagen fija en la parte superior derecha
                try {
                    URL imageUrl = getClass().getResource("/logo_azul.png");
                    if (imageUrl == null) {
                        throw new IOException("No se encontró la imagen 'logo_azul.png' en los recursos.");
                    }
                    PDImageXObject pdImage = PDImageXObject.createFromFile(imageUrl.getPath(), document);
                    float imageWidth = 100;
                    float imageHeight = 100;
                    float xPosition = 595 - imageWidth - 30;
                    float yPositionImage = 842 - imageHeight - 30;
                    contentStream.drawImage(pdImage, xPosition, yPositionImage, imageWidth, imageHeight);
                    System.out.println("Imagen añadida en la posición: (" + xPosition + ", " + yPositionImage + ")");
                } catch (IOException e) {
                    System.out.println("Error al cargar la imagen: " + e.getMessage());
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al cargar la imagen: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Título "Ingresos"
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 750);
                contentStream.showText("Ingresos");
                contentStream.endText();

                // Fecha
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 730);
                contentStream.showText("Fecha Inicio: " + (fecha != null ? fecha : new SimpleDateFormat("dd/MM/yyyy").format(new Date())));
                contentStream.endText();

                // Datos del cliente
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 710);
                contentStream.showText("Cliente: " + (cliente != null ? cliente : "No especificado"));
                contentStream.endText();

                // Sección de Detalles del Pedido (arriba)
                float yPosition = 650;
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(30, yPosition);
                contentStream.showText("Pedido");
                contentStream.newLineAtOffset(80, 0);
                contentStream.showText("Descripción");
                contentStream.newLineAtOffset(120, 0);
                contentStream.showText("Cantidad");
                contentStream.newLineAtOffset(60, 0);
                contentStream.showText("Dimensiones");
                contentStream.newLineAtOffset(80, 0);
                contentStream.showText("Precio Unitario");
                contentStream.newLineAtOffset(90, 0);
                contentStream.showText("Subtotal");
                contentStream.newLineAtOffset(70, 0);
                contentStream.showText("Total");
                contentStream.endText();

                // Dibujar línea horizontal debajo del encabezado
                yPosition -= 5;
                contentStream.setLineWidth(0.5f);
                contentStream.moveTo(30, yPosition);
                contentStream.lineTo(550, yPosition); // Ajusta el ancho según tus necesidades
                contentStream.stroke();

                yPosition -= 15;
                contentStream.setFont(PDType1Font.HELVETICA, 12);

                // Llenar detalles del pedido (excluir la fila de resumen)
                int detailRows = Math.max(0, tablaModel.getRowCount() - 1); // Evitar índices negativos
                for (int i = 0; i < detailRows; i++) {
                    yPosition -= 20;
                    if (yPosition < 400) { // Cambio de página si se acerca al límite
                        contentStream.endText();
                        contentStream.close();
                        page = new PDPage(PDRectangle.A4);
                        document.addPage(page);
                        try (PDPageContentStream newContentStream = new PDPageContentStream(document, page)) {
                            newContentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                            newContentStream.beginText();
                            newContentStream.newLineAtOffset(30, 750);
                            newContentStream.showText("Pedido");
                            newContentStream.newLineAtOffset(80, 0);
                            newContentStream.showText("Descripción");
                            newContentStream.newLineAtOffset(120, 0);
                            newContentStream.showText("Cantidad");
                            newContentStream.newLineAtOffset(60, 0);
                            newContentStream.showText("Dimensiones");
                            newContentStream.newLineAtOffset(80, 0);
                            newContentStream.showText("Precio Unitario");
                            newContentStream.newLineAtOffset(90, 0);
                            newContentStream.showText("Subtotal");
                            newContentStream.newLineAtOffset(70, 0);
                            newContentStream.showText("Total");
                            newContentStream.endText();

                            // Dibujar línea horizontal debajo del encabezado en nueva página
                            yPosition = 745;
                            newContentStream.setLineWidth(0.5f);
                            newContentStream.moveTo(30, yPosition);
                            newContentStream.lineTo(550, yPosition);
                            newContentStream.stroke();

                            yPosition -= 15;
                            newContentStream.setFont(PDType1Font.HELVETICA, 12);
                            newContentStream.beginText();
                            newContentStream.newLineAtOffset(30, yPosition);
                            newContentStream.showText(tablaModel.getValueAt(i, 0) != null ? tablaModel.getValueAt(i, 0).toString() : "");
                            newContentStream.newLineAtOffset(80, 0);
                            newContentStream.showText(tablaModel.getValueAt(i, 1) != null ? tablaModel.getValueAt(i, 1).toString() : "");
                            newContentStream.newLineAtOffset(120, 0);
                            newContentStream.showText(tablaModel.getValueAt(i, 2) != null ? tablaModel.getValueAt(i, 2).toString() : "");
                            newContentStream.newLineAtOffset(60, 0);
                            newContentStream.showText(tablaModel.getValueAt(i, 3) != null ? tablaModel.getValueAt(i, 3).toString() : "");
                            newContentStream.newLineAtOffset(80, 0);
                            newContentStream.showText(tablaModel.getValueAt(i, 4) != null ? tablaModel.getValueAt(i, 4).toString() : "");
                            newContentStream.newLineAtOffset(90, 0);
                            newContentStream.showText(tablaModel.getValueAt(i, 5) != null ? tablaModel.getValueAt(i, 5).toString() : "");
                            newContentStream.newLineAtOffset(70, 0);
                            newContentStream.showText(tablaModel.getValueAt(i, 6) != null ? tablaModel.getValueAt(i, 6).toString() : "");
                            newContentStream.endText();

                            // Dibujar línea horizontal debajo de la fila
                            yPosition -= 20;
                            newContentStream.moveTo(30, yPosition);
                            newContentStream.lineTo(550, yPosition);
                            newContentStream.stroke();
                        }
                    } else {
                        contentStream.beginText();
                        contentStream.newLineAtOffset(30, yPosition);
                        contentStream.showText(tablaModel.getValueAt(i, 0) != null ? tablaModel.getValueAt(i, 0).toString() : "");
                        contentStream.newLineAtOffset(80, 0);
                        contentStream.showText(tablaModel.getValueAt(i, 1) != null ? tablaModel.getValueAt(i, 1).toString() : "");
                        contentStream.newLineAtOffset(120, 0);
                        contentStream.showText(tablaModel.getValueAt(i, 2) != null ? tablaModel.getValueAt(i, 2).toString() : "");
                        contentStream.newLineAtOffset(60, 0);
                        contentStream.showText(tablaModel.getValueAt(i, 3) != null ? tablaModel.getValueAt(i, 3).toString() : "");
                        contentStream.newLineAtOffset(80, 0);
                        contentStream.showText(tablaModel.getValueAt(i, 4) != null ? tablaModel.getValueAt(i, 4).toString() : "");
                        contentStream.newLineAtOffset(90, 0);
                        contentStream.showText(tablaModel.getValueAt(i, 5) != null ? tablaModel.getValueAt(i, 5).toString() : "");
                        contentStream.newLineAtOffset(70, 0);
                        contentStream.showText(tablaModel.getValueAt(i, 6) != null ? tablaModel.getValueAt(i, 6).toString() : "");
                        contentStream.endText();

                        // Dibujar línea horizontal debajo de la fila
                        yPosition -= 5;
                        contentStream.moveTo(30, yPosition);
                        contentStream.lineTo(550, yPosition);
                        contentStream.stroke();
                        yPosition -= 15;
                    }
                }

                // Espacio entre secciones
                yPosition -= 40;

                // Línea horizontal para separar secciones
                contentStream.moveTo(30, yPosition);
                contentStream.lineTo(550, yPosition);
                contentStream.stroke();

                // Sección de Resumen (abajo)
                if (tablaModel.getRowCount() > 0) {
                    int lastRow = tablaModel.getRowCount() - 1;
                    yPosition -= 20;
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(30, yPosition);
                    contentStream.showText("Resumen");
                    contentStream.endText();

                    yPosition -= 20;
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(30, yPosition);
                    contentStream.showText("Monto Total: " + (tablaModel.getValueAt(lastRow, 7) != null ? tablaModel.getValueAt(lastRow, 7).toString() : "0.0"));
                    contentStream.newLineAtOffset(100, 0);
                    contentStream.showText("Pagado: " + (tablaModel.getValueAt(lastRow, 8) != null ? tablaModel.getValueAt(lastRow, 8).toString() : "0.0"));
                    contentStream.newLineAtOffset(80, 0);
                    contentStream.showText("Debido: " + (tablaModel.getValueAt(lastRow, 9) != null ? tablaModel.getValueAt(lastRow, 9).toString() : "0.0"));
                    contentStream.endText();

                    // Dibujar línea horizontal debajo del resumen
                    yPosition -= 5;
                    contentStream.moveTo(30, yPosition);
                    contentStream.lineTo(550, yPosition);
                    contentStream.stroke();
                }

                // Total General
                yPosition -= 40;
                contentStream.beginText();
                contentStream.newLineAtOffset(30, yPosition);
                contentStream.showText("Total General: " + (total != null ? total : "$0.00"));
                contentStream.endText();

                // Línea horizontal debajo de Total General
                yPosition -= 5;
                contentStream.moveTo(30, yPosition);
                contentStream.lineTo(550, yPosition);
                contentStream.stroke();

                // Mensaje
                yPosition -= 40;
                contentStream.beginText();
                contentStream.newLineAtOffset(30, yPosition);
                contentStream.showText("¡Gracias por su ingreso!");
                contentStream.endText();
            }

            System.out.println("Guardando documento en: " + archivoSalida);
            document.save(archivoSalida);
            System.out.println("Documento guardado");
            JOptionPane.showMessageDialog(null, "PDF generado en: " + archivoSalida);
        } catch (IOException e) {
            System.out.println("Error al generar PDF: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar PDF: " + e.getMessage());
        }
    }
}