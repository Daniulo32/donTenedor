/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajax;

import DAO.ReservationsJpaController;
import DTO.Reservations;
import core.goEmail;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlet.tools;

/**
 *
 * @author danieljimenez
 */
public class updateStatusReservation extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        tools t = new tools();
        goEmail sendEmail = new goEmail();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("donTenedorPU");
        ReservationsJpaController ctrReservation = new ReservationsJpaController(emf);
        
        int idReserve = Integer.parseInt(request.getParameter("idReservation"));
        String status = request.getParameter("status");
        
        Reservations reservation = ctrReservation.findReservations(idReserve);
        
        reservation.setStatus(status);
        
        try {
            ctrReservation.edit(reservation);
            String message = "Su reserva para el restaurante "
                    +reservation.getIdRestaurant().getNameRestaurant()+" con fecha "
                    +t.convertDateToString(reservation.getReservationDate())+" "
                    + " a las "+t.convertHourToString(reservation.getHour())+" "
                    + " se ha modificado <br> Estado: "+status+"<br> Sin lo "
                    + "desea pongase en contacto con el restaurante en "
                    +reservation.getIdRestaurant().getEmail()+"<br> Gracias por "
                    + "su confianza";
            sendEmail.send(reservation.getIdUser().getEmail(), "Modificacion en Reserva", message);
        } catch (Exception e) {
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
