/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.ReservationsJpaController;
import DTO.Reservations;
import DTO.Users;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author danieljimenez
 */
public class imprimirPdf extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("donTenedorPU");
        SimpleDateFormat dt = new SimpleDateFormat("'Día' dd 'del' M 'del' yyyy");
        SimpleDateFormat dthour = new SimpleDateFormat("HH:mm");
        DecimalFormat formatDecimal = new DecimalFormat("0.00");
        String prefix = getServletContext().getRealPath("/");

        //indicamos que la salida va a ser un pdf
        response.setContentType("application/pdf");

        //abrimos el flujo
        OutputStream ficheroPdf = response.getOutputStream();

        //obtenemos sesion y obtenemos el usuario de la sesion
        HttpSession sesion = request.getSession();

        Users usuario = (Users) sesion.getAttribute("usuario");

        //obtenemos reserva
        int idReservation = Integer.parseInt(request.getParameter("id"));
        ReservationsJpaController ctrReservas = new ReservationsJpaController(emf);

        Reservations reserva = ctrReservas.findReservations(idReservation);
        
        
        try {
            try {
                // Se crea el documento
                Document documento = new Document();

                PdfWriter write = PdfWriter.getInstance(documento, ficheroPdf);
                write.setInitialLeading(30);

                documento.open();
                //-----------------------------tipos de fuentes para el pdf-------------------------------//
                Font titulo = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.GRAY);
                Font titulo2 = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.WHITE);
                Font fuenteNegrita = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD, BaseColor.WHITE);
                Font fuenteNormal = new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL, BaseColor.BLACK);

                BaseColor colorFondoCelda = new BaseColor(43, 112, 103);

                // añadimos imagen al pdf
                String rutaImagen = prefix + "//images//logo.png";
                Image img = Image.getInstance(rutaImagen);
                img.scaleAbsoluteWidth(250f);
                img.scaleAbsoluteHeight(100f);
                img.setAbsolutePosition(50f, 700f);
                documento.add(img);

                //creamos el codigo de barra de identificacion
                BarcodeEAN barcode = new BarcodeEAN();
                barcode.setCodeType(Barcode.EAN8);
                barcode.setCode("45698745");
                PdfContentByte pdfContentByte = write.getDirectContent();
                Image barCodeImage = barcode.createImageWithBarcode(pdfContentByte, null, null);
                barCodeImage.setAbsolutePosition(430f, 700f);
                barCodeImage.scalePercent(200);

                documento.add(barCodeImage);

                //añadimos titulo de la lista de productos
                documento.add(new Phrase("\n"));
                documento.add(new Phrase("\n"));
                documento.add(new Phrase("\n"));
                documento.add(new Phrase("\n"));
                documento.add(new Phrase("\n"));
                documento.add(new Phrase("\n"));
                documento.add(new Phrase("\n"));

                Paragraph resguardo = new Paragraph();

                resguardo.add(new Phrase("Resguardo de Reserva", titulo));

                resguardo.setAlignment(Element.ALIGN_LEFT);

                documento.add(resguardo);

                documento.add(new Phrase("\n"));

                //Creamos la tabla para los datos personales del cliente y de la empresa, esta tabla no tendra bordes
                PdfPTable tabla = new PdfPTable(4);
                tabla.setTotalWidth(new float[]{120, 200, 120, 200});//anchura de las celdas
                tabla.getDefaultCell().setBorder(Rectangle.NO_BORDER);//celdas sin bordes
                tabla.getDefaultCell().setPaddingTop(8);
                tabla.getDefaultCell().setPaddingBottom(3);
                tabla.setHorizontalAlignment(Element.ALIGN_CENTER);//alineamos la tabla a la izquierda
                tabla.setWidthPercentage(95);//porcentaje que ocupa la tabla

                //creamos una celda que ocupa toda la tabla y asi poner el titulo
                Paragraph parrafoDatosCliente = new Paragraph();
                Phrase tituloDatosCliente = new Phrase("Datos del Cliente", titulo2);
                parrafoDatosCliente.add(tituloDatosCliente);
                parrafoDatosCliente.setAlignment(Element.ALIGN_CENTER);

                PdfPCell celdaDatosCliente = new PdfPCell(parrafoDatosCliente);
                celdaDatosCliente.setColspan(4);
                celdaDatosCliente.setBackgroundColor(BaseColor.LIGHT_GRAY);
                celdaDatosCliente.setPaddingTop(10);
                celdaDatosCliente.setPaddingBottom(10);
                tabla.addCell(celdaDatosCliente);

                PdfPCell celdaNombre = new PdfPCell();
                celdaNombre.setBackgroundColor(colorFondoCelda);
                celdaNombre.addElement(new Phrase("Nombre:", fuenteNegrita));
                celdaNombre.setPaddingBottom(10);
                celdaNombre.setPaddingTop(0);
                tabla.addCell(celdaNombre);

                tabla.addCell(new Phrase(usuario.getName(), fuenteNormal));

                PdfPCell celdaFreserva = new PdfPCell();
                celdaFreserva.setBackgroundColor(colorFondoCelda);
                celdaFreserva.addElement(new Phrase("Apellidos:", fuenteNegrita));
                celdaFreserva.setPaddingBottom(10);
                celdaFreserva.setPaddingTop(0);
                tabla.addCell(celdaFreserva);

                tabla.addCell(new Phrase(reserva.getIdUser().getSubname(), fuenteNormal));

                PdfPCell celdaDni = new PdfPCell();
                celdaDni.setBackgroundColor(colorFondoCelda);
                celdaDni.addElement(new Phrase("D.N.I:", fuenteNegrita));
                celdaDni.setPaddingBottom(10);
                celdaDni.setPaddingTop(0);
                tabla.addCell(celdaDni);

                tabla.addCell(new Phrase(usuario.getDni(), fuenteNormal));

                PdfPCell celdaTel = new PdfPCell();
                celdaTel.setBackgroundColor(colorFondoCelda);
                celdaTel.addElement(new Phrase("E-mail:", fuenteNegrita));
                celdaTel.setPaddingBottom(10);
                celdaTel.setPaddingTop(0);
                tabla.addCell(celdaTel);

                tabla.addCell(new Phrase(usuario.getEmail() + "", fuenteNormal));

                //añadimos a la tabla los datos de la empresa
                Paragraph parrafoDatosEmpresa = new Paragraph();
                Phrase tituloDatosEmpresa = new Phrase("Datos del la Reserva", titulo2);
                parrafoDatosEmpresa.add(tituloDatosEmpresa);
                parrafoDatosEmpresa.setAlignment(Element.ALIGN_CENTER);

                PdfPCell celdaDatosEmpresa = new PdfPCell(parrafoDatosEmpresa);
                celdaDatosEmpresa.setColspan(4);
                celdaDatosEmpresa.setBackgroundColor(BaseColor.LIGHT_GRAY);
                celdaDatosEmpresa.setPaddingTop(10);
                celdaDatosEmpresa.setPaddingBottom(10);
                tabla.addCell(celdaDatosEmpresa);

                PdfPCell celdaDestino = new PdfPCell();
                celdaDestino.setBackgroundColor(colorFondoCelda);
                celdaDestino.addElement(new Phrase("Restaurante:", fuenteNegrita));
                celdaDestino.setPaddingBottom(10);
                celdaDestino.setPaddingTop(0);
                tabla.addCell(celdaDestino);

                tabla.addCell(new Phrase(reserva.getIdRestaurant().getNameRestaurant(), fuenteNormal));

                PdfPCell celdaIda = new PdfPCell();
                celdaIda.setBackgroundColor(colorFondoCelda);
                celdaIda.addElement(new Phrase("Reserva:", fuenteNegrita));
                celdaIda.setPaddingBottom(10);
                celdaIda.setPaddingTop(0);
                tabla.addCell(celdaIda);

                tabla.addCell(new Phrase(dt.format(reserva.getReservationDate()), fuenteNormal));

                PdfPCell celdaRegreso = new PdfPCell();
                celdaRegreso.setBackgroundColor(colorFondoCelda);
                celdaRegreso.addElement(new Phrase("Hora:", fuenteNegrita));
                celdaRegreso.setPaddingBottom(10);
                celdaRegreso.setPaddingTop(0);
                tabla.addCell(celdaRegreso);

                tabla.addCell(new Phrase(dthour.format(reserva.getHour())+"H", fuenteNormal));
                
                PdfPCell celdaDescuento = new PdfPCell();
                celdaDescuento.setBackgroundColor(colorFondoCelda);
                celdaDescuento.addElement(new Phrase("Descuento:", fuenteNegrita));
                celdaDescuento.setPaddingBottom(10);
                celdaDescuento.setPaddingTop(0);
                tabla.addCell(celdaDescuento);
                
                if(reserva.getDiscount() != null){
                    tabla.addCell(new Phrase(reserva.getDiscount()+"% descuento factura", fuenteNormal));
                }else{
                    tabla.addCell(new Phrase("Sin descuento", fuenteNormal));
                }

                documento.add(tabla);
                
                documento.add(new Phrase("\n"));
                
                Paragraph parrafoCondiciones = new Paragraph("Estos términos y condiciones (que pueden ser modificados cada cierto tiempo) son aplicables a todos los servicios directos o indirectos (a través de los distribuidores) disponibles online, a través de cualquier dispositivo móvil, por correo electrónico o por teléfono. Al acceder, navegar y utilizar nuestro sitio web (móvil) o cualquiera de nuestras aplicaciones disponibles a través de plataformas (en adelante, mencionados de forma conjunta como la \"Plataforma\") y/o realizar una reserva, aceptas haber leído, entendido y estar de acuerdo con los términos y condiciones que se muestran (incluyendo la política de privacidad).");
                documento.add(parrafoCondiciones);
                
                documento.add(new Phrase("\n"));
                documento.add(new Phrase("\n"));
                documento.add(new Phrase("\n"));
                
                Paragraph datosContacto = new Paragraph();
                Phrase email = new Phrase("Email: info@dontenedor.com",fuenteNegrita);
                Phrase telefono = new Phrase("Tel: +34/954126539",fuenteNegrita);
                datosContacto.add(email);
                datosContacto.add(new Phrase("\n"));
                datosContacto.add(telefono);
                documento.add(datosContacto);
                
                //------------------------------------------------------------------------------
                documento.close();

            } catch (Exception e) {
            }
        } finally {
            ficheroPdf.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
