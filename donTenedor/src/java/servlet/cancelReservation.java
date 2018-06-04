/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.ReservationsJpaController;
import DTO.Reservations;
import DTO.Restaurant;
import DTO.Users;
import core.goEmail;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author danieljimenez
 */
public class cancelReservation extends HttpServlet {

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

        int idReservation = Integer.parseInt(request.getParameter("idReservation"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("donTenedorPU");
        ReservationsJpaController ctrReservation = new ReservationsJpaController(emf);
        goEmail sendEmail = new goEmail();
        tools t = new tools();
        
        Reservations reserve = ctrReservation.findReservations(idReservation);
        Users user = reserve.getIdUser();
        Restaurant restau = reserve.getIdRestaurant();
        try {
            if (reserve.getStatus().equals("Sin Confirmar")) {
                String message = "El usuario "+user.getName()+" "+user.getSubname()+" "
                        + " ha cancelado la reserva para el "+t.convertDateToString(reserve.getReservationDate())
                        +" a las "+t.convertHourToString(reserve.getHour())+" disculpe "
                        + "las molestias, si lo desea pongase en contacto con el"
                        + " usuario en el correo que le proporcionamos <br> <br> "
                        + "<br> Email: "+user.getEmail();
                sendEmail.send(restau.getEmail(),"Reserva Cancelada",message);
                ctrReservation.destroy(idReservation);
            } else {
                ctrReservation.destroy(idReservation);
            }
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
